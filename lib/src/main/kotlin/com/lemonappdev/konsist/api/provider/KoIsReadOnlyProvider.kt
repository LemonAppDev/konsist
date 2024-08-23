package com.lemonappdev.konsist.api.provider

interface KoIsReadOnlyProvider : KoBaseProvider {
    /**
     * Determines whatever declaration is read only (i.e. is declared as `val`).
     */
    val isReadOnly: Boolean
}
