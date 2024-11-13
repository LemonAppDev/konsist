package com.lemonappdev.konsist.core.util

import java.io.File

object HyperlinkUtil {
    private const val FILE_PREFIX = "file://"

    fun toHyperlink(path: String): String {
        val absolutePath = File(path).absolutePath
        return if (!absolutePath.startsWith(FILE_PREFIX)) {
            "$FILE_PREFIX$absolutePath"
        } else {
            absolutePath
        }
    }
}
