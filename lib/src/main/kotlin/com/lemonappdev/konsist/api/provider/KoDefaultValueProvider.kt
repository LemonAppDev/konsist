package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to the default value.
 */
interface KoDefaultValueProvider : KoBaseProvider {
    /**
     * Default value of the declaration.
     */
    val defaultValue: String?

    /**
     * Determines whatever the declaration ha the default value.
     *
     * @param value the default value to check (optional).
     * @return `true` if the declaration has the specified default value (or any default value if [value] is `null`), `false` otherwise.
     */
    fun hasDefaultValue(value: String? = null): Boolean
}
