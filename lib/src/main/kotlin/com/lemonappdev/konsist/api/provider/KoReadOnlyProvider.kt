package com.lemonappdev.konsist.api.provider

@Deprecated("Will be removed in version 0.18.0", ReplaceWith("KoIsReadOnlyProvider"))
interface KoReadOnlyProvider : KoBaseProvider {
    /**
     * Determines whatever declaration is read only (i.e. is declared as `val`).
     */
    val isReadOnly: Boolean
}
