package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to the read only information.
 */
interface KoIsReadOnlyProvider : KoBaseProvider {
    /**
     * Determines whatever declaration is read only (i.e. is declared as `val`).
     */
    val isReadOnly: Boolean
}
