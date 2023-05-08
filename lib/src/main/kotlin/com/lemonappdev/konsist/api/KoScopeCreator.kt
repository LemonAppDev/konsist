package com.lemonappdev.konsist.api

/**
 * Scope creator.
 */
interface KoScopeCreator {
    /**
     * Returns a [KoScope] containing all of Kotlin files in the project.
     */
    fun scopeFromProject(moduleName: String? = null, sourceSetName: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin production files in the project.
     */
    fun scopeFromProduction(moduleName: String? = null, sourceSetName: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin test files in the project.
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
