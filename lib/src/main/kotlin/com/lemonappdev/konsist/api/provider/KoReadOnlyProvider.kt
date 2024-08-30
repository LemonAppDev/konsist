package com.lemonappdev.konsist.api.provider

interface KoReadOnlyProvider : KoBaseProvider {
    /**
     * Determines whatever declaration is read only (i.e. is declared as `val`).
     */
    val isReadOnly: Boolean
}
