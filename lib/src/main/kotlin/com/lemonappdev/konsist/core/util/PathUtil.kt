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
     * "/app/src/main/kotlin/A.kt" and "/src/app/src/main/kotlin/A.kt" (see [getSourceDirectoryIndex]).
     */
    internal fun getSourceSetName(projectPath: String): String {
        val path = toMacOsSeparator(projectPath)
        val sourceDirectoryIndex = getSourceDirectoryIndex(path)

        if (sourceDirectoryIndex == -1) {
            return path.substringBefore('/')
        }

        return path
            .substring(sourceDirectoryIndex + SOURCE_DIRECTORY_PATH.length)
            .substringBefore('/')
    }

    /**
     * Returns index of the module "/src/" directory in [path] ("/" separators), or -1 if the path has no "src" directory.
     * Module may reside in a directory named "src" (e.g. "/src/app/src/main/kotlin/A.kt"), so the first "src" directory
     * followed by the source set directory and the "kotlin" or "java" directory is used. If there is no such directory,
     * the first "src" directory is used.
     */
    internal fun getSourceDirectoryIndex(path: String): Int =
        SOURCE_ROOT_REGEX.find(path)?.range?.first ?: path.indexOf(SOURCE_DIRECTORY_PATH)

    /**
     * Returns true if the file with given [projectPath] is located in the Gradle build configuration directory
     * (buildSrc), e.g. "/buildSrc/src/main/kotlin/A.kt". Directory name is compared case-insensitively.
     * Only directories outside the "src" directory are checked, so a "buildsrc" package is not a build configuration
     * directory.
     */
    internal fun isBuildConfigPath(projectPath: String): Boolean {
        val path = toMacOsSeparator(projectPath)
        val sourceDirectoryIndex = getSourceDirectoryIndex(path)

        val directoryPath =
            if (sourceDirectoryIndex == -1) {
                path.substringBeforeLast('/', "")
            } else {
                path.substring(0, sourceDirectoryIndex)
            }

        return directoryPath
            .split('/')
            .any { it.equals(GRADLE_BUILD_CONFIG_DIRECTORY_NAME, ignoreCase = true) }
    }

    private const val SOURCE_DIRECTORY_PATH = "/src/"
    private val SOURCE_ROOT_REGEX = Regex("/src/[^/]+/(?:kotlin|java)/")
    private const val GRADLE_BUILD_CONFIG_DIRECTORY_NAME = "buildSrc"
}
