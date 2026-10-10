package com.lemonappdev.konsist.api.container

/**
 * Scope creator.
 *
 * Creates a [KoScope] instance from a given set of files, such as all project files, a single module, a path, etc.
 *
 */
interface KoScopeCreator {
    /**
     * The path to the project root directory.
     * Uses OS-specific separators (`\` on Windows, `/` on other OSes).
     */
    val projectRootPath: String

    /**
     * Creates a [KoScope] containing all Kotlin files in the project.
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param moduleName The name of the module. If null, all modules will be included.
     * Gradle project path (e.g. `:feature:auth`) is the preferred format, but `feature/auth` and `feature\auth`
     * are also accepted on all OSes. Use `root` (or `:`) for the top-level module. Module names are case-sensitive.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @param ignoreBuildConfig If true, build config files and directories such as Gradle buildSrc directory will be ignored.
     * @return a [KoScope] containing all Kotlin files in the project.
     */
    fun scopeFromProject(
        moduleName: String? = null,
        sourceSetName: String? = null,
        ignoreBuildConfig: Boolean = true,
    ): KoScope

    /**
     * Creates a [KoScope] containing all Kotlin files in the module.
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param moduleName The name of the module.
     * Gradle project path (e.g. `:feature:auth`) is the preferred format, but `feature/auth` and `feature\auth`
     * are also accepted on all OSes. Use `root` (or `:`) for the top-level module. Module names are case-sensitive.
     * @param moduleNames The name(s) of the module(s), in the same format as [moduleName].
     * @return a [KoScope] containing all Kotlin files in the module.
     */
    fun scopeFromModule(
        moduleName: String,
        vararg moduleNames: String,
    ): KoScope

    /**
     * Creates a [KoScope] containing all Kotlin files in the module.
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param moduleNames Set of the module names.
     * Gradle project path (e.g. `:feature:auth`) is the preferred format, but `feature/auth` and `feature\auth`
     * are also accepted on all OSes. Use `root` (or `:`) for the top-level module. Module names are case-sensitive.
     * @return a [KoScope] containing all Kotlin files in the module.
     */
    fun scopeFromModules(moduleNames: Collection<String>): KoScope

    /**
     * Creates a [KoScope] containing all Kotlin files in the given package.
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param packagee The name of the package.
     * @param moduleName The name of the module. If null, all modules will be included.
     * Gradle project path (e.g. `:feature:auth`) is the preferred format, but `feature/auth` and `feature\auth`
     * are also accepted on all OSes. Use `root` (or `:`) for the top-level module. Module names are case-sensitive.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @return a [KoScope] containing all Kotlin files in the given package.
     */
    fun scopeFromPackage(
        packagee: String,
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): KoScope

    /**
     * Creates a [KoScope] containing all Kotlin files in the given source set(s). If the source set is present in multiple modules
     * then all of them will be included.
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param sourceSetName The name of the source set.
     * @param sourceSetNames The name(s) of the source set(s).
     * @return a [KoScope] containing all Kotlin files in the given source set(s).
     */
    fun scopeFromSourceSet(
        sourceSetName: String,
        vararg sourceSetNames: String,
    ): KoScope

    /**
     * Creates a [KoScope] containing all Kotlin files in the given source set(s). If the source set is present in multiple modules
     * then all of them will be included.
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param sourceSetNames Set of the source set names.
     * @return a [KoScope] containing all Kotlin files in the given source set(s).
     */
    fun scopeFromSourceSets(sourceSetNames: Collection<String>): KoScope

    /**
     * Creates a [KoScope] containing all Kotlin files in the production source sets.
     * The production source set is the source set whose name neither starts nor ends with "test".
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param moduleName The name of the module. If null, all modules will be included.
     * Gradle project path (e.g. `:feature:auth`) is the preferred format, but `feature/auth` and `feature\auth`
     * are also accepted on all OSes. Use `root` (or `:`) for the top-level module. Module names are case-sensitive.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @return a [KoScope] containing all Kotlin files in the production source sets.
     * @throws IllegalArgumentException if [sourceSetName] is a test source set.
     *
     * @see [scopeFromTest]
     */
    fun scopeFromProduction(
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): KoScope

    /**
     * Creates a [KoScope] containing all Kotlin files in the test source sets.
     * The test source set is the source set whose name starts or ends with "test".
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param moduleName The name of the module. If null, all modules will be included.
     * Gradle project path (e.g. `:feature:auth`) is the preferred format, but `feature/auth` and `feature\auth`
     * are also accepted on all OSes. Use `root` (or `:`) for the top-level module. Module names are case-sensitive.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @return a [KoScope] containing all Kotlin files in the test source sets.
     * @throws IllegalArgumentException if [sourceSetName] is a production source set.
     *
     * @see [scopeFromProduction]
     */
    fun scopeFromTest(
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): KoScope

    /**
     * Creates a [KoScope] containing all Kotlin files in the given directory.
     *
     * Both `/` and `\` separators are accepted on all OSes.
     * Letter case follows the file system: on case-sensitive file systems (Linux) `app/src` does not match `App/src`,
     * on case-insensitive ones (macOS, Windows) it does, and returned file paths keep the letter case passed in.
     *
     * @param path The path relative to the project root directory.
     * @param paths The path(s) relative to the project root directory.
     * @return a [KoScope] containing all Kotlin files in the given directory.
     */
    fun scopeFromDirectory(
        path: String,
        vararg paths: String,
    ): KoScope

    /**
     * Creates a [KoScope] containing all Kotlin files in the given directories.
     *
     * Both `/` and `\` separators are accepted on all OSes.
     * Letter case follows the file system: on case-sensitive file systems (Linux) `app/src` does not match `App/src`,
     * on case-insensitive ones (macOS, Windows) it does, and returned file paths keep the letter case passed in.
     *
     * @param paths The set of paths relative to the project root directory.
     * @return a [KoScope] containing all Kotlin files in the given directories.
     */
    fun scopeFromDirectories(paths: Collection<String>): KoScope

    /**
     * Creates a [KoScope] containing all Kotlin files in the given directory.
     * Some features (such as `KoFile.projectPath`, `KoFile.moduleName`) do not work with this method.
     * Both `/` and `\` separators are accepted on all OSes.
     * Letter case follows the file system: on case-sensitive file systems (Linux) `app/src` does not match `App/src`,
     * on case-insensitive ones (macOS, Windows) it does, and returned file paths keep the letter case passed in.
     *
     * @param absolutePath The absolute path to the directory from outside the project.
     * @param paths The absolute path(s) to the directories from outside the project.
     * @return a [KoScope] containing all Kotlin files in the given directory.
     */
    fun scopeFromExternalDirectory(
        absolutePath: String,
        vararg paths: String,
    ): KoScope

    /**
     * Creates a [KoScope] containing all Kotlin files in the given directories.
     * Some features (such as `KoFile.projectPath`, `KoFile.moduleName`) do not work with this method.
     * Both `/` and `\` separators are accepted on all OSes.
     * Letter case follows the file system: on case-sensitive file systems (Linux) `app/src` does not match `App/src`,
     * on case-insensitive ones (macOS, Windows) it does, and returned file paths keep the letter case passed in.
     *
     * @param absolutePaths Set of the absolute paths to the directories from outside the project.
     * @return a [KoScope] containing all Kotlin files in the given directories.
     */
    fun scopeFromExternalDirectories(absolutePaths: Collection<String>): KoScope

    /**
     * Creates a [KoScope] of the given file(s).
     *
     * Both `/` and `\` separators are accepted on all OSes.
     * Letter case must match the file system, e.g. `app/src` does not match `App/src`. On case-sensitive file systems
     * (Linux) a mismatch throws, on case-insensitive ones (macOS, Windows) a mismatched Kotlin file is skipped.
     *
     * @param path The path relative to the project root directory.
     * @param paths The path(s) relative to the project root directory.
     * @return a [KoScope] of the given file(s).
     */
    fun scopeFromFile(
        path: String,
        vararg paths: String,
    ): KoScope

    /**
     * Creates a [KoScope] of the given files.
     *
     * Both `/` and `\` separators are accepted on all OSes.
     * Letter case must match the file system, e.g. `app/src` does not match `App/src`. On case-sensitive file systems
     * (Linux) a mismatch throws, on case-insensitive ones (macOS, Windows) a mismatched Kotlin file is skipped.
     *
     * @param paths The set of paths relative to the project root directory.
     * @return a [KoScope] of the given files.
     */
    fun scopeFromFiles(paths: Collection<String>): KoScope
}
