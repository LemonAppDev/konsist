package com.lemonappdev.konsist.api

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
     * @param ignoreBuildConfig If true, build config files such as Gradle buildSrc directory will be ignored.
     */
    fun scopeFromProject(moduleName: String? = null, sourceSetName: String? = null, ignoreBuildConfig: Boolean = true): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the module.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param moduleNames The name of the module.
     */
    fun scopeFromModule(vararg moduleNames: String): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the given package.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param moduleName The name of the module. If null, all modules will be included.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     */
    fun scopeFromPackage(packagee: String, moduleName: String? = null, sourceSetName: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in source set. If the source set is present in multiple modules
     * then all of them will be included.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param sourceSetNames The name of the source set.
     */
    fun scopeFromSourceSet(vararg sourceSetNames: String): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the production source sets.
     * The production source set is the source set which name does not start and ends with "test".
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param moduleName The name of the module. If null, all modules will be included.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
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
     *
     * @See [scopeFromTest]
     */
    fun scopeFromTest(moduleName: String? = null, sourceSetName: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the given directory.
     *
     * @param path The path to the directory.
     * @param resolvePathFromProjectRoot Determines whether the `path` parameter should be resolved relative to the project root directory.
     *  If set to `true`, the `path` parameter will be resolved relative to the project root directory.
     *  If set to `false`, the `path` parameter will be treated as an absolute path.
     *  The default value is `true`.
     */
    fun scopeFromDirectory(path: String, resolvePathFromProjectRoot: Boolean = true): KoScope

    /**
     * Returns a [KoScope] of a given file.
     *
     * @param path The path to the file.
     * @param resolvePathFromProjectRoot Determines whether the `path` parameter should be resolved relative to the project root directory.
     *  If set to `true`, the `path` parameter will be resolved relative to the project root directory.
     *  If set to `false`, the `path` parameter will be treated as an absolute path.
     *  The default value is `true`.
     */
    fun scopeFromFile(path: String, resolvePathFromProjectRoot: Boolean = true): KoScope
}
