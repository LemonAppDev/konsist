package com.lemonappdev.konsist.api.provider

interface KoSourceSetProvider : KoBaseProvider {

    /**
     * The file's source set name.
     */
    val sourceSetName: String

    /**
     * Whether file reside in source set.
     *
     * @param sourceSet The name of the source set to check.
     * @return `true` if a file resides in the specified source set, `false` otherwise.
     */
    fun resideInSourceSet(sourceSet: String): Boolean
}
