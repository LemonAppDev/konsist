package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has an implementation.
 */
interface KoImplementationProvider : KoBaseProvider {
    /**
     * Whether this declaration has implementation.
     */
    val hasImplementation: Boolean
}
