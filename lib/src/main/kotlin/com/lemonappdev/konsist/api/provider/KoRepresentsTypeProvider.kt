package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it represents a specified type.
 */
interface KoRepresentsTypeProvider : KoBaseProvider {
    /**
     * Determines whatever this declaration represents the specified type.
     *
     * @param name the name of type to compare. It can be either a simple name or a fully qualified name.
     * @return `true` if this type represents the specified type, `false` otherwise.
     */
    fun representsType(name: String?): Boolean
}
