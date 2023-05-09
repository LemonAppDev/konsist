package com.lemonappdev.konsist.api

import java.io.File

/**
 * Scope creator.
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
     */
    fun scopeFromProject(moduleName: String? = null, sourceSetName: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the production source sets.
     * The production source set is the source set which name does not start and ends with "test".
     *
     * @See [scopeFromProduction]
     */
    fun scopeFromProduction(moduleName: String? = null, sourceSetName: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the test source sets.
     * The test source set is the source set which name starts or ends with "test".
     *
     * @See [scopeFromTest]
     */
    fun scopeFromTest(moduleName: String? = null, sourceSetName: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the given package.
     */
    fun scopeFromPackage(packagee: String, moduleName: String? = null, sourceSetName: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the given directory.
     */
    fun scopeFromDirectory(path: String): KoScope

    /**
     * Returns a [KoScope] of a given file.
     */
    fun scopeFromFile(path: String): KoScope
}
