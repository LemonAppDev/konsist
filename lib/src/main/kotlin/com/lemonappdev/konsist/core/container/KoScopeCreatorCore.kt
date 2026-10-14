package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.container.KoScopeCreator
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.ext.isKotlinFile
import com.lemonappdev.konsist.core.ext.sep
import com.lemonappdev.konsist.core.ext.toKoFile
import com.lemonappdev.konsist.core.ext.toMacOsSeparator
import com.lemonappdev.konsist.core.ext.toOsSeparator
import com.lemonappdev.konsist.core.filesystem.ModuleDirectoryDetector
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.provider.util.KoFileDeclarationProvider
import com.lemonappdev.konsist.core.util.LocationUtil
import com.lemonappdev.konsist.core.util.ModuleUtil
import com.lemonappdev.konsist.core.util.PathUtil
import kotlinx.coroutines.runBlocking
import java.io.File

@Suppress("detekt.TooManyFunctions")
internal class KoScopeCreatorCore : KoScopeCreator {
    override val projectRootPath: String by lazy { PathProvider.rootProjectPath }

    override fun scopeFromProject(
        moduleName: String?,
        sourceSetName: String?,
        ignoreBuildConfig: Boolean,
    ): KoScope =
        runBlocking {
            val koFiles = getFiles(moduleName, sourceSetName, ignoreBuildConfig)
            KoScopeCore(koFiles)
        }

    override fun scopeFromModule(
        moduleName: String,
        vararg moduleNames: String,
    ): KoScope = scopeFromModules(setOf(moduleName) + moduleNames)

    /*
     * Files are collected in a single pass, so a file matching many module names (e.g. ":feature.." and
     * ":feature:payment") is added once.
     */
    override fun scopeFromModules(moduleNames: Collection<String>): KoScopeCore =
        runBlocking {
            val moduleNameMatchers = moduleNames.map { ModuleUtil.getModuleNameMatcher(it) }
            val koFiles =
                getFiles { file ->
                    val fileModuleName = getModuleName(file.path, projectRootPath)
                    moduleNameMatchers.any { it(fileModuleName) }
                }

            KoScopeCore(koFiles)
        }

    override fun scopeFromPackage(
        packagee: String,
        moduleName: String?,
        sourceSetName: String?,
    ): KoScope =
        runBlocking {
            val koFiles = getFiles(moduleName, sourceSetName) { residesInPackage(it, packagee) }

            KoScopeCore(koFiles)
        }

    override fun scopeFromSourceSet(
        sourceSetName: String,
        vararg sourceSetNames: String,
    ): KoScope = scopeFromSourceSets(setOf(sourceSetName) + sourceSetNames)

    override fun scopeFromSourceSets(sourceSetNames: Collection<String>): KoScope =
        runBlocking {
            sourceSetNames
                .flatMap { getFiles(sourceSetName = it) }
                .let { KoScopeCore(it) }
        }

    /*
     * Files are filtered by path before parsing, so files of other modules and source sets are not parsed.
     * Files located outside the module "src" directory (e.g. "/app/A.kt" and "/app/testData/A.kt") are ignored.
     * Module and source set are computed the same way as [KoFileDeclaration.moduleName] and
     * [KoFileDeclaration.sourceSetName], so module scope contains exactly the module files located in the "src"
     * directory and source set scope contains exactly the files residing in the source set.
     */
    private suspend fun getFiles(
        moduleName: String? = null,
        sourceSetName: String? = null,
        ignoreBuildConfig: Boolean = true,
        predicate: (File) -> Boolean = { true },
    ): List<KoFileDeclaration> {
        val moduleNameMatcher = moduleName?.let { ModuleUtil.getModuleNameMatcher(it) }

        return KoFileDeclarationProvider.getKoFileDeclarations { file ->
            residesInSourceDirectory(file.path, projectRootPath) &&
                (!ignoreBuildConfig || !KoFileDeclarationProvider.isBuildConfigFile(file.path)) &&
                (moduleNameMatcher == null || moduleNameMatcher(getModuleName(file.path, projectRootPath))) &&
                (sourceSetName == null || residesInSourceSet(file.path, projectRootPath, sourceSetName)) &&
                predicate(file)
        }
    }

    override fun scopeFromProduction(
        moduleName: String?,
        sourceSetName: String?,
    ): KoScope =
        runBlocking {
            sourceSetName?.let {
                require(!isTestSourceSet(it)) { "Source set '$it' is a test source set, but it should be production source set." }
            }

            val koFiles = getFiles(moduleName, sourceSetName) { !isTestSourceSet(getSourceSetName(it.path, projectRootPath)) }

            KoScopeCore(koFiles)
        }

    override fun scopeFromTest(
        moduleName: String?,
        sourceSetName: String?,
    ): KoScope =
        runBlocking {
            sourceSetName?.let {
                require(isTestSourceSet(it)) { "Source set '$it' is a production source set, but it should be test source set." }
            }

            val koFiles = getFiles(moduleName, sourceSetName) { isTestSourceSet(getSourceSetName(it.path, projectRootPath)) }

            KoScopeCore(koFiles)
        }

    /**
     * Creates a scope from the given paths by resolving their absolute paths and collecting the files from those directories.
     */
    private fun getScopeFromPaths(paths: Collection<String>): KoScope {
        val filesFromPaths =
            paths
                .map { getAbsolutePath(projectPath = it) }
                .flatMap { getFilesFromDirectory(absolutePath = it) }

        return KoScopeCore(koFiles = filesFromPaths)
    }

    /**
     * Obtains all the files belonging to [absolutePath].
     * The function will throw an [IllegalArgumentException] when:
     *  - the directory does not exist.
     *  - the path is a file.
     */
    private fun getFilesFromDirectory(absolutePath: String): List<KoFileDeclaration> {
        val osAbsolutePath = absolutePath.toOsSeparator()
        val directory = File(osAbsolutePath)
        require(directory.exists()) { "Directory does not exist: $osAbsolutePath" }
        require(!directory.isFile) { "Path is a file, but should be a directory: $osAbsolutePath" }

        return directory.toKoFiles()
    }

    override fun scopeFromDirectory(
        path: String,
        vararg paths: String,
    ): KoScope = getScopeFromPaths(paths = setOf(path) + paths)

    override fun scopeFromDirectories(paths: Collection<String>): KoScope = getScopeFromPaths(paths = paths)

    override fun scopeFromExternalDirectory(
        absolutePath: String,
        vararg paths: String,
    ): KoScope {
        val totalPaths = setOf(absolutePath) + paths
        val filesFromPaths = totalPaths.flatMap { getFilesFromDirectory(absolutePath = it) }

        return KoScopeCore(koFiles = filesFromPaths)
    }

    override fun scopeFromExternalDirectories(absolutePaths: Collection<String>): KoScope {
        val filesFromPaths = absolutePaths.flatMap { getFilesFromDirectory(absolutePath = it) }

        return KoScopeCore(koFiles = filesFromPaths)
    }

    override fun scopeFromFile(
        path: String,
        vararg paths: String,
    ): KoScope = scopeFromFiles(setOf(path) + paths)

    override fun scopeFromFiles(paths: Collection<String>): KoScope {
        val files =
            paths
                .map { getAbsolutePath(it) }
                .map { File(it) }
                .onEach {
                    require(it.exists()) { "File does not exist: ${it.absolutePath}" }
                    require(it.isFile) { "Path is a directory, but should be a file: ${it.absolutePath}" }
                }

        val notKotlinFiles =
            files
                .filterNot { it.isKotlinFile }
                .map { it.toKoFile() }

        val koFiles = getKoFiles(files)

        return KoScopeCore(koFiles + notKotlinFiles)
    }

    /*
     * Package name is read without parsing the file (see [KoFileDeclarationProvider.getPackageName]).
     */
    private fun residesInPackage(
        file: File,
        packagee: String,
    ): Boolean {
        val packageName = KoFileDeclarationProvider.getPackageName(file)
        return !packageName.isNullOrEmpty() && LocationUtil.residesInLocation(packagee, packageName)
    }

    private fun getAbsolutePath(projectPath: String): String = "$projectRootPath$sep${projectPath.toOsSeparator()}"

    private fun isTestSourceSet(name: String): Boolean {
        val lowercaseName = name.lowercase()
        return lowercaseName.substringAfter(':').matches(Regex(".*$TEST_NAME_IN_PATH.*"))
    }

    private fun File.toKoFiles(): List<KoFileDeclaration> =
        walk()
            .filter { it.isKotlinFile }
            .map { it.toKoFile() }
            .toList()

    /*
     * Returns project files with the given paths. Other project files are not parsed.
     */
    private fun getKoFiles(files: List<File>): List<KoFileDeclaration> {
        val paths = files.map { it.path }.toSet()

        return runBlocking {
            KoFileDeclarationProvider.getKoFileDeclarations { it.path in paths }
        }
    }

    companion object {
        private const val TEST_NAME_IN_PATH = "test"

        /**
         * Returns true if the file with given absolute [filePath] resides in the module with given [moduleName]
         * (see [ModuleUtil.residesInModule]).
         */
        internal fun residesInModule(
            filePath: String,
            projectRootPath: String,
            moduleName: String,
        ): Boolean = ModuleUtil.residesInModule(getModuleName(filePath, projectRootPath), moduleName)

        /**
         * Returns true if the file with given absolute [filePath] resides in the source set with given [sourceSetName]
         * (see [PathUtil.getSourceSetName]). Leading and trailing separators of source set name are ignored
         * (e.g. `main/` is treated as `main`). Blank source set name does not match any file.
         */
        internal fun residesInSourceSet(
            filePath: String,
            projectRootPath: String,
            sourceSetName: String,
        ): Boolean {
            val normalizedSourceSetName = sourceSetName.toMacOsSeparator().trim().trim('/')

            return normalizedSourceSetName.isNotEmpty() && getSourceSetName(filePath, projectRootPath) == normalizedSourceSetName
        }

        /**
         * Returns true if the file with given absolute [filePath] is located in the module "src" directory
         * (see [PathUtil.residesInSourceDirectory]). Module directory is not computed for the file without "src"
         * directory in its path.
         */
        internal fun residesInSourceDirectory(
            filePath: String,
            projectRootPath: String,
        ): Boolean {
            val projectPath = getProjectPath(filePath, projectRootPath)

            if (PathUtil.getSourceDirectoryIndex(projectPath) == -1) {
                return false
            }

            val moduleDirectoryPath = getModuleDirectoryPath(projectPath, projectRootPath)
            return PathUtil.residesInSourceDirectory(projectPath, moduleDirectoryPath)
        }

        /**
         * Returns module name (see [ModuleUtil.getModuleName]) of the file with given absolute [filePath].
         */
        private fun getModuleName(
            filePath: String,
            projectRootPath: String,
        ): String =
            ModuleUtil.getModuleName(
                getProjectPath(filePath, projectRootPath),
                isSourceSetDirectory = { ModuleDirectoryDetector.isSourceSetDirectory(projectRootPath, it) },
                getModuleDirectory = { ModuleDirectoryDetector.getModuleDirectory(projectRootPath, it) },
            )

        /**
         * Returns source set name (see [PathUtil.getSourceSetName]) of the file with given absolute [filePath].
         */
        private fun getSourceSetName(
            filePath: String,
            projectRootPath: String,
        ): String {
            val projectPath = getProjectPath(filePath, projectRootPath)
            val moduleDirectoryPath = getModuleDirectoryPath(projectPath, projectRootPath)

            return PathUtil.getSourceSetName(projectPath, moduleDirectoryPath)
        }

        /**
         * Returns path of the file with given absolute [filePath] relative to the project root with "/" separators.
         * Paths with "/" and "\\" separators are supported on all OSes.
         * Trailing separator of the project root path is ignored, so drive roots (e.g. `X:\`) and `/` are supported.
         */
        private fun getProjectPath(
            filePath: String,
            projectRootPath: String,
        ): String =
            filePath
                .toMacOsSeparator()
                .removePrefix(projectRootPath.toMacOsSeparator().trimEnd('/'))

        /**
         * Returns module directory path (see [ModuleUtil.getModuleDirectoryPath]) of the file with given [projectPath].
         */
        private fun getModuleDirectoryPath(
            projectPath: String,
            projectRootPath: String,
        ): String =
            ModuleUtil.getModuleDirectoryPath(
                projectPath,
                isSourceSetDirectory = { ModuleDirectoryDetector.isSourceSetDirectory(projectRootPath, it) },
                getModuleDirectory = { ModuleDirectoryDetector.getModuleDirectory(projectRootPath, it) },
            )
    }
}
