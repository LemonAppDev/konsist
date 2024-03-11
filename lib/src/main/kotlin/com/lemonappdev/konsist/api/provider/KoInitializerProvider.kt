package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has been initialized.
 */
interface KoInitializerProvider : KoBaseProvider {
    /**
     * Whether this declaration has been initialized.
     */
    val isInitialized: Boolean

    /**
     * Whether this declaration has implementation.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("isInitialized"))
    val hasImplementation: Boolean
}
