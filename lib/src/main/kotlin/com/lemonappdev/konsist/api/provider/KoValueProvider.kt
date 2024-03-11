package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to value.
 *
 */
interface KoValueProvider : KoBaseProvider {
    /**
     * The value of declaration.
     */
    val value: String?

    /**
     * Determines whatever the declaration has the value.
     *
     * @param value the value to check (optional).
     * @return `true` if the declaration has the specified value (or any value if [value] is `null`), `false` otherwise.
     */
    fun hasValue(value: String? = null): Boolean
}
