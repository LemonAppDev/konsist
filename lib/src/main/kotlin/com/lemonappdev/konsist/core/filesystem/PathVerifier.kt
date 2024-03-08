package com.lemonappdev.konsist.core.filesystem

import java.io.File

class PathVerifier {
    fun verifyPathIfExists(
        file: File,
        path: String,
    ) = File(file.absoluteFile, path).exists()
}
