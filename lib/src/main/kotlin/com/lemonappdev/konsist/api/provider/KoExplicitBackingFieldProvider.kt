package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoExplicitBackingFieldDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to the explicit backing field declaration
 * e.g. `field = MutableStateFlow(0)` in `val state: StateFlow<Int> field = MutableStateFlow(0)`.
 */
interface KoExplicitBackingFieldProvider : KoBaseProvider {
    /**
     * The explicit backing field of the declaration.
     */
    val explicitBackingField: KoExplicitBackingFieldDeclaration?

    /**
     * Determines whether the declaration has an explicit backing field.
     */
    val hasExplicitBackingField: Boolean
}
