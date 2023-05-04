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
     * The 'actual' modifier.
     */
    fun hasActualModifier(): Boolean
}
