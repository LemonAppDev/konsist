package com.lemonappdev.konsist.core.filesystem

import com.lemonappdev.konsist.core.exception.KoInternalException
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

    companion object {
        private val pathVerifier = PathVerifier()

        internal fun getInstance() = PathProvider(KoFileFactory(), ProjectRootDirProviderFactory(pathVerifier))
    }
}
