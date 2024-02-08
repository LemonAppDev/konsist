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
     */
    val projectRootPath: String

    /**
     * Creates a [KoScope] containing all of Kotlin files in the project.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param moduleName The name of the module. If null, all modules will be included.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @param ignoreBuildConfig If true, build config files and directories such as Gradle buildSrc directory will be ignored.
     * @return a [KoScope] containing all of Kotlin files in the project.
     */
    fun scopeFromProject(moduleName: String? = null, sourceSetName: String? = null, ignoreBuildConfig: Boolean = true): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the module.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param moduleName The name of the module.
     * @param moduleNames The name(s) of the module(s).
     * @return a [KoScope] containing all of Kotlin files in the module.
     */
    fun scopeFromModule(moduleName: String, vararg moduleNames: String): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the module.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param moduleNames Set of the module names.
     * @return a [KoScope] containing all of Kotlin files in the module.
     */
    fun scopeFromModules(moduleNames: Set<String>): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the given package.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param packagee The name of the package.
     * @param moduleName The name of the module. If null, all modules will be included.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @return a [KoScope] containing all of Kotlin files in the given package.
     */
    fun scopeFromPackage(packagee: String, moduleName: String? = null, sourceSetName: String? = null): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in source set. If the source set is present in multiple modules
     * then all of them will be included.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param sourceSetName The name of the source set.
     * @param sourceSetNames The name(s) of the source set(s).
     * @return a [KoScope] containing all of Kotlin files in source set.
     */
    fun scopeFromSourceSet(sourceSetName: String, vararg sourceSetNames: String): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in source set. If the source set is present in multiple modules
     * then all of them will be included.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param sourceSetNames Set of the source set names.
     * @return a [KoScope] containing all of Kotlin files in source set.
     */
    fun scopeFromSourceSets(sourceSetNames: Set<String>): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the production source sets.
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
     * Creates a [KoScope] containing all of Kotlin files in the test source sets.
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
     * Creates a [KoScope] containing all of Kotlin files in the given directory.
     *
     * @param path The path relative to the project root directory.
     * @param paths The path(s) relative to the project root directory
     * @return a [KoScope] containing all of Kotlin files in the given directory.
     */
    fun scopeFromDirectory(path: String, vararg paths: String): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the given directories.
     *
     * @param paths The set of paths relative to the project root directory.
     * @return a [KoScope] containing all of Kotlin files in the given directories.
     */
    fun scopeFromDirectories(paths: Set<String>): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the given directory.
     * Some features (as `KoFile.projectPath`, `KoFile.moduleName`) do not work with this method.
     *
     * @param absolutePath The absolute path to the directory from outside the project.
     * @param paths The absolute path(s) to the project root directory
     * @return a [KoScope] containing all of Kotlin files in the given directory.
     */
    fun scopeFromExternalDirectory(absolutePath: String, vararg paths: String): KoScope

    /**
     * Creates a [KoScope] containing all of Kotlin files in the given directories.
     * Some features (as `KoFile.projectPath`, `KoFile.moduleName`) do not work with this method.
     *
     * @param absolutePaths Set of the absolute paths to the directory from outside the project.
     * @return a [KoScope] containing all of Kotlin files in the given directory.
     */
    fun scopeFromExternalDirectories(absolutePaths: Set<String>): KoScope

    /**
     * Creates a [KoScope] of a given file.
     *
     * @param path The path relative to the project root directory.
     * @param paths The path(s) relative to the project root directory
     * @return a [KoScope] of a given file.
     */
    fun scopeFromFile(path: String, vararg paths: String): KoScope

    /**
     * Creates a [KoScope] of a given files.
     *
     * @param paths The set of paths relative to the project root directory.
     * @return a [KoScope] of a given files.
     */
    fun scopeFromFiles(paths: Set<String>): KoScope
}
