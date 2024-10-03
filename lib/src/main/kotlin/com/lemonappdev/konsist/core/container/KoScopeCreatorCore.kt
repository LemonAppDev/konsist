package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.container.KoScopeCreator
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.core.ext.isKotlinFile
import com.lemonappdev.konsist.core.ext.sep
import com.lemonappdev.konsist.core.ext.toKoFile
import com.lemonappdev.konsist.core.ext.toMacOsSeparator
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.provider.util.KoFileDeclarationProvider
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import java.io.File

@Suppress("detekt.TooManyFunctions")
internal class KoScopeCreatorCore : KoScopeCreator {
    override val projectRootPath: String by lazy { PathProvider.rootProjectPath }

    private val gradleRootBuildDirectoryRegex by lazy {
        Regex("$projectRootPath/$GRADLE_BUILD_DIR/.*".toMacOsSeparator())
    }
    private val gradleModuleBuildDirectoryRegex by lazy {
        Regex("$projectRootPath/.+/$GRADLE_BUILD_DIR/.*".toMacOsSeparator())
    }
    private val mavenRootBuildDirectoryRegex by lazy {
        Regex("$projectRootPath/$MAVEN_BUILD_DIR/.*".toMacOsSeparator())
    }
    private val mavenModuleBuildDirectoryRegex by lazy {
        Regex("$projectRootPath/.+/$MAVEN_BUILD_DIR/.*".toMacOsSeparator())
    }
    private val gradleDotGradleDirectoryRegex by lazy {
        Regex("$projectRootPath/.gradle/.*".toMacOsSeparator())
    }

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
            moduleNames
                .flatMap { getFiles(it) }
                .let { KoScopeCore(it) }
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
            val localProjectKotlinFiles =
                KoFileDeclarationProvider
                    .getKoFileDeclarations { !isBuildToolPath(it.path.toMacOsSeparator()) }
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

            var pathPrefix =
                if (moduleName == ROOT_MODULE_NAME) {
                    projectRootPath
                } else if (moduleName != null) {
                    "$projectRootPath/$moduleName"
                } else {
                    "$projectRootPath.*"
                }

            pathPrefix =
                if (sourceSetName != null) {
                    "$pathPrefix/src/$sourceSetName/.*"
                } else {
                    "$pathPrefix/src/.*"
                }.toMacOsSeparator()

            return@coroutineScope localProjectKotlinFiles
                .filter { it.path.toMacOsSeparator().matches(Regex(pathPrefix)) }
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
        val directory = File(absolutePath)
        require(directory.exists()) { "Directory does not exist: $absolutePath" }
        require(!directory.isFile) { "Path is a file, but should be a directory: $absolutePath" }

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

    private fun getAbsolutePath(projectPath: String): String = "$projectRootPath$sep$projectPath"

    /**
     * Determines whether the provided path corresponds to a directory created by a build tool (Gradle, Maven)
     */
    private fun isBuildToolPath(path: String): Boolean = isBuildOrTargetPath(path) || isDotGradlePath(path)

    /**
     * Determines if the given path is a build directory "build" for Gradle and "target" for Maven.
     *
     * Maven target directory and the Gradle build directory are used to store the results of the build process,
     * such as compiled code and packaged artifacts. The specific names ("target" for Maven and "build" for Gradle)
     * are conventions established by each build tool to organize and manage these files. Developers working with
     * these tools need to be aware of these directories to locate and work with the output of their builds.
     */
    private fun isBuildOrTargetPath(path: String): Boolean =
        path.matches(gradleRootBuildDirectoryRegex) ||
            path.matches(gradleModuleBuildDirectoryRegex) ||
            path.matches(mavenRootBuildDirectoryRegex) ||
            path.matches(mavenModuleBuildDirectoryRegex)

    /**
     * Determines if the given path is a directory ".gradle".
     * This directory is not intended to store project files however Gradle can cache some Kotlin files there
     * (.gradle/caches directory).
     *
     * The .gradle directory is a directory created and used by the Gradle build tool. It stores various files and
     * caches related to the build process, including the Gradle Wrapper scripts, dependency caches, and build-related
     * metadata. This directory helps improve the efficiency of Gradle builds by storing and managing essential
     * information and artifacts.
     */
    private fun isDotGradlePath(path: String): Boolean = path.matches(gradleDotGradleDirectoryRegex)

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
                .getKoFileDeclarations { !isBuildToolPath(it.path.toMacOsSeparator()) }
                .filter {
                    files.any { file ->
                        file.path == it.path
                    }
                }
        }

    companion object {
        private const val TEST_NAME_IN_PATH = "test"
        private const val ROOT_MODULE_NAME = "root"

        private const val GRADLE_BUILD_DIR = "build"
        private const val MAVEN_BUILD_DIR = "target"
    }
}
