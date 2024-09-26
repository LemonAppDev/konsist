package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a declaration that provides information about whether it is a mutable type.
 */
interface KoIsMutableTypeProvider : KoBaseProvider {
    /**
     * Determines whether the declaration is a mutable type.
     *
     * This method only works if the type contains the "Mutable" string in its name.
     */
    val isMutableType: Boolean
}
