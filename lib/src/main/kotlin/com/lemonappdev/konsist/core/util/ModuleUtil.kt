package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.core.filesystem.ModuleDirectoryDetector
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.util.PathUtil.toMacOsSeparator

object ModuleUtil {
    internal const val ROOT_MODULE_NAME = "root"
    private const val GRADLE_ROOT_PROJECT_PATH = ":"
    private const val WILDCARD = ".."

    /**
     * Returns module name with "/" separators (regardless of OS), e.g. "feature/data", or "root" for the top-level module.
     * Module may reside in a directory named "src", e.g. "src/app" for "/src/app/src/main/kotlin/A.kt"
     * (see [PathUtil.getSourceRootIndex]).
     *
     * Module of the file located outside the source root ("src/<sourceSet>/kotlin" or "src/<sourceSet>/java") is
     * the closest module directory containing the file (see [ModuleDirectoryDetector]), e.g. "feature" for
     * "/feature/config/A.kt" and "/feature/src/test/resources/A.kt", "src/tools" for "/src/tools/A.kt" and "root" for
     * "/samples/A.kt" (when "feature/config" and "samples" are not module directories). If there is no such directory,
     * module is the directory containing the first "src" directory (see [PathUtil.getSourceDirectoryIndex]) or,
     * for the file located outside the "src" directory, the file directory.
     *
     * [getModuleDirectory] returns module directory (see [ModuleDirectoryDetector.getModuleDirectory]) containing
     * the directory with given path (relative to the project root, e.g. "/feature/config", or "" for the project root
     * directory), or null if there is no such directory.
     */
    fun getModuleName(
        projectPath: String,
        getModuleDirectory: (String) -> String? = ::getProjectModuleDirectory,
    ): String =
        getModuleDirectoryPath(projectPath, getModuleDirectory)
            .substringAfter("/")
            .ifEmpty { ROOT_MODULE_NAME }

    /**
     * Returns path of the module directory (see [getModuleName]) relative to the project root with "/" separators,
     * e.g. "/feature/data", or "" for the top-level module.
     */
    internal fun getModuleDirectoryPath(
        projectPath: String,
        getModuleDirectory: (String) -> String? = ::getProjectModuleDirectory,
    ): String {
        val path = toMacOsSeparator(projectPath)
        val sourceRootIndex = PathUtil.getSourceRootIndex(path)

        if (sourceRootIndex != -1) {
            return path.substring(0, sourceRootIndex)
        }

        val fileDirectory = path.substringBeforeLast("/", "")
        return getModuleDirectory(fileDirectory) ?: getDefaultModuleDirectory(path, fileDirectory)
    }

    private fun getProjectModuleDirectory(directoryPath: String): String? =
        ModuleDirectoryDetector.getModuleDirectory(PathProvider.rootProjectPath, directoryPath)

    /*
     * Directory containing the first "src" directory, or the file directory for the file located outside the "src"
     * directory, e.g. "/app" for "/app/src/test/resources/A.kt" and "/samples" for "/samples/A.kt".
     */
    private fun getDefaultModuleDirectory(
        path: String,
        fileDirectory: String,
    ): String {
        val sourceDirectoryIndex = PathUtil.getSourceDirectoryIndex(path)

        return if (sourceDirectoryIndex == -1) fileDirectory else path.substring(0, sourceDirectoryIndex)
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
