package com.lemonappdev.konsist.api.declaration

/**
 * Represents a companion object declaration.
 */
interface KoCompanionObjectDeclaration : KoComplexDeclaration {
    /**
     * Returns `true` if this companion object has a name.
     */
    fun hasName(): Boolean
}
