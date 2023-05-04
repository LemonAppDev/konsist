package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin property declaration.
 */
interface KoPropertyDeclaration : KoDeclaration {
    /**
     * Whatever property is 'var'.
     */
    val isVar: Boolean

    /**
     * Whatever property is 'val'.
     */
    val isVal: Boolean

    /**
     * Property delegate name.
     */
    val delegateName: String?

    /**
     * Property type.
     */
    val type: KoTypeDeclaration?

    /**
     * The 'lateinit' modifier.
     */
    fun hasLateinitModifier(): Boolean

    /**
     * The 'override' modifier.
     */
    fun hasOverrideModifier(): Boolean

    /**
     * The 'abstract' modifier.
     */
    fun hasAbstractModifier(): Boolean

    /**
     * The 'open' modifier.
     */
    fun hasOpenModifier(): Boolean

    /**
     * The 'final' modifier.
     */
    fun hasFinalModifier(): Boolean

    /**
     * The 'actual' modifier.
     */
    fun hasActualModifier(): Boolean

    /**
     * The 'expect' modifier.
     */
    fun hasExpectModifier(): Boolean

    /**
     * The 'const' modifier.
     */
    fun hasConstModifier(): Boolean

    /**
     * Whatever property is extension property.
     */
    fun isExtension(): Boolean

    /**
     * Whatever property has a delegate.
     */
    fun hasDelegate(name: String? = null): Boolean

    /**
     * Whatever property has a type.
     */
    fun hasType(type: String? = null): Boolean
}
