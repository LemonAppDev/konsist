package com.lemonappdev.konsist.core.filesystem.rootprovider

import com.lemonappdev.konsist.core.filesystem.PathVerifier
import java.io.File

abstract class ProjectRootDirResolver(
    private val pathVerifier: PathVerifier,
) {
    abstract val paths: Set<String>

    fun getDir(file: File): File? {
        val allExist = paths.all { pathVerifier.verifyPathIfExists(file, it) }

        return if (allExist) {
            file
        } else {
            null
        }
    }
}
