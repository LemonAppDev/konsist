package com.lemonappdev.konsist.api.declaration

/**
 * Companion object declaration.
 */
interface KoObjectDeclaration : KoComplexDeclaration {
    /**
     * Whether this object has a data modifier.
     */
    fun hasDataModifier(): Boolean
}
