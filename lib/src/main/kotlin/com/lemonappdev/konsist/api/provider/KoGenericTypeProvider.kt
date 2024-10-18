package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is a generic type.
 */
@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsGenericTypeProvider"))
interface KoGenericTypeProvider : KoBaseProvider {
    /**
     * Determines whatever type is generic type.
     */
    val isGenericType: Boolean
}
