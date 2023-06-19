package com.lemonappdev.konsist.core.container.koscope

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.container.koscope.KoScope
import com.lemonappdev.konsist.api.container.koscope.KoScopeCreator
import com.lemonappdev.konsist.api.ext.sequence.withPackage
import com.lemonappdev.konsist.core.ext.isKotlinFile
import com.lemonappdev.konsist.core.ext.isKotlinSnippetFile
import com.lemonappdev.konsist.core.ext.sep
import com.lemonappdev.konsist.core.ext.toKoFile
import com.lemonappdev.konsist.core.ext.toMacOsSeparator
import com.lemonappdev.konsist.core.filesystem.PathProvider
import java.io.File

internal class KoScopeCreatorImpl : KoScopeCreator {
    private val pathProvider: PathProvider by lazy { PathProvider.getInstance() }

    private val projectKotlinFiles: Sequence<KoFile> by lazy { File(pathProvider.rootProjectPath).toKoFiles() }

    private val projectKttxtFiles: Sequence<KoFile> by lazy { File(pathProvider.rootProjectPath).toKoFilesFromKttxt() }

    override val projectRootPath: String by lazy { pathProvider.rootProjectPath }

    override fun scopeFromProject(moduleName: String?, sourceSetName: String?, ignoreBuildConfig: Boolean): KoScope {
        val koFiles = getFiles(moduleName, sourceSetName, ignoreBuildConfig)
        return KoScopeImpl(koFiles)
    }

    override fun scopeFromModule(vararg moduleNames: String): KoScope = moduleNames
        .flatMap { getFiles(it) }
        .asSequence()
        .let { KoScopeImpl(it) }

    override fun scopeFromPackage(packagee: String, moduleName: String?, sourceSetName: String?): KoScope {
        val koFiles = getFiles(moduleName, sourceSetName)
            .withPackage(packagee)

        return KoScopeImpl(koFiles)
    }

    override fun scopeFromSourceSet(vararg sourceSetNames: String): KoScope = sourceSetNames
        .flatMap { getFiles(sourceSetName = it) }
        .asSequence()
        .let { KoScopeImpl(it) }

    private fun getFiles(
        moduleName: String? = null,
        sourceSetName: String? = null,
        ignoreBuildConfig: Boolean = true,
    ): Sequence<KoFile> {
        val localProjectKotlinFiles = projectKotlinFiles
            .filterNot { isBuildPath(it.path.toMacOsSeparator()) }
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

        return KoScopeImpl(koFiles)
    }

    override fun scopeFromTest(moduleName: String?, sourceSetName: String?): KoScope {
        sourceSetName?.let {
            require(isTestSourceSet(it)) { "Source set '$it' is a production source set, but it should be test source set." }
        }

        val koFiles = getFiles(moduleName, sourceSetName)
            .filter { isTestSourceSet(it.sourceSetName) }

        return KoScopeImpl(koFiles)
    }

    override fun scopeFromDirectory(path: String): KoScope {
        val absolutePath = "$projectRootPath$sep$path"
            .replace("$sep, $sep", sep) // if given path starts with file separator `absolutePath` contains `//`

        val files = projectKotlinFiles
            .filter { it.path.startsWith(absolutePath) }

        require(files.toList().isNotEmpty()) { "Directory does not exist: $absolutePath" }

        return KoScopeImpl(files)
    }

    override fun scopeFromFile(path: String): KoScope {
        val absolutePath = "$projectRootPath$sep$path"
            .replace("kttxt", "kt")
            .replace("$sep$sep", sep) // if given path starts with file separator `absolutePath` contains `//`

        val koFile = projectKttxtFiles
            .firstOrNull { it.path == absolutePath }

        require(koFile != null) { "File does not exist: $absolutePath" }

        return KoScopeImpl(koFile)
    }

    /**
     * Determines if the given path is a build directory "build" for Gradle and "target" for Maven.
     */
    private fun isBuildPath(path: String): Boolean {
        val gradleBuildDirectoryName = "build"
        val gradleRootBuildDirectoryRegex = Regex("$projectRootPath/$gradleBuildDirectoryName/.*".toMacOsSeparator())
        val gradleModuleBuildDirectoryRegex = Regex("$projectRootPath/.+/$gradleBuildDirectoryName/.*".toMacOsSeparator())

        val mavenBuildDirectoryName = "target"
        val mavenRootBuildDirectoryRegex = Regex("$projectRootPath/$mavenBuildDirectoryName/.*".toMacOsSeparator())
        val mavenModuleBuildDirectoryRegex = Regex("$projectRootPath/.+/$mavenBuildDirectoryName/.*".toMacOsSeparator())

        return path.matches(gradleRootBuildDirectoryRegex) ||
            path.matches(gradleModuleBuildDirectoryRegex) ||
            path.matches(mavenRootBuildDirectoryRegex) ||
            path.matches(mavenModuleBuildDirectoryRegex)
    }

    private fun isTestSourceSet(name: String): Boolean {
        val lowercaseName = name.lowercase()
        return lowercaseName.substringAfter(':').matches(Regex(".*$TEST_NAME_IN_PATH.*"))
    }

    private fun KoFile.isBuildConfigFile(): Boolean {
        val lowercasePath = path.lowercase().toMacOsSeparator()
        val gradleBuildConfigDirectoryName = "buildSrc".lowercase()
        return lowercasePath.matches(Regex(".*/$gradleBuildConfigDirectoryName.*"))
    }

    private fun File.toKoFiles(): Sequence<KoFile> = walk()
        .filter { it.isKotlinFile }
        .map { it.toKoFile() }

    private fun File.toKoFilesFromKttxt(): Sequence<KoFile> = walk()
        .filter { it.isKotlinSnippetFile }
        .map { it.toKoFile() }

    companion object {
        private const val TEST_NAME_IN_PATH = "test"
        private const val ROOT_MODULE_NAME = "root"
    }
}
