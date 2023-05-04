package com.lemonappdev.konsist.api.declaration

/**
 * Object declaration.
 */
interface KoObjectDeclaration : KoComplexDeclaration {
    /**
     * Whether this object has a data modifier.
     */
    fun hasDataModifier(): Boolean
}
