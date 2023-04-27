package com.lemonappdev.konsist.core.filesystem

import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.filesystem.rootprovider.GitProjectRootDirProvider
import com.lemonappdev.konsist.core.filesystem.rootprovider.GradleProjectRootDirProvider
import com.lemonappdev.konsist.core.filesystem.rootprovider.MavenProjectRootDirProvider
import java.io.File

class PathProvider(
    private val koFileFactory: KoFileFactory,
    private val projectRootDirProviderFactory: ProjectRootDirProviderFactory,
) {

    val rootProjectPath: String by lazy { rootProjectDirectory.absoluteFile.path }

    val rootProjectDirectory: File by lazy {
        val file = koFileFactory.create("")

        val projectRootDirectory = getProjectRootDirectory(file)

        if (projectRootDirectory == null) {
            val message = "Project directory not found. Searched in ${file.absoluteFile.path} and parent directories"
            throw KoInternalException(message)
        } else {
            projectRootDirectory
        }
    }

    private fun getProjectRootDirectory(file: File?): File? {
        if (file == null) {
            return null
        }

        return projectRootDirProviderFactory.create()
            .map { it.getDir(file) }
            .firstOrNull()
            ?: getProjectRootDirectory(file.absoluteFile.parentFile)
    }
}

class ProjectRootDirProviderFactory(
    pathVerifier: PathVerifier,
) {
    private val providers = listOf(
        GradleProjectRootDirProvider(pathVerifier),
        MavenProjectRootDirProvider(pathVerifier),
        GitProjectRootDirProvider(pathVerifier),
    )

    fun create() = providers
}
