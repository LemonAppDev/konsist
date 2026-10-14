package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about its module.
 */
interface KoModuleProvider : KoBaseProvider {
    /**
     * The declaration's module name - module directory path relative to the project root (not Gradle project path).
     * Uses `/` separators on all OSes, e.g. "feature/data", or "root" for the top-level module.
     * For the file located outside the source root (`src/<sourceSet>/kotlin` or `src/<sourceSet>/java`), it is
     * the closest directory containing the file that contains a Gradle or Maven build file (e.g. `build.gradle.kts`,
     * `settings.gradle.kts`, `pom.xml`) or the `src` directory, e.g. "feature" for `feature/config/A.kt` and
     * `feature/src/test/resources/A.kt`, and "root" for `samples/A.kt` (when `feature/config` and `samples` are
     * not module directories). If there is no such directory, it is the directory containing the first `src` directory
     * or, for the file located outside the `src` directory, the file directory path.
     * Module may reside in a directory named `src`, e.g. "src/app" for `src/app/src/main/kotlin/A.kt`
     * (the first `src` directory followed by the source set directory and the `kotlin` or `java` directory is used).
     */
    val moduleName: String

    /**
     * Determines whether the declaration resides in the specified module.
     *
     * @param name The name of the module to check. If this is the top-level module, use "root" (or ":").
     * Gradle project path (e.g. `:feature:auth`) is the preferred format, but `feature/auth` and `feature\auth`
     * are also accepted on all OSes.
     * Module name is the module directory path, so Gradle project path works only when it matches the directory
     * (Gradle `projectDir` overrides in settings.gradle(.kts) are not supported).
     * Use `..` as a wildcard for any number of module directories, including none, e.g. `:feature..` matches
     * `feature` and its nested modules (`feature/payment`), `..data` matches `data` and `feature/data`,
     * and `..` matches all modules.
     * @return `true` if the declaration resides in the specified module, `false` otherwise.
     */
    fun residesInModule(name: String): Boolean

    /**
     * Determines whether the declaration resides in the specified module.
     *
     * @param name The name of the module to check. If this is the top-level module, use "root" (or ":").
     * @return `true` if the declaration resides in the specified module, `false` otherwise.
     */
    @Deprecated("Will be removed in version 1.0.0-rc1", ReplaceWith("residesInModule(name)"))
    fun resideInModule(name: String): Boolean
}
