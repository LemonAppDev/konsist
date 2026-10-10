package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.core.util.PathUtil.toMacOsSeparator

object ModuleUtil {
    internal const val ROOT_MODULE_NAME = "root"
    private const val GRADLE_ROOT_PROJECT_PATH = ":"

    /**
     * Returns module name with "/" separators (regardless of OS), e.g. "feature/data", or "root" for the top-level module.
     */
    fun getModuleName(
        projectPath: String,
        rootProjectPath: String,
    ): String {
        val projectName =
            toMacOsSeparator(rootProjectPath)
                .substringAfterLast("/")

        val moduleName =
            toMacOsSeparator(projectPath)
                .substringBefore("/src/")
                .substringAfter("/")

        return if (moduleName == projectName || moduleName == "") {
            ROOT_MODULE_NAME
        } else {
            moduleName
        }
    }

    /**
     * Converts module name provided by the user to the format returned by [getModuleName]
     * ("/" separators, no leading and trailing separators).
     *
     * Accepts Gradle project paths (e.g. ":feature:auth" or "feature:auth"), "/" and "\" separators,
     * e.g. ":feature:auth", "feature/auth" and "feature\auth" are all converted to "feature/auth".
     * Gradle root project path (":") is converted to "root".
     *
     * @throws IllegalArgumentException when the module name is blank.
     */
    internal fun normalizeModuleName(moduleName: String): String {
        if (moduleName.trim() == GRADLE_ROOT_PROJECT_PATH) {
            return ROOT_MODULE_NAME
        }

        val normalizedModuleName =
            toMacOsSeparator(moduleName.trim())
                .replace(':', '/')
                .trim('/')

        require(normalizedModuleName.isNotBlank()) { "Module name is blank: '$moduleName'" }

        return normalizedModuleName
    }
}
