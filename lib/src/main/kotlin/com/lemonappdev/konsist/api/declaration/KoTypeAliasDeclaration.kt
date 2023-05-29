package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin type alias declaration.
 */
interface KoTypeAliasDeclaration : KoDeclaration {
    /**
     * Type alias type.
     */
    val type: KoTypeDeclaration

    /**
     * Whether the type alias has actual modifier.
     *
     * @return `true` if the type alias has the `actual` modifier, `false` otherwise.
     */
    fun hasActualModifier(): Boolean
}
