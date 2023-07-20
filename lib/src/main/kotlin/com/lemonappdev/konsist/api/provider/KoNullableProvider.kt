package com.lemonappdev.konsist.api.provider

interface KoNullableProvider : KoBaseProvider {
    /**
     * Whatever type is nullable.
     */
    val isNullable: Boolean
}
