package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that can either have an explicit name
 * or rely on a compiler-assigned default name.
 */
interface KoHasDefaultNameProvider : KoBaseProvider {
    /**
     * Indicates whether the declaration uses a compiler-assigned default name
     * instead of an explicitly specified one.
     *
     * @return `true` if the declaration uses a default name, `false` if it defines its own explicit name.
     */
    val hasDefaultName: Boolean
}
