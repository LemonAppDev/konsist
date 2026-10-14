package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.core.filesystem.ModuleDirectoryDetector
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.util.PathUtil.toMacOsSeparator
import java.util.concurrent.ConcurrentHashMap

object ModuleUtil {
    internal const val ROOT_MODULE_NAME = "root"
    private const val GRADLE_ROOT_PROJECT_PATH = ":"
    private const val WILDCARD = ".."
    private const val INVALID_WILDCARD = "..."
    private val sourceSetDirectoryRegex = Regex("/src/[^/]+")

    /*
     * Matchers are cached, because the same module name is usually checked for many declarations
     * (e.g. `withModule(":feature..")` or `residesInModule(":feature..")` in assertion), so its regex is compiled once.
     */
    private val moduleNameMatchers = ConcurrentHashMap<String, (String) -> Boolean>()

    /**
     * Returns module name with "/" separators (regardless of OS), e.g. "feature/data", or "root" for the top-level module.
     * Module may reside in a directory named "src", e.g. "src/app" for "/src/app/src/main/kotlin/A.kt"
     * (see [PathUtil.getSourceRootIndex]).
     *
     * Module of the file located outside the source root ("src/<sourceSet>/kotlin", "src/<sourceSet>/java" or
     * "src/<sourceSet>/resources") is the closest module directory containing the file (see [ModuleDirectoryDetector]),
     * e.g. "feature" for "/feature/config/A.kt" and "/feature/src/test/testData/A.kt", "src/tools" for "/src/tools/A.kt"
     * and "root" for "/samples/A.kt" (when "feature/config" and "samples" are not module directories). If there is no such directory,
     * module is the directory containing the first "src" directory (see [PathUtil.getSourceDirectoryIndex]) or,
     * for the file located outside the "src" directory, the file directory.
     * Project located in the source set directory of the module (e.g. Gradle test fixture project
     * "/app/src/test/testData/project") is not a module, so its files reside in the module containing the source set,
     * e.g. "app" for "/app/src/test/testData/project/src/main/kotlin/A.kt".
     *
     * [isSourceSetDirectory] returns true if the directory with given path (relative to the project root,
     * e.g. "/app/src/test") is a source set directory (see [ModuleDirectoryDetector.isSourceSetDirectory]).
     * [getModuleDirectory] returns module directory (see [ModuleDirectoryDetector.getModuleDirectory]) containing
     * the directory with given path (relative to the project root, e.g. "/feature/config", or "" for the project root
     * directory), or null if there is no such directory.
     */
    fun getModuleName(
        projectPath: String,
        isSourceSetDirectory: (String) -> Boolean = ::isProjectSourceSetDirectory,
        getModuleDirectory: (String) -> String? = ::getProjectModuleDirectory,
    ): String =
        getModuleDirectoryPath(projectPath, isSourceSetDirectory, getModuleDirectory)
            .substringAfter("/")
            .ifEmpty { ROOT_MODULE_NAME }

    /**
     * Returns path of the module directory (see [getModuleName]) relative to the project root with "/" separators,
     * e.g. "/feature/data", or "" for the top-level module.
     */
    internal fun getModuleDirectoryPath(
        projectPath: String,
        isSourceSetDirectory: (String) -> Boolean = ::isProjectSourceSetDirectory,
        getModuleDirectory: (String) -> String? = ::getProjectModuleDirectory,
    ): String {
        val moduleDirectoryPath = getClosestModuleDirectoryPath(toMacOsSeparator(projectPath), getModuleDirectory)
        return getOuterModuleDirectoryPath(moduleDirectoryPath, isSourceSetDirectory) ?: moduleDirectoryPath
    }

    private fun getClosestModuleDirectoryPath(
        path: String,
        getModuleDirectory: (String) -> String?,
    ): String {
        val sourceRootIndex = PathUtil.getSourceRootIndex(path)

        if (sourceRootIndex != -1) {
            return path.substring(0, sourceRootIndex)
        }

        val fileDirectory = path.substringBeforeLast("/", "")
        return getModuleDirectory(fileDirectory) ?: getDefaultModuleDirectory(path, fileDirectory)
    }

    /*
     * Returns path of the module containing the source set directory in which the module directory with given
     * [moduleDirectoryPath] is located, e.g. "/app" for "/app/src/test/testData/project" (when "/app/src/test" is
     * a source set directory), or null if the module directory is not located in a source set directory.
     * Module located in a directory named "src" (e.g. "/src/app") is not located in a source set directory,
     * because "/src/app" does not contain the "kotlin", "java" or "resources" directory.
     */
    private fun getOuterModuleDirectoryPath(
        moduleDirectoryPath: String,
        isSourceSetDirectory: (String) -> Boolean,
    ): String? =
        sourceSetDirectoryRegex
            .findAll(moduleDirectoryPath)
            .firstOrNull { isSourceSetDirectory(moduleDirectoryPath.substring(0, it.range.last + 1)) }
            ?.let { moduleDirectoryPath.substring(0, it.range.first) }

    private fun getProjectModuleDirectory(directoryPath: String): String? =
        ModuleDirectoryDetector.getModuleDirectory(PathProvider.rootProjectPath, directoryPath)

    private fun isProjectSourceSetDirectory(directoryPath: String): Boolean =
        ModuleDirectoryDetector.isSourceSetDirectory(PathProvider.rootProjectPath, directoryPath)

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
     *
     * @throws IllegalArgumentException if [name] is invalid (see [getModuleNameMatcher]).
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
     * Predicate is built once per module name and cached, so it can be used to check many files and declarations.
     *
     * @throws IllegalArgumentException if [name] contains more than two consecutive dots (e.g. ":feature..."),
     * because such name is ambiguous and would not match any module.
     */
    internal fun getModuleNameMatcher(name: String): (String) -> Boolean =
        moduleNameMatchers.getOrPut(name) { createModuleNameMatcher(name) }

    private fun createModuleNameMatcher(name: String): (String) -> Boolean {
        val normalizedName = normalizeModuleName(name)

        require(!normalizedName.contains(INVALID_WILDCARD)) {
            "Module name '$name' contains more than two consecutive dots. Use '$WILDCARD' as a wildcard " +
                "for any number of module directories, e.g. ':feature$WILDCARD' or ':feature$WILDCARD:data'."
        }

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
