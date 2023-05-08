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

    private val pathProvider by lazy {
        PathProvider(
            KoFileFactory(),
            ProjectRootDirProviderFactory(pathVerifier),
        )
    }

    private val projectKotlinFiles by lazy {
        pathProvider
            .rootProjectDirectory
            .walk()
            .filter { it.isKotlinFile }
            .map { it.toKoFile() }
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
        val koFiles = getFiles(module, sourceSet)
            .filterNot { isTestFile(it) }

        return KoScopeImpl(koFiles)
    }

    override fun scopeFromTest(module: String?, sourceSet: String?): KoScope {
        val koFiles = getFiles(module, sourceSet)
            .filter { isTestFile(it) }

        return KoScopeImpl(koFiles)
    }

    override fun scopeFromPackage(packagee: String, module: String?, sourceSet: String?): KoScope {
        val koFiles = getFiles(module, sourceSet)
            .withPackage(packagee)

        return KoScopeImpl(koFiles)
    }

    override fun scopeFromPath(path: String): KoScope {
        val file = File(path)
        require(file.exists()) { "Path does not exist: $path" }
        require(!file.isFile) { "Path is a file but should be a directory: $path" }
        return KoScopeImpl(file.toKoFile())
    }

    override fun scopeFromFile(path: String): KoScope {
        val file = File(path)

        require(file.exists()) { "File does not exist: $path" }
        require(file.isFile) { "Path is a directory, but should be a file: $path" }

        val koKoFile = file.toKoFile()

        return KoScopeImpl(koKoFile)
    }

    private fun isTestFile(it: KoFileDeclaration): Boolean {
        val path = it.filePath.lowercase()
        return path.contains("test/") || path.contains("/test")
    }
}
