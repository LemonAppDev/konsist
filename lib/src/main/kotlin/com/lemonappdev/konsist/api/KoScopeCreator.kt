package com.lemonappdev.konsist.api

import java.io.File

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
    val rootProjectPath: String

    /**
     * Returns a root project directory.
     */
    val rootProjectDirectory: File

    /**
     * Returns a [KoScope] containing all of Kotlin files in the project.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param moduleName The name of the module. If null, all modules will be included.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     * @param ignoreBuildConfig If true, build config files such as Gradle buildSrc directory will be ignored.
     * @return a [KoScope] containing all of Kotlin files in the project.
     */
    fun scopeFromProject(moduleName: String? = null, sourceSetName: String? = null, ignoreBuildConfig: Boolean = true): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the module.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param moduleNames The name of the module.
     * @return a [KoScope] containing all of Kotlin files in the module.
     */
    fun scopeFromModule(vararg moduleNames: String): KoScope

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
     * @param sourceSetNames The name of the source set.
     * @return a [KoScope] containing all of Kotlin files in source set.
     */
    fun scopeFromSourceSet(vararg sourceSetNames: String): KoScope

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
     * @param path The absolute path to the directory.
     * @return a [KoScope] containing all of Kotlin files in the given directory.
     *
     * @see [scopeFromProjectDirectory]
     */
    fun scopeFromDirectory(path: String): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the given directory.
     *
     * @param path The project root relative path to the directory.
     * @return a [KoScope] containing all of Kotlin files in the given directory.
     *
     * @see [scopeFromDirectory]
     */
    fun scopeFromProjectDirectory(path: String): KoScope

    /**
     * Returns a [KoScope] of a given file.
     *
     * @param path The absolute path to the file.
     * @return a [KoScope] of a given file.
     *
     * @see [scopeFromProjectFile]
     */
    fun scopeFromFile(path: String): KoScope

    /**
     * Returns a [KoScope] of a given file.
     *
     * @param path The project root relative path to the file.
     * @return a [KoScope] of a given file.
     *
     * @see [scopeFromFile]
     */
    fun scopeFromProjectFile(path: String): KoScope
}
