package com.lemonappdev.konsist.core.util

import java.io.File

object PathUtil {
    fun toOsSeparator(path: String): String =
        path
            .replace("/", File.separator)
            .replace("\\", File.separator)

    fun toMacOsSeparator(path: String): String = path.replace("\\", "/")
}
