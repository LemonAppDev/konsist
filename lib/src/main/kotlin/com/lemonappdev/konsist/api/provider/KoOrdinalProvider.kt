package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to its ordinal.
 */
interface KoOrdinalProvider : KoBaseProvider {
    /**
     * Ordinal of the declaration (zero-based position of the enum constant in its enum class declaration).
     */
    val ordinal: Int
}
