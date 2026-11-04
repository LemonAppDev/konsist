package com.lemonappdev.konsist.core.filesystem.rootprovider

import com.lemonappdev.konsist.core.filesystem.PathVerifier
import java.io.File

abstract class ProjectRootDirResolver(
    protected val pathVerifier: PathVerifier,
) {
    abstract val paths: Set<String>

    open fun getProjectRootDir(file: File): File? {
        val allExist = paths.any { pathVerifier.verifyPathIfExists(file, it) }

        return if (allExist) {
            file
        } else {
            null
        }
    }
}
