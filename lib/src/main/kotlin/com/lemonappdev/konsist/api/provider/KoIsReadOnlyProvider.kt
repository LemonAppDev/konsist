package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides information about whether it is read-only.
 */
interface KoIsReadOnlyProvider : KoBaseProvider {
    /**
     * Determines whether the declaration is read-only (i.e. is declared as `val`).
     */
    val isReadOnly: Boolean
}
