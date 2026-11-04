package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it represents a specified type.
 */
interface KoRepresentsTypeProvider : KoBaseProvider {
    /**
     * Determines whether this declaration represents the specified type.
     *
     * @param name the name of the type to compare. It can be either a simple name or a fully qualified name.
     * @param ignoreCase Specifies whether the comparison should ignore case.
     *        If `true`, the comparison will be case-insensitive.
     *        If `false`, the comparison will consider case sensitivity.
     * @return `true` if this declaration represents the specified type, `false` otherwise.
     */
    fun representsType(
        name: String?,
        ignoreCase: Boolean = false,
    ): Boolean
}
