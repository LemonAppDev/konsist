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
import java.io.File

@Suppress("detekt.TooManyFunctions")
internal class KoScopeCreatorCore : KoScopeCreator {
    private val pathProvider: PathProvider by lazy { PathProvider.getInstance() }

    private val projectKotlinFiles: List<KoFileDeclaration> by lazy { File(pathProvider.rootProjectPath).toKoFiles() }

    override val projectRootPath: String by lazy { pathProvider.rootProjectPath }

    override fun scopeFromProject(moduleName: String?, sourceSetName: String?, ignoreBuildConfig: Boolean): KoScope {
        val koFiles = getFiles(moduleName, sourceSetName, ignoreBuildConfig)
        return KoScopeCore(koFiles)
    }

    override fun scopeFromModule(moduleName: String, vararg moduleNames: String): KoScope =
        (listOf(moduleName) + moduleNames)
            .flatMap { getFiles(it) }
            .let { KoScopeCore(it) }

    override fun scopeFromPackage(packagee: String, moduleName: String?, sourceSetName: String?): KoScope {
        val koFiles = getFiles(moduleName, sourceSetName)
            .withPackage(packagee)

        return KoScopeCore(koFiles)
    }

    override fun scopeFromSourceSet(sourceSetName: String, vararg sourceSetNames: String): KoScope =
        (listOf(sourceSetName) + sourceSetNames)
            .flatMap { getFiles(sourceSetName = it) }
            .let { KoScopeCore(it) }

    private fun getFiles(
        moduleName: String? = null,
        sourceSetName: String? = null,
        ignoreBuildConfig: Boolean = true,
    ): List<KoFileDeclaration> {
        val localProjectKotlinFiles = projectKotlinFiles
            .filterNot { isBuildToolPath(it.path.toMacOsSeparator()) }
            .let {
                if (ignoreBuildConfig) {
                    it.filterNot { file -> file.isBuildConfigFile() }
                } else {
                    it
                }
            }

        if (moduleName == null && sourceSetName == null) {
            return localProjectKotlinFiles
        }

        var pathPrefix = if (moduleName == ROOT_MODULE_NAME) {
            projectRootPath
        } else if (moduleName != null) {
            "$projectRootPath/$moduleName"
        } else {
            "$projectRootPath.*"
        }

        pathPrefix = if (sourceSetName != null) {
            "$pathPrefix/src/$sourceSetName/.*"
        } else {
            "$pathPrefix/src/.*"
        }.toMacOsSeparator()

        return localProjectKotlinFiles
            .filter { it.path.toMacOsSeparator().matches(Regex(pathPrefix)) }
    }

    override fun scopeFromProduction(moduleName: String?, sourceSetName: String?): KoScope {
        sourceSetName?.let {
            require(!isTestSourceSet(it)) { "Source set '$it' is a test source set, but it should be production source set." }
        }

        val koFiles = getFiles(moduleName, sourceSetName)
            .filterNot { isTestSourceSet(it.sourceSetName) }

        return KoScopeCore(koFiles)
    }

    override fun scopeFromTest(moduleName: String?, sourceSetName: String?): KoScope {
        sourceSetName?.let {
            require(isTestSourceSet(it)) { "Source set '$it' is a production source set, but it should be test source set." }
        }

        val koFiles = getFiles(moduleName, sourceSetName)
            .filter { isTestSourceSet(it.sourceSetName) }

        return KoScopeCore(koFiles)
    }

    override fun scopeFromDirectory(path: String): KoScope {
        val absolutePath = "$projectRootPath$sep$path"
        return createScopeFromDirectory(absolutePath)
    }

    override fun scopeFromExternalDirectory(absolutePath: String): KoScope = createScopeFromDirectory(absolutePath)

    private fun createScopeFromDirectory(absolutePath: String): KoScope {
        val directory = File(absolutePath)
        require(directory.exists()) { "Directory does not exist: $absolutePath" }
        require(!directory.isFile) { "Path is a file, but should be a directory: $absolutePath" }

        val files = directory
            .walk()
            .filter { it.isKotlinFile }
            .toList()

        val koFiles = getKoFiles(files)

        return KoScopeCore(koFiles)
    }

    override fun scopeFromFile(path: String, vararg paths: String): KoScope = scopeFromFiles(setOf(path) + paths)

    override fun scopeFromFiles(paths: Set<String>): KoScope {
        val files = paths
            .map { getAbsolutePath(it) }
            .map { File(it) }
            .onEach {
                require(it.exists()) { "File does not exist: ${it.absolutePath}" }
                require(it.isFile) { "Path is a directory, but should be a file: ${it.absolutePath}" }
            }

        val notKotlinFiles = files
            .filterNot { it.isKotlinFile }
            .map { it.toKoFile() }

        val koFiles = getKoFiles(files)

        return KoScopeCore(koFiles + notKotlinFiles)
    }

    private fun getAbsolutePath(projectPath: String): String = "$projectRootPath$sep$projectPath"

    /**
     *
     * Determines whether the provided path corresponds to a directory created by a build tool (Gradle, Maven)
     */
    private fun isBuildToolPath(path: String): Boolean {
        return isBuildOrTargetPath(path) || isDotGradlePath(path)
    }

    /**
     * Determines if the given path is a build directory "build" for Gradle and "target" for Maven.
     *
     * Maven target directory and the Gradle build directory are used to store the results of the build process,
     * such as compiled code and packaged artifacts. The specific names ("target" for Maven and "build" for Gradle)
     * are conventions established by each build tool to organize and manage these files. Developers working with
     * these tools need to be aware of these directories to locate and work with the output of their builds.
     */
    private fun isBuildOrTargetPath(path: String): Boolean {
        val gradleBuildDirectoryName = "build"
        val gradleRootBuildDirectoryRegex = Regex("$projectRootPath/$gradleBuildDirectoryName/.*".toMacOsSeparator())
        val gradleModuleBuildDirectoryRegex =
            Regex("$projectRootPath/.+/$gradleBuildDirectoryName/.*".toMacOsSeparator())

        val mavenBuildDirectoryName = "target"
        val mavenRootBuildDirectoryRegex = Regex("$projectRootPath/$mavenBuildDirectoryName/.*".toMacOsSeparator())
        val mavenModuleBuildDirectoryRegex = Regex("$projectRootPath/.+/$mavenBuildDirectoryName/.*".toMacOsSeparator())

        return path.matches(gradleRootBuildDirectoryRegex) ||
            path.matches(gradleModuleBuildDirectoryRegex) ||
            path.matches(mavenRootBuildDirectoryRegex) ||
            path.matches(mavenModuleBuildDirectoryRegex)
    }

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
    private fun isDotGradlePath(path: String): Boolean {
        val dotGradleBuildDirectoryName = ".gradle"
        val gradleRootBuildDirectoryRegex = Regex("$projectRootPath/$dotGradleBuildDirectoryName/.*".toMacOsSeparator())
        return path.matches(gradleRootBuildDirectoryRegex)
    }

    private fun isTestSourceSet(name: String): Boolean {
        val lowercaseName = name.lowercase()
        return lowercaseName.substringAfter(':').matches(Regex(".*$TEST_NAME_IN_PATH.*"))
    }

    private fun KoFileDeclaration.isBuildConfigFile(): Boolean {
        val lowercasePath = path.lowercase().toMacOsSeparator()
        val gradleBuildConfigDirectoryName = "buildSrc".lowercase()
        return lowercasePath.matches(Regex(".*/$gradleBuildConfigDirectoryName.*"))
    }

    private fun File.toKoFiles(): List<KoFileDeclaration> = walk()
        .filter { it.isKotlinFile }
        .map { it.toKoFile() }
        .toList()

    private fun getKoFiles(files: List<File>) = projectKotlinFiles.filter {
        files.any { file ->
            file.path == it.path
        }
    }

    companion object {
        private const val TEST_NAME_IN_PATH = "test"
        private const val ROOT_MODULE_NAME = "root"
    }
}
