package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to argument properties.
 *
 */
interface KoArgumentPropertiesProvider : KoBaseProvider {
    /**
     * The argument name.
     */
    val argumentName: String?

    /**
     * The value of argument.
     */
    val value: String
}
