package com.lemonappdev.konsist.api.provider

interface KoNullableProvider: KoProvider {
    /**
     * Whatever type is nullable.
     */
    val isNullable: Boolean
}
