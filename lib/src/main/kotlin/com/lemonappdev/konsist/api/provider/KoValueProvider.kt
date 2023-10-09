package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to value.
 *
 */
interface KoValueProvider : KoBaseProvider {
    /**
     * The value of declaration.
     */
    val value: String
}
