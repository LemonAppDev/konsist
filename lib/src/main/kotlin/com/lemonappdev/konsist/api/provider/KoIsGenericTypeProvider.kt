package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is a generic type.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("KoIsGenericProvider"))
interface KoIsGenericTypeProvider : KoBaseProvider {
    /**
     * Determines whatever type is generic type.
     */
    @Deprecated("Will be removed in version 0.19.0", ReplaceWith("isGeneric"))
    val isGenericType: Boolean
}
