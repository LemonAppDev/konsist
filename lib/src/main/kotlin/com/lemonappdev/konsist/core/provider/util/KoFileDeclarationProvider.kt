package com.lemonappdev.konsist.core.provider.util

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.cache.KoFileDeclarationCache
import com.lemonappdev.konsist.core.ext.isKotlinFile
import com.lemonappdev.konsist.core.ext.toKoFile
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.filesystem.ProjectDirectoryFilter
import com.lemonappdev.konsist.core.filesystem.ProjectFilesLoader
import com.lemonappdev.konsist.core.filesystem.ProjectKotlinFileScanner
import com.lemonappdev.konsist.core.util.PackageNameReader
import com.lemonappdev.konsist.core.util.PathUtil
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import java.io.File

internal object KoFileDeclarationProvider {
    private val projectRootDir: File = File(PathProvider.rootProjectPath)

    private val projectKotlinFileScanner = ProjectKotlinFileScanner(projectRootDir)

    private val projectFilesLoader = ProjectFilesLoader { projectKotlinFileScanner.scan() }

    init {
        check(projectRootDir.exists()) { "Directory does not exist: ${projectRootDir.absolutePath}" }
        check(projectRootDir.isDirectory) { "Project root directory is a File ${projectRootDir.absolutePath}" }
    }

    /**
     * Retrieves a list of Kotlin files from the project's root directory. The directory is scanned once, files are not
     * parsed. Directories ignored by [ProjectDirectoryFilter] (build outputs, hidden directories, node_modules) are not
     * scanned. Directories are scanned in parallel (see [ProjectKotlinFileScanner]).
     *
     * Concurrent callers share a single scan. Cancelled or failed scan is retried by the next call
     * (see [ProjectFilesLoader]).
     */
    suspend fun getProjectKotlinFiles(): List<File> = projectFilesLoader.getFiles()

    /**
     * Retrieves a list of [KoFileDeclaration]s of the project's Kotlin files matching the [predicate].
     * Files are filtered before parsing, so files not matching the [predicate] are not parsed.
     *
     * Parsed files are cached (see [KoFileDeclarationCache]), files that are not parsed yet are parsed concurrently.
     *
     * @return A list of [KoFileDeclaration]s representing the parsed Kotlin files.
     * @throws Exception if there's an issue accessing the file system or parsing the files.
     */
    suspend fun getKoFileDeclarations(predicate: (File) -> Boolean = { true }): List<KoFileDeclaration> {
        val files = getProjectKotlinFiles().filter(predicate)
        val parsedFiles = files.map { KoFileDeclarationCache.getInstanceOrNull(it) }

        if (parsedFiles.all { it != null }) {
            return parsedFiles.filterNotNull()
        }

        return coroutineScope {
            files
                .zip(parsedFiles) { file, parsedFile ->
                    parsedFile?.let { CompletableDeferred(it) } ?: async(Dispatchers.IO) { parseKotlinFile(file) }
                }.awaitAll()
                .filterNotNull()
        }
    }

    /**
     * Returns the package name of the file (empty for the file without package) or `null` when the file can't be read.
     * Package name of the parsed file is taken from its package directive, other files are not parsed
     * (see [PackageNameReader]).
     */
    fun getPackageName(file: File): String? =
        KoFileDeclarationCache.getInstanceOrNull(file)?.let { it.packagee?.name.orEmpty() }
            ?: PackageNameReader.getPackageName(file)

    /**
     * Returns true if the file is located in the Gradle build configuration directory (buildSrc).
     * Only the path relative to the project root is checked (see [PathUtil.isBuildConfigPath]).
     */
    fun isBuildConfigFile(path: String): Boolean = PathUtil.isBuildConfigPath(PathUtil.getProjectPath(path, PathProvider.rootProjectPath))

    private fun parseKotlinFile(file: File): KoFileDeclaration? {
        /*
        Due to asynchronous execution, the file's state may have changed since the initial file tree walk.
        This is particularly common with code generators.
        We verify the file is still a valid Kotlin file before parsing to handle cases where files may have been
        removed or altered. If the file is no longer valid, we return null instead of attempting to parse it.
         */
        return when {
            file.isKotlinFile -> file.toKoFile()
            else -> null
        }
    }
}
