package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.core.util.PathUtil.toMacOsSeparator

object ModuleUtil {
    internal const val ROOT_MODULE_NAME = "root"
    private const val GRADLE_ROOT_PROJECT_PATH = ":"

    /**
     * Returns module name with "/" separators (regardless of OS), e.g. "feature/data", or "root" for the top-level module.
     * Module of the file located outside the "src" directory is the file directory, e.g. "samples" for "/samples/A.kt".
     */
    fun getModuleName(projectPath: String): String {
        val path = toMacOsSeparator(projectPath)

        val moduleDirectory =
            if (path.contains("/src/")) {
                path.substringBefore("/src/")
            } else {
                path.substringBeforeLast("/", "")
            }

        return moduleDirectory
            .substringAfter("/")
            .ifEmpty { ROOT_MODULE_NAME }
    }

    /**
     * Converts module name provided by the user to the format returned by [getModuleName]
     * ("/" separators, no leading and trailing separators).
     *
     * Accepts Gradle project paths (e.g. ":feature:auth" or "feature:auth"), "/" and "\" separators,
     * e.g. ":feature:auth", "feature/auth" and "feature\auth" are all converted to "feature/auth".
     * Gradle root project path (":") is converted to "root". Blank module name is converted to an empty string,
     * which does not match any module.
     */
    internal fun normalizeModuleName(moduleName: String): String {
        if (moduleName.trim() == GRADLE_ROOT_PROJECT_PATH) {
            return ROOT_MODULE_NAME
        }

        return toMacOsSeparator(moduleName.trim())
            .replace(':', '/')
            .trim('/')
    }
}
