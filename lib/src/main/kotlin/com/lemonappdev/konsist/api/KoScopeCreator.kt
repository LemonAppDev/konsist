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
     */
    fun scopeFromProject(moduleName: String? = null, sourceSetName: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the project.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param moduleName The name of the module.
     */
    fun scopeFromModule(moduleName: String): KoScope

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
     * Returns a [KoScope] containing all of Kotlin files in the given package.
     * Method does return Kotlin files present in build directories such as "build" and "target".
     *
     * @param moduleName The name of the module. If null, all modules will be included.
     * @param sourceSetName The name of the source set. If null, all source sets will be included.
     */
    fun scopeFromPackage(packagee: String, moduleName: String? = null, sourceSetName: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the given directory.
     *
     * @param path The path to the directory.
     */
    fun scopeFromDirectory(path: String): KoScope

    /**
     * Returns a [KoScope] of a given file.
     *
     * @param path The path to the file.
     */
    fun scopeFromFile(path: String): KoScope
}
