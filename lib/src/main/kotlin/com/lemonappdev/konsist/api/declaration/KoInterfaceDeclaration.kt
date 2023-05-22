package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin import declaration.
 */
interface KoInterfaceDeclaration : KoComplexDeclaration {
    /**
     * Whatever interface has a 'actual' modifier.
     *
     * @return `true` if the declaration has the 'actual' modifier, `false` otherwise.
     */
    fun hasActualModifier(): Boolean

    /**
     * Whatever interface has a 'expect' modifier.
     *
     * @return `true` if the declaration has the 'expect' modifier, `false` otherwise.
     */
    fun hasExpectModifier(): Boolean
}
