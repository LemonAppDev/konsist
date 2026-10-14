package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.core.util.PathUtil.toMacOsSeparator

object ModuleUtil {
    internal const val ROOT_MODULE_NAME = "root"
    private const val GRADLE_ROOT_PROJECT_PATH = ":"
    private const val WILDCARD = ".."

    /**
     * Returns module name with "/" separators (regardless of OS), e.g. "feature/data", or "root" for the top-level module.
     * Module of the file located outside the "src" directory is the file directory, e.g. "samples" for "/samples/A.kt".
     * Module may reside in a directory named "src", e.g. "src/app" for "/src/app/src/main/kotlin/A.kt"
     * (see [PathUtil.getSourceDirectoryIndex]).
     */
    fun getModuleName(projectPath: String): String {
        val path = toMacOsSeparator(projectPath)
        val sourceDirectoryIndex = PathUtil.getSourceDirectoryIndex(path)

        val moduleDirectory =
            if (sourceDirectoryIndex == -1) {
                path.substringBeforeLast("/", "")
            } else {
                path.substring(0, sourceDirectoryIndex)
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

    /**
     * Returns true if module with given [moduleName] (format returned by [getModuleName]) matches module name [name]
     * provided by the user (any format accepted by [normalizeModuleName]).
     */
    internal fun residesInModule(
        moduleName: String,
        name: String,
    ): Boolean = getModuleNameMatcher(name)(moduleName)

    /**
     * Returns predicate checking if module name (format returned by [getModuleName]) matches module name [name]
     * provided by the user (any format accepted by [normalizeModuleName]).
     *
     * Use '..' as a wildcard for any number of module directories, including none, e.g. ":feature.." matches
     * "feature", "feature/payment" and "feature/payment/api", "..data" matches "data" and "feature/data",
     * and ".." matches all modules. Module name without wildcard must match exactly.
     * Predicate is built once, so it can be used to check many files.
     */
    internal fun getModuleNameMatcher(name: String): (String) -> Boolean {
        val normalizedName = normalizeModuleName(name)

        if (!normalizedName.contains(WILDCARD)) {
            return { it == normalizedName }
        }

        val regex = getModuleNameRegex(normalizedName)
        return { "/$it/".matches(regex) }
    }

    /*
     * Each module directory is matched with a trailing '/' and wildcard matches any number of such directories,
     * e.g. "feature/.." -> "/feature/(?:[^/]+/)*" matches "/feature/" and "/feature/payment/".
     */
    private fun getModuleNameRegex(normalizedName: String): Regex {
        val segments =
            normalizedName
                .replace(WILDCARD, "/$WILDCARD/")
                .split('/')
                .filter { it.isNotEmpty() }

        val pattern =
            buildString {
                append('/')
                segments.forEach { segment ->
                    if (segment == WILDCARD) {
                        append("(?:[^/]+/)*")
                    } else {
                        append(Regex.escape(segment))
                        append('/')
                    }
                }
            }

        return Regex(pattern)
    }
}
