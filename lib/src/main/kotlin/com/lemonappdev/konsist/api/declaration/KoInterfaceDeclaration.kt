package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin import declaration.
 */
interface KoInterfaceDeclaration : KoComplexDeclaration {
    /**
     * Whatever interface has a `actual` modifier.
     *
     * @return `true` if the interface has the `actual` modifier, `false` otherwise.
     */
    fun hasActualModifier(): Boolean

    /**
     * Whatever interface has a `expect` modifier.
     *
     * @return `true` if the interface has the `expect` modifier, `false` otherwise.
     */
    fun hasExpectModifier(): Boolean

    /**
     * Whatever interface has a `fun` modifier.
     *
     * @return `true` if the interface has the `fun` modifier, `false` otherwise.
     */
    fun hasFunModifier(): Boolean
}
