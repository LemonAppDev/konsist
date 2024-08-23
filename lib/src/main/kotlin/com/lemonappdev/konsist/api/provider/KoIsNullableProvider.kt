package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether its type is nullable.
 */
interface KoIsNullableProvider : KoBaseProvider {
    /**
     * Determines whatever declaration type is nullable.
     */
    val isNullable: Boolean
}
