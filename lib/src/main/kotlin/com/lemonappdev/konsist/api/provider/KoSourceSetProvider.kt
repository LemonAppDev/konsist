package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about its source set.
 */
interface KoSourceSetProvider : KoBaseProvider {

    /**
     * The declaration's source set name.
     */
    val sourceSetName: String

    /**
     * Determines whatever declaration reside in source set.
     *
     * @param sourceSetName The name of the source set to check.
     * @return `true` if a declaration resides in the specified source set, `false` otherwise.
     */
    fun resideInSourceSet(sourceSetName: String): Boolean
}
