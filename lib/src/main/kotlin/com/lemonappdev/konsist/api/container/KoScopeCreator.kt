package com.lemonappdev.konsist.api.container

/**
 * Scope creator.
 *
 * Creates a [KoScope] instance from the given set of files such as all project files, single module, path etc.
 *
 */
interface KoScopeCreator {
    /**
     * Returns a path to the root project directory.
     */
    val projectRootPath: String

    /**
     * Returns a [KoScope] containing all of Kotlin files in the project.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param moduleName The name of the module. If null, all modules will be included.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @param ignoreBuildConfig If true, build config files and directories such as Gradle buildSrc directory will be ignored.
     * @return a [KoScope] containing all of Kotlin files in the project.
     */
    fun scopeFromProject(moduleName: String? = null, sourceSetName: String? = null, ignoreBuildConfig: Boolean = true): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the module.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param moduleName The name of the module.
     * @param moduleNames The name(s) of the module(s).
     * @return a [KoScope] containing all of Kotlin files in the module.
     */
    fun scopeFromModule(moduleName: String, vararg moduleNames: String): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the given package.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param packagee The name of the package.
     * @param moduleName The name of the module. If null, all modules will be included.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @return a [KoScope] containing all of Kotlin files in the given package.
     */
    fun scopeFromPackage(packagee: String, moduleName: String? = null, sourceSetName: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in source set. If the source set is present in multiple modules
     * then all of them will be included.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param sourceSetName The name of the source set.
     * @param sourceSetNames The name(s) of the source set(s).
     * @return a [KoScope] containing all of Kotlin files in source set.
     */
    fun scopeFromSourceSet(sourceSetName: String, vararg sourceSetNames: String): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the production source sets.
     * The production source set is the source set which name does not start and ends with "test".
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param moduleName The name of the module. If null, all modules will be included.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @return a [KoScope] containing all of Kotlin files in the production source sets.
     *
     * @See [scopeFromProduction]
     */
    fun scopeFromProduction(moduleName: String? = null, sourceSetName: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the test source sets.
     * The test source set is the source set which name starts or ends with "test".
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param moduleName The name of the module. If null, all modules will be included.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @return a [KoScope] containing all of Kotlin files in the test source sets.
     *
     * @See [scopeFromTest]
     */
    fun scopeFromTest(moduleName: String? = null, sourceSetName: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the given directory.
     *
     * @param path The path relative to the project root directory.
     * @return a [KoScope] containing all of Kotlin files in the given directory.
     */
    fun scopeFromDirectory(path: String): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the given directory.
     * Some features (as `KoFile.projectPath`, `KoFile.moduleName`) do not work with this method.
     *
     * @param absolutePath The absolute path to the directory from outside the project.
     * @return a [KoScope] containing all of Kotlin files in the given directory.
     */
    fun scopeFromExternalDirectory(absolutePath: String): KoScope

    /**
     * Returns a [KoScope] of a given file.
     *
     * @param path The path relative to the project root directory.
     * @return a [KoScope] of a given file.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("scopeFromFiles(path)"))
    fun scopeFromFile(path: String): KoScope

    /**
     * Returns a [KoScope] of a given file.
     *
     * @param path The path relative to the project root directory.
     * @param paths The path(s) relative to the project root directory
     * @return a [KoScope] of a given file.
     */
    fun scopeFromFiles(path: String, vararg paths: String): KoScope

    /**
     * Returns a [KoScope] of a given files.
     *
     * @param paths The list of paths relative to the project root directory.
     * @return a [KoScope] of a given files.
     */
    fun scopeFromFiles(paths: List<String>): KoScope
}
