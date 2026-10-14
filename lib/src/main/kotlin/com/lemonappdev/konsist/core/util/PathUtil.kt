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
                it
                    .replace("/", File.separator)
                    .replace("\\", File.separator)
            }

    fun toMacOsSeparator(path: String): String = path.replace("\\", "/")

    /**
     * Returns [path] relative to [rootProjectPath], starting with a separator, e.g. "/app/src/main/kotlin/A.kt".
     * Trailing separator of [rootProjectPath] is ignored, so drive roots (e.g. "X:\") and "/" work like other paths.
     */
    internal fun getProjectPath(
        path: String,
        rootProjectPath: String,
    ): String = path.removePrefix(toOsSeparator(rootProjectPath).trimEnd(File.separatorChar))

    /**
     * Returns name of the source set containing the file with given [projectPath], e.g. "main" for
     * "/app/src/main/kotlin/A.kt".
     */
    internal fun getSourceSetName(projectPath: String): String =
        projectPath
            .substringAfter("${File.separator}src${File.separator}")
            .substringBefore(File.separator)
}
