package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about its source set.
 */
interface KoSourceSetProvider : KoBaseProvider {
    /**
     * The declaration's source set name - the directory located in the `src` directory of the module directory
     * (see [KoModuleProvider.moduleName]), e.g. "main" for `app/src/main/kotlin/A.kt` and `src/app/src/main/kotlin/A.kt`,
     * and "test" for `app/src/test/resources/A.kt`.
     * Empty for the file located outside the module `src` directory (e.g. `app/A.kt`) and the file located directly
     * in the module `src` directory (e.g. `app/src/A.kt`).
     */
    val sourceSetName: String

    /**
     * Determines whether the declaration resides in the specified source set.
     *
     * @param sourceSetName The name of the source set to check.
     * @return `true` if the declaration resides in the specified source set, `false` otherwise.
     */
    fun residesInSourceSet(sourceSetName: String): Boolean

    /**
     * Determines whether the declaration resides in the specified source set.
     *
     * @param sourceSetName The name of the source set to check.
     * @return `true` if the declaration resides in the specified source set, `false` otherwise.
     */
    @Deprecated("Will be removed in version 1.0.0-rc1", ReplaceWith("residesInSourceSet(sourceSetName)"))
    fun resideInSourceSet(sourceSetName: String): Boolean
}
