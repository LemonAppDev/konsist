package com.lemonappdev.konsist.core.container.scope

import com.lemonappdev.konsist.api.container.scope.KoScope
import com.lemonappdev.konsist.api.container.scope.KoScopeCreator
import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.ext.sequence.withPackage
import com.lemonappdev.konsist.core.ext.isKotlinFile
import com.lemonappdev.konsist.core.ext.toKoFile
import com.lemonappdev.konsist.core.filesystem.PathProvider
import java.io.File

internal class KoScopeCreatorImpl : KoScopeCreator {
    private val pathProvider: PathProvider by lazy { PathProvider.getInstance() }

    private val projectKotlinFiles by lazy {

        File(pathProvider.rootProjectPath).toKoFiles()
    }

    override val projectRootPath = pathProvider.rootProjectPath

    override fun scopeFromProject(moduleName: String?, sourceSetName: String?, ignoreBuildConfig: Boolean): KoScope {
        val koFiles = getFiles(moduleName, sourceSetName, ignoreBuildConfig)
        return KoScopeImpl(koFiles)
    }

    override fun scopeFromModule(vararg moduleNames: String) = moduleNames
        .flatMap { getFiles(it) }
        .asSequence()
        .let { KoScopeImpl(it) }

    override fun scopeFromPackage(packagee: String, moduleName: String?, sourceSetName: String?): KoScope {
        val koFiles = getFiles(moduleName, sourceSetName)
            .withPackage(packagee)

        return KoScopeImpl(koFiles)
    }

    override fun scopeFromSourceSet(vararg sourceSetNames: String) = sourceSetNames
        .flatMap { getFiles(sourceSetName = it) }
        .asSequence()
        .let { KoScopeImpl(it) }

    private fun getFiles(
        moduleName: String? = null,
        sourceSetName: String? = null,
        ignoreBuildConfig: Boolean = true,
    ): Sequence<KoFile> {
        val localProjectKotlinFiles = projectKotlinFiles
            .filterNot { isBuildPath(it.path) }
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

        var pathPrefix = if (moduleName != null) {
            "$projectRootPath/$moduleName"
        } else {
            "$projectRootPath.*"
        }

        pathPrefix = if (sourceSetName != null) {
            "$pathPrefix/src/$sourceSetName/.*"
        } else {
            "$pathPrefix/src/.*"
        }

        return localProjectKotlinFiles
            .filter { it.path.matches(Regex(pathPrefix)) }
    }

    override fun scopeFromProduction(moduleName: String?, sourceSetName: String?): KoScope {
        sourceSetName?.let {
            require(!isTestSourceSet(it)) { "Source set '$it' is a test source set, but it should be production source set." }
        }

        val koFiles = getFiles(moduleName, sourceSetName).filterNot { it.isTestFile() }

        return KoScopeImpl(koFiles)
    }

    override fun scopeFromTest(moduleName: String?, sourceSetName: String?): KoScope {
        sourceSetName?.let {
            require(isTestSourceSet(it)) { "Source set '$it' is a production source set, but it should be test source set." }
        }

        val koFiles = getFiles(moduleName, sourceSetName).filter { it.isTestFile() }

        return KoScopeImpl(koFiles)
    }

    override fun scopeFromDirectory(path: String): KoScope {
        val directory = File(path)
        require(directory.exists()) { "Directory does not exist: $path" }
        require(!directory.isFile) { "Path is a file, but should be a directory: $path" }

        val files = directory.toKoFiles()

        return KoScopeImpl(files)
    }

    override fun scopeFromProjectDirectory(path: String): KoScope = scopeFromDirectory("$projectRootPath/$path")

    override fun scopeFromFile(path: String): KoScope {
        val file = File(path)

        require(file.exists()) { "File does not exist: $path" }
        require(file.isFile) { "Path is a directory, but should be a file: $path" }

        val koKoFile = file.toKoFile()

        return KoScopeImpl(koKoFile)
    }

    override fun scopeFromProjectFile(path: String): KoScope = scopeFromFile("$projectRootPath/$path")

    /**
     * Determines if the given path is a build directory "build" for Gradle and "target" for Maven.
     */
    private fun isBuildPath(path: String): Boolean {
        val gradleBuildDirectoryName = "build"
        val gradleRootBuildDirectoryRegex = Regex("$projectRootPath/$gradleBuildDirectoryName/.*")
        val gradleModuleBuildDirectoryRegex = Regex("$projectRootPath/.+/$gradleBuildDirectoryName/.*")

        val mavenBuildDirectoryName = "target"
        val mavenRootBuildDirectoryRegex = Regex("$projectRootPath/$mavenBuildDirectoryName/.*")
        val mavenModuleBuildDirectoryRegex = Regex("$projectRootPath/.+/$mavenBuildDirectoryName/.*")

        return path.matches(gradleRootBuildDirectoryRegex) ||
            path.matches(gradleModuleBuildDirectoryRegex) ||
            path.matches(mavenRootBuildDirectoryRegex) ||
            path.matches(mavenModuleBuildDirectoryRegex)
    }

    private fun isTestPath(path: String): Boolean {
        val lowercasePath = path.lowercase()
        return lowercasePath.contains("/$TEST_NAME_IN_PATH") || lowercasePath.contains("$TEST_NAME_IN_PATH/")
    }

    private fun isTestSourceSet(name: String): Boolean {
        val lowercaseName = name.lowercase()
        return lowercaseName.matches(Regex(".*$TEST_NAME_IN_PATH.*"))
    }

    private fun KoFile.isTestFile(): Boolean {
        val path = path.substringAfter(pathProvider.rootProjectPath)
        return isTestPath(path)
    }

    private fun KoFile.isBuildConfigFile(): Boolean {
        val lowercasePath = path.lowercase()
        val gradleBuildConfigDirectoryName = "buildSrc".lowercase()
        return lowercasePath.matches(Regex(".*/$gradleBuildConfigDirectoryName.*"))
    }

    private fun File.toKoFiles(): Sequence<KoFile> = walk()
        .filter { it.isKotlinFile }
        .map { it.toKoFile() }

    companion object {
        private const val TEST_NAME_IN_PATH = "test"
    }
}
