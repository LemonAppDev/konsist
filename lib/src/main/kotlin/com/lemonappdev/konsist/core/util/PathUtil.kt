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
     * Returns name of the source set containing the file with given [projectPath] - the directory located in the "src"
     * directory of the module directory with given [moduleDirectoryPath] (see [ModuleUtil.getModuleDirectoryPath]),
     * e.g. "main" for "/app/src/main/kotlin/A.kt" and "/src/app/src/main/kotlin/A.kt", and "test" for
     * "/app/src/test/resources/A.kt". Returns an empty string for the file located outside the module "src" directory
     * (e.g. "/app/A.kt") and the file located directly in the module "src" directory (e.g. "/app/src/A.kt").
     */
    internal fun getSourceSetName(
        projectPath: String,
        moduleDirectoryPath: String = ModuleUtil.getModuleDirectoryPath(projectPath),
    ): String {
        val path = toMacOsSeparator(projectPath)
        val moduleSourceDirectoryPath = "$moduleDirectoryPath$SOURCE_DIRECTORY_PATH"

        if (!path.startsWith(moduleSourceDirectoryPath)) {
            return ""
        }

        val sourceSetPath = path.removePrefix(moduleSourceDirectoryPath)

        // Source set is a directory, so the file located directly in the "src" directory has no source set
        return if (sourceSetPath.contains('/')) sourceSetPath.substringBefore('/') else ""
    }

    /**
     * Returns index of the module "/src/" directory in [path] ("/" separators), or -1 if the path has no "src" directory.
     * Module may reside in a directory named "src" (e.g. "/src/app/src/main/kotlin/A.kt"), so the first "src" directory
     * followed by the source set directory and the "kotlin", "java" or "resources" directory is used. If there is no such
     * directory, the first "src" directory is used.
     */
    internal fun getSourceDirectoryIndex(path: String): Int =
        getSourceRootIndex(path).takeIf { it != -1 } ?: path.indexOf(SOURCE_DIRECTORY_PATH)

    /**
     * Returns index of the "/src/" directory of the first source root ("/src/<sourceSet>/kotlin/",
     * "/src/<sourceSet>/java/" or "/src/<sourceSet>/resources/") in [path] ("/" separators), or -1 if the path has
     * no source root, e.g. 4 for "/app/src/main/kotlin/A.kt" and "/app/src/test/resources/A.kt", and -1 for
     * "/app/src/test/testData/A.kt".
     * Resources are a source root, so a project located in resources (e.g. Gradle test fixture project
     * "/app/src/test/resources/project/build.gradle.kts") is not a module.
     */
    internal fun getSourceRootIndex(path: String): Int = SOURCE_ROOT_REGEX.find(path)?.range?.first ?: -1

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
    private val SOURCE_ROOT_REGEX = Regex("/src/[^/]+/(?:kotlin|java|resources)/")
    private const val GRADLE_BUILD_CONFIG_DIRECTORY_NAME = "buildSrc"
}
