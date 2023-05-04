package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin import declaration.
 */
interface KoInterfaceDeclaration : KoComplexDeclaration {
    /**
     * Whatever interface has a 'actual' modifier.
     */
    fun hasActualModifier(): Boolean

    /**
     * Whatever interface has a 'expect' modifier.
     */
    fun hasExpectModifier(): Boolean
}
