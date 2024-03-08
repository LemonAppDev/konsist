package com.lemonappdev.konsist.core.util

import java.io.File

object PathUtil {
    val separator: String = File.separator

    fun toOsSeparator(path: String): String =
        path
            .replace("/", File.separator)
            .replace("\\", File.separator)

    fun toOsSeparator(paths: List<String>): List<String> =
        paths
            .map {
                it.replace("/", File.separator)
                    .replace("\\", File.separator)
            }

    fun toMacOsSeparator(path: String): String = path.replace("\\", "/")
}
