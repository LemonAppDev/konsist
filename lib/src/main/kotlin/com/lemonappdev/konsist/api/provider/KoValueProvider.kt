package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to its value.
 */
interface KoValueProvider : KoBaseProvider {
    /**
     * The value of the declaration.
     */
    val value: String?

    /**
     * Determines whether the declaration has the specified value.
     *
     * @param value the value to check (optional).
     * @return `true` if the declaration has the specified value (or any value if [value] is `null`), `false` otherwise.
     */
    fun hasValue(value: String? = null): Boolean
}
