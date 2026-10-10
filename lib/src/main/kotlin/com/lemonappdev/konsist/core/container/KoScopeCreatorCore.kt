package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.container.KoScopeCreator
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.core.ext.isKotlinFile
import com.lemonappdev.konsist.core.ext.sep
import com.lemonappdev.konsist.core.ext.toKoFile
import com.lemonappdev.konsist.core.ext.toMacOsSeparator
import com.lemonappdev.konsist.core.ext.toOsSeparator
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.provider.util.KoFileDeclarationProvider
import com.lemonappdev.konsist.core.util.ModuleUtil
import com.lemonappdev.konsist.core.util.ModuleUtil.ROOT_MODULE_NAME
import kotlinx.coroutines.coroutineScope
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

    override fun scopeFromModules(moduleNames: Collection<String>): KoScopeCore =
        runBlocking {
            require(moduleNames.isNotEmpty()) { "Module names are empty, but at least one module name should be provided." }

            moduleNames
                .distinctBy { ModuleUtil.normalizeModuleName(it) }
                .flatMap { moduleName ->
                    getFiles(moduleName).also {
                        require(it.isNotEmpty()) { "Module does not contain any Kotlin files: '$moduleName'" }
                    }
                }.let { KoScopeCore(it) }
        }

    override fun scopeFromPackage(
        packagee: String,
        moduleName: String?,
        sourceSetName: String?,
    ): KoScope =
        runBlocking {
            val koFiles =
                getFiles(moduleName, sourceSetName)
                    .withPackage(packagee)

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

    private suspend fun getFiles(
        moduleName: String? = null,
        sourceSetName: String? = null,
        ignoreBuildConfig: Boolean = true,
    ): List<KoFileDeclaration> =
        coroutineScope {
            moduleName?.let { requireModuleDirectoryExists(it) }

            val localProjectKotlinFiles =
                KoFileDeclarationProvider
                    .getKoFileDeclarations()
                    .let {
                        if (ignoreBuildConfig) {
                            it.filterNot { file -> file.isBuildConfigFile() }
                        } else {
                            it
                        }
                    }

            if (moduleName == null && sourceSetName == null) {
                return@coroutineScope localProjectKotlinFiles
            }

            val pathRegex = getPathRegex(projectRootPath, moduleName, sourceSetName)

            return@coroutineScope localProjectKotlinFiles
                .filter { it.path.toMacOsSeparator().matches(pathRegex) }
        }

    override fun scopeFromProduction(
        moduleName: String?,
        sourceSetName: String?,
    ): KoScope =
        runBlocking {
            sourceSetName?.let {
                require(!isTestSourceSet(it)) { "Source set '$it' is a test source set, but it should be production source set." }
            }

            val koFiles =
                getFiles(moduleName, sourceSetName)
                    .filterNot { isTestSourceSet(it.sourceSetName) }

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

            val koFiles =
                getFiles(moduleName, sourceSetName)
                    .filter { isTestSourceSet(it.sourceSetName) }

            KoScopeCore(koFiles)
        }

    /**
     * Get the scope of the paths obtaining the absolute path of it and, getting the files from that directory
     */
    private fun getScopeFromPaths(paths: Collection<String>): KoScope {
        val filesFromPaths =
            paths
                .map { getAbsolutePath(projectPath = it) }
                .flatMap { getFilesFromDirectory(absolutePath = it) }

        return KoScopeCore(koFiles = filesFromPaths)
    }

    /**
     * Obtain all the files belonging to [absolutePath].
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

    /**
     * Throws an [IllegalArgumentException] when the module directory does not exist, so a misspelled module name
     * does not silently produce an empty scope.
     */
    private fun requireModuleDirectoryExists(moduleName: String) {
        val normalizedModuleName = ModuleUtil.normalizeModuleName(moduleName)

        if (normalizedModuleName == ROOT_MODULE_NAME) {
            return
        }

        val moduleDirectory = File(getAbsolutePath(normalizedModuleName))
        require(moduleDirectory.isDirectory) {
            "Module does not exist: '$moduleName'. Directory not found: ${moduleDirectory.path}"
        }
    }

    private fun getAbsolutePath(projectPath: String): String = "$projectRootPath$sep${projectPath.toOsSeparator()}"

    private fun isTestSourceSet(name: String): Boolean {
        val lowercaseName = name.lowercase()
        return lowercaseName.substringAfter(':').matches(Regex(".*$TEST_NAME_IN_PATH.*"))
    }

    private fun KoFileDeclaration.isBuildConfigFile(): Boolean {
        val lowercasePath = path.lowercase().toMacOsSeparator()
        val gradleBuildConfigDirectoryName = "buildSrc".lowercase()
        return lowercasePath.matches(Regex(".*/$gradleBuildConfigDirectoryName.*"))
    }

    private fun File.toKoFiles(): List<KoFileDeclaration> =
        walk()
            .filter { it.isKotlinFile }
            .map { it.toKoFile() }
            .toList()

    private fun getKoFiles(files: List<File>) =
        runBlocking {
            KoFileDeclarationProvider
                .getKoFileDeclarations()
                .filter {
                    files.any { file ->
                        file.path == it.path
                    }
                }
        }

    companion object {
        private const val TEST_NAME_IN_PATH = "test"

        /**
         * Builds regex matching file paths of given module and source set. Path parts are escaped, so folder names
         * containing regex characters (e.g. `C:\Projects (1)\app`) are matched literally. Leading and trailing
         * separators of module and source set names are ignored (e.g. `feature/auth/` is treated as `feature/auth`).
         * Module name can be a Gradle project path (e.g. `:feature:auth` is treated as `feature/auth`).
         */
        internal fun getPathRegex(
            projectRootPath: String,
            moduleName: String?,
            sourceSetName: String?,
        ): Regex {
            val rootPathPattern = Regex.escape(projectRootPath.toMacOsSeparator())

            val modulePattern =
                when (val normalizedModuleName = moduleName?.let { ModuleUtil.normalizeModuleName(it) }) {
                    ROOT_MODULE_NAME -> rootPathPattern
                    null -> "$rootPathPattern.*"
                    else -> "$rootPathPattern/${Regex.escape(normalizedModuleName)}"
                }

            val sourceSetPattern =
                if (sourceSetName != null) {
                    "/src/${Regex.escape(sourceSetName.toMacOsSeparator().trim('/'))}/.*"
                } else {
                    "/src/.*"
                }

            return Regex("$modulePattern$sourceSetPattern")
        }
    }
}
