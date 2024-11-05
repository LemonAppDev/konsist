package com.lemonappdev.konsist.core.util

import java.io.File

object HyperlinkUtil {
    fun toHyperlink(path: String): String {
        val absolutePath = File(path).absolutePath
        return "file://$absolutePath"
    }
}
