package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a declaration that provides information about whether it is a mutable type.
 */
interface KoIsMutableTypeProvider : KoBaseProvider {
    /**
     * Determines whatever the declaration is a mutable type.
     */
    val isMutableType: Boolean
}
