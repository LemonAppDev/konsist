package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is a generic.
 */
interface KoIsGenericProvider : KoBaseProvider {
    /**
     * Determines whatever declaration is generic.
     */
    val isGeneric: Boolean
}
