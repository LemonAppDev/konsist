package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it has been initialized.
 */
interface KoInitializerProvider : KoBaseProvider {
    /**
     * Determines whatever this declaration has been initialized.
     */
    val isInitialized: Boolean

    /**
     * Determines whatever this declaration has implementation.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("isInitialized"))
    val hasImplementation: Boolean
}
