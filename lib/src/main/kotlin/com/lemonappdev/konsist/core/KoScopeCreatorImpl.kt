package com.lemonappdev.konsist.core

import com.lemonappdev.konsist.api.KoScope
import com.lemonappdev.konsist.api.KoScopeCreator
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.sequence.withPackage
import com.lemonappdev.konsist.core.ext.isKotlinFile
import com.lemonappdev.konsist.core.ext.toKoFile
import com.lemonappdev.konsist.core.filesystem.KoFileFactory
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.filesystem.PathVerifier
import com.lemonappdev.konsist.core.filesystem.ProjectRootDirProviderFactory
import com.lemonappdev.konsist.core.scope.KoScopeImpl
import java.io.File

internal class KoScopeCreatorImpl : KoScopeCreator {
    private val pathVerifier = PathVerifier()

    private val pathProvider: PathProvider by lazy {
        PathProvider(
            KoFileFactory(),
            ProjectRootDirProviderFactory(pathVerifier),
        )
    }

    private val projectKotlinFiles by lazy {
        pathProvider
            .rootProjectDirectory
            .toKoFiles()
    }

    override fun scopeFromProject(module: String?, sourceSet: String?): KoScope {
        val koFiles = getFiles(module, sourceSet)
        return KoScopeImpl(koFiles)
    }

    private fun getFiles(module: String?, sourceSet: String?): Sequence<KoFileDeclaration> {
        if (module == null && sourceSet == null) {
            return projectKotlinFiles
        }

        var pathPrefix = if (module != null) {
            "${pathProvider.rootProjectPath}/${module.lowercase()}"
        } else {
            "${pathProvider.rootProjectPath}/.*"
        }

        pathPrefix = if (sourceSet != null) {
            "$pathPrefix/src/$sourceSet"
        } else {
            "$pathPrefix/src/.*"
        }

        // Dot means any character, asterisk means occur any number of times
        pathPrefix += "/.*"

        return projectKotlinFiles
            .filter { it.filePath.matches(Regex(pathPrefix)) }
    }

    override fun scopeFromProduction(module: String?, sourceSet: String?): KoScope {
        sourceSet?.let {
            require(!isTestSourceSetName(it)) { "Source set '$it' is a test source set, but it should be production source set." }
        }

        val koFiles = getFiles(module, sourceSet).filterNot { it.isTestFile() }

        return KoScopeImpl(koFiles)
    }

    override fun scopeFromTest(module: String?, sourceSet: String?): KoScope {
        sourceSet?.let {
            require(!isTestSourceSetName(it)) { "Source set '$it' is a production source set, but it should be test source set." }
        }

        val koFiles = getFiles(module, sourceSet).filter { it.isTestFile() }

        return KoScopeImpl(koFiles)
    }

    override fun scopeFromPackage(packagee: String, module: String?, sourceSet: String?): KoScope {
        val koFiles = getFiles(module, sourceSet)
            .withPackage(packagee)

        return KoScopeImpl(koFiles)
    }

    override fun scopeFromDirectory(path: String): KoScope {
        val directory = File(path)
        require(directory.exists()) { "Directory does not exist: $path" }
        require(!directory.isFile) { "Path is a file, but should be a directory: $path" }

        val files = directory.toKoFiles()

        return KoScopeImpl(files)
    }

    override fun scopeFromFile(path: String): KoScope {
        val file = File(path)

        require(file.exists()) { "File does not exist: $path" }
        require(file.isFile) { "Path is a directory, but should be a file: $path" }

        val koKoFile = file.toKoFile()

        return KoScopeImpl(koKoFile)
    }

    private fun isTestPath(path: String): Boolean {
        val localPath = path.lowercase()
        return localPath.contains("/$TEST_NAME_IN_PATH") || localPath.contains("$TEST_NAME_IN_PATH/")
    }

    private fun isTestSourceSetName(path: String): Boolean {
        val localPath = path.lowercase()
        return localPath.contains(TEST_NAME_IN_PATH) || localPath.contains(TEST_NAME_IN_PATH)
    }

    private fun KoFileDeclaration.isTestFile(): Boolean {
        val path = filePath.substringAfter(pathProvider.rootProjectPath)
        return isTestPath(path)
    }

    private fun File.toKoFiles() = walk()
        .filter { it.isKotlinFile }
        .map { it.toKoFile() }

    companion object {
        private const val TEST_NAME_IN_PATH = "test"
    }
}
