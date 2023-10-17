package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether its type is nullable.
 */
interface KoNullableProvider : KoBaseProvider {
    /**
     * Whatever type is nullable.
     */
    val isNullable: Boolean
}
