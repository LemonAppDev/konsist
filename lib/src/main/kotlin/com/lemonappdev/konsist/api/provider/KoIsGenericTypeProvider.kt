package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is a generic type.
 */
interface KoIsGenericTypeProvider : KoBaseProvider {
    /**
     * Determines whatever type is generic type.
     */
    val isGenericType: Boolean
}
