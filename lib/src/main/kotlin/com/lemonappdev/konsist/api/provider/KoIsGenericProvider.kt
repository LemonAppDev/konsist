package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is generic.
 */
interface KoIsGenericProvider : KoBaseProvider {
    /**
     * Determines whether the declaration is generic.
     */
    val isGeneric: Boolean
}
