package com.lemonappdev.konsist.api.container

/**
 * Scope creator.
 *
 * Creates a [KoScope] instance from the given set of files such as all project files, single module, path etc.
 *
 */
interface KoScopeCreator {
    /**
     * Creates a path to the root project directory.
     * Uses OS-specific separators (`\` on Windows, `/` on other OSes).
     */
    val projectRootPath: String

    /**
     * Creates a [KoScope] containing all of Kotlin files in the project.
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param moduleName The name of the module. If null, all modules will be included.
     * Gradle project path (e.g. `:feature:auth`) is the preferred format, but `feature/auth` and `feature\auth`
     * are also accepted on all OSes. Use `root` (or `:`) for the top-level module.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @param ignoreBuildConfig If true, build config files and directories such as Gradle buildSrc directory will be ignored.
     * @return a [KoScope] containing all of Kotlin files in the project.
     * @throws IllegalArgumentException if the module name is blank or the module directory does not exist.
     */
    fun scopeFromProject(
        moduleName: String? = null,
        sourceSetName: String? = null,
        ignoreBuildConfig: Boolean = true,
    ): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the module.
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param moduleName The name of the module.
     * Gradle project path (e.g. `:feature:auth`) is the preferred format, but `feature/auth` and `feature\auth`
     * are also accepted on all OSes. Use `root` (or `:`) for the top-level module.
     * @param moduleNames The name(s) of the module(s), in the same format as [moduleName].
     * @return a [KoScope] containing all of Kotlin files in the module.
     * @throws IllegalArgumentException if the module name is blank, the module directory does not exist,
     * or the module does not contain any Kotlin files.
     */
    fun scopeFromModule(
        moduleName: String,
        vararg moduleNames: String,
    ): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the module.
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param moduleNames Set of the module names.
     * Gradle project path (e.g. `:feature:auth`) is the preferred format, but `feature/auth` and `feature\auth`
     * are also accepted on all OSes. Use `root` (or `:`) for the top-level module.
     * @return a [KoScope] containing all of Kotlin files in the module.
     * @throws IllegalArgumentException if [moduleNames] is empty, any module name is blank, any module directory
     * does not exist, or any module does not contain any Kotlin files.
     */
    fun scopeFromModules(moduleNames: Collection<String>): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the given package.
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param packagee The name of the package.
     * @param moduleName The name of the module. If null, all modules will be included.
     * Gradle project path (e.g. `:feature:auth`) is the preferred format, but `feature/auth` and `feature\auth`
     * are also accepted on all OSes. Use `root` (or `:`) for the top-level module.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @return a [KoScope] containing all of Kotlin files in the given package.
     * @throws IllegalArgumentException if the module name is blank or the module directory does not exist.
     */
    fun scopeFromPackage(
        packagee: String,
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in source set. If the source set is present in multiple modules
     * then all of them will be included.
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param sourceSetName The name of the source set.
     * @param sourceSetNames The name(s) of the source set(s).
     * @return a [KoScope] containing all of Kotlin files in source set.
     */
    fun scopeFromSourceSet(
        sourceSetName: String,
        vararg sourceSetNames: String,
    ): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in source set. If the source set is present in multiple modules
     * then all of them will be included.
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param sourceSetNames Set of the source set names.
     * @return a [KoScope] containing all of Kotlin files in source set.
     */
    fun scopeFromSourceSets(sourceSetNames: Collection<String>): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the production source sets.
     * The production source set is the source set which name does not start and ends with "test".
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param moduleName The name of the module. If null, all modules will be included.
     * Gradle project path (e.g. `:feature:auth`) is the preferred format, but `feature/auth` and `feature\auth`
     * are also accepted on all OSes. Use `root` (or `:`) for the top-level module.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @return a [KoScope] containing all of Kotlin files in the production source sets.
     * @throws IllegalArgumentException if the module name is blank or the module directory does not exist.
     *
     * @See [scopeFromProduction]
     */
    fun scopeFromProduction(
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the test source sets.
     * The test source set is the source set which name starts or ends with "test".
     * Method does not return Kotlin files present in build directories ("build" and "target" outside "src" directory),
     * hidden directories (e.g. ".git", ".gradle", ".idea") and "node_modules" directories.
     *
     * @param moduleName The name of the module. If null, all modules will be included.
     * Gradle project path (e.g. `:feature:auth`) is the preferred format, but `feature/auth` and `feature\auth`
     * are also accepted on all OSes. Use `root` (or `:`) for the top-level module.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @return a [KoScope] containing all of Kotlin files in the test source sets.
     * @throws IllegalArgumentException if the module name is blank or the module directory does not exist.
     *
     * @See [scopeFromTest]
     */
    fun scopeFromTest(
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the given directory.
     *
     * Both `/` and `\` separators are accepted on all OSes.
     * Paths are case-sensitive on all OSes (as on Linux), so letter case must match the file system,
     * also on case-insensitive file systems (macOS, Windows), e.g. `app/src` does not match `App/src`.
     *
     * @param path The path relative to the project root directory.
     * @param paths The path(s) relative to the project root directory
     * @return a [KoScope] containing all of Kotlin files in the given directory.
     */
    fun scopeFromDirectory(
        path: String,
        vararg paths: String,
    ): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the given directories.
     *
     * Both `/` and `\` separators are accepted on all OSes.
     * Paths are case-sensitive on all OSes (as on Linux), so letter case must match the file system,
     * also on case-insensitive file systems (macOS, Windows), e.g. `app/src` does not match `App/src`.
     *
     * @param paths The set of paths relative to the project root directory.
     * @return a [KoScope] containing all of Kotlin files in the given directories.
     */
    fun scopeFromDirectories(paths: Collection<String>): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the given directory.
     * Some features (as `KoFile.projectPath`, `KoFile.moduleName`) do not work with this method.
     * Both `/` and `\` separators are accepted on all OSes.
     * Paths are case-sensitive on all OSes (as on Linux), so letter case must match the file system,
     * also on case-insensitive file systems (macOS, Windows), e.g. `app/src` does not match `App/src`.
     *
     * @param absolutePath The absolute path to the directory from outside the project.
     * @param paths The absolute path(s) to the project root directory
     * @return a [KoScope] containing all of Kotlin files in the given directory.
     */
    fun scopeFromExternalDirectory(
        absolutePath: String,
        vararg paths: String,
    ): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the given directories.
     * Some features (as `KoFile.projectPath`, `KoFile.moduleName`) do not work with this method.
     * Both `/` and `\` separators are accepted on all OSes.
     * Paths are case-sensitive on all OSes (as on Linux), so letter case must match the file system,
     * also on case-insensitive file systems (macOS, Windows), e.g. `app/src` does not match `App/src`.
     *
     * @param absolutePaths Set of the absolute paths to the directory from outside the project.
     * @return a [KoScope] containing all of Kotlin files in the given directory.
     */
    fun scopeFromExternalDirectories(absolutePaths: Collection<String>): KoScope

    /**
     * Creates a [KoScope] of a given file.
     *
     * Both `/` and `\` separators are accepted on all OSes.
     * Paths are case-sensitive on all OSes (as on Linux), so letter case must match the file system,
     * also on case-insensitive file systems (macOS, Windows), e.g. `app/src` does not match `App/src`.
     *
     * @param path The path relative to the project root directory.
     * @param paths The path(s) relative to the project root directory
     * @return a [KoScope] of a given file.
     */
    fun scopeFromFile(
        path: String,
        vararg paths: String,
    ): KoScope

    /**
     * Creates a [KoScope] of a given files.
     *
     * Both `/` and `\` separators are accepted on all OSes.
     * Paths are case-sensitive on all OSes (as on Linux), so letter case must match the file system,
     * also on case-insensitive file systems (macOS, Windows), e.g. `app/src` does not match `App/src`.
     *
     * @param paths The set of paths relative to the project root directory.
     * @return a [KoScope] of a given files.
     */
    fun scopeFromFiles(paths: Collection<String>): KoScope
}
