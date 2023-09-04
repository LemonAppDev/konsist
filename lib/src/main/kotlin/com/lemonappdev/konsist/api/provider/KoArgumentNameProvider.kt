package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to argument names.
 *
 */
interface KoArgumentNameProvider : KoBaseProvider {
    /**
     * The argument name.
     */
    val argumentName: String?

    /**
     * The value of argument.
     */
    val value: String
}
