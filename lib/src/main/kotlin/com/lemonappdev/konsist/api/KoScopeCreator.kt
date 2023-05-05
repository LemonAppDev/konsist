package com.lemonappdev.konsist.api

/**
 * Scope creator.
 */
interface KoScopeCreator {
    /**
     * Returns a [KoScope] containing all of Kotlin files in the project.
     */
    fun scopeFromProject(module: String? = null, sourceSet: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin production files in the project.
     */
    fun scopeFromProduction(module: String? = null, sourceSet: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin test files in the project.
     */
    fun scopeFromTest(module: String? = null, sourceSet: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the given package.
     */
    fun scopeFromPackage(packagee: String, module: String? = null, sourceSet: String? = null): KoScope

    /**
     * Returns a [KoScope] containing all of Kotlin files in the given directory.
     */
    fun scopeFromPath(path: String): KoScope

    /**
     * Returns a [KoScope] of a given file.
     */
    fun scopeFromFile(path: String): KoScope
}
