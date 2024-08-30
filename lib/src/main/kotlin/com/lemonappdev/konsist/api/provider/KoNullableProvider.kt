package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether its type is nullable.
 */
@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsNullableProvider"))
interface KoNullableProvider : KoBaseProvider {
    /**
     * Determines whatever declaration type is nullable.
     */
    val isNullable: Boolean
}
