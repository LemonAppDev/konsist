package com.lemonappdev.konsist.core.filesystem

import java.io.File

class KoFileFactory {
    fun create(path: String) = File(path)
}
