package com.lemonappdev.konsist.core.filesystem

import com.lemonappdev.konsist.core.exception.KoInternalException
import java.io.File

object PathProvider {
    private val koFileFactory = KoFileFactory()
    private val pathVerifier = PathVerifier()
    private val projectRootDirProviderFactory = ProjectRootDirProviderFactory(pathVerifier)

    val rootProjectPath: String by lazy {
        val file = koFileFactory.create("")

        val projectRootDirectory = getProjectRootDirectory(file)

        if (projectRootDirectory == null) {
            val message = "Project directory not found. Searched in ${file.absoluteFile.path} and parent directories"
            throw KoInternalException(message)
        } else {
            projectRootDirectory.absoluteFile.path
        }
    }

    private fun getProjectRootDirectory(file: File?): File? {
        if (file == null) {
            return null
        }

        return projectRootDirProviderFactory
            .create()
            .map { it.getProjectRootDir(file) }
            .firstOrNull { it != null }
            ?: getProjectRootDirectory(file.absoluteFile.parentFile)
    }
}
