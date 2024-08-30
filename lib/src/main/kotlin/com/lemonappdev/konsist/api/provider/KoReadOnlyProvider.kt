package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to the read only information.
 */
@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsReadOnlyProvider"))
interface KoReadOnlyProvider : KoBaseProvider {
    /**
     * Determines whatever declaration is read only (i.e. is declared as `val`).
     */
    val isReadOnly: Boolean
}
