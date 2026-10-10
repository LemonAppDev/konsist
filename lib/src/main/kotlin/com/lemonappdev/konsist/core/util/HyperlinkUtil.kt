package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.core.util.PathUtil.toMacOsSeparator
import java.io.File

object HyperlinkUtil {
    private const val FILE_PREFIX = "file://"

    /**
     * Returns hyperlink clickable in IntelliJ console on all OSes, e.g. "file:///C:/project/src/Sample.kt:3:1" on Windows
     * and "file:///project/src/Sample.kt:3:1" on Unix. IntelliJ requires "file:///" and "/" separators.
     */
    fun toHyperlink(path: String): String {
        val pathWithoutFilePrefix = path.removePrefix(FILE_PREFIX)

        return toFileUrl(File(pathWithoutFilePrefix).absolutePath)
    }

    internal fun toFileUrl(absolutePath: String): String = "$FILE_PREFIX/${toMacOsSeparator(absolutePath).removePrefix("/")}"
}
