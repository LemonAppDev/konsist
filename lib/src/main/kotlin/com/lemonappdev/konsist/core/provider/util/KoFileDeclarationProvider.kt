package com.lemonappdev.konsist.core.provider.util

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.cache.KoFileDeclarationCache
import com.lemonappdev.konsist.core.ext.isKotlinFile
import com.lemonappdev.konsist.core.ext.toKoFile
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.filesystem.ProjectDirectoryFilter
import com.lemonappdev.konsist.core.filesystem.ProjectKotlinFileScanner
import com.lemonappdev.konsist.core.util.PackageNameReader
import com.lemonappdev.konsist.core.util.PathUtil
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.File

internal object KoFileDeclarationProvider {
    private val projectRootDir: File = File(PathProvider.rootProjectPath)

    private val projectKotlinFileScanner = ProjectKotlinFileScanner(projectRootDir)

    private val mutex: Mutex = Mutex()

    @Volatile
    private var projectKotlinFilesDeferred: Deferred<List<File>>? = null

    init {
        check(projectRootDir.exists()) { "Directory does not exist: ${projectRootDir.absolutePath}" }
        check(projectRootDir.isDirectory) { "Project root directory is a File ${projectRootDir.absolutePath}" }
    }

    /**
     * Retrieves a list of Kotlin files from the project's root directory. The directory is scanned once, files are not
     * parsed. Directories ignored by [ProjectDirectoryFilter] (build outputs, hidden directories, node_modules) are not
     * scanned. Directories are scanned in parallel (see [ProjectKotlinFileScanner]).
     *
     * Threading Strategy:
     * Ensures thread-safe initialization of a single deferred operation to scan the directory, using a mutex to guard
     * against concurrent initializations.
     *
     * e.g.
     * 1. getProjectKotlinFiles started at Thread 1 - start directory scanning
     * 2. getProjectKotlinFiles started at Thread 2 - directory scanning in progress, wait for it to complete
     * 3. getProjectKotlinFiles started at Thread 1 completes
     * 4. getProjectKotlinFiles started at Thread 2 completes
     */
    suspend fun getProjectKotlinFiles(): List<File> =
        coroutineScope {
            val currentDeferred: Deferred<List<File>> =
                mutex.withLock {
                    projectKotlinFilesDeferred ?: async(Dispatchers.IO) { projectKotlinFileScanner.scan() }
                        .also { projectKotlinFilesDeferred = it }
                }

            currentDeferred.await()
        }

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
        This is particularly common with code generators. We don't exclude build or generated files at this stage
        (that happens later), so we need to be more permissive in the initial loading process.
        We verify the file is still a valid Kotlin file before parsing to handle cases where files may have been
        removed or altered. If the file is no longer valid, we return null instead of attempting to parse it.
         */
        return when {
            file.isKotlinFile -> file.toKoFile()
            else -> null
        }
    }
}
