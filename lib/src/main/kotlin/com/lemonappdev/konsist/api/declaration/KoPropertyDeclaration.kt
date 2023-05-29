package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin property declaration.
 */
interface KoPropertyDeclaration : KoDeclaration {
    /**
     * Whatever property is `var`.
     */
    val isVar: Boolean

    /**
     * Whatever property is `val`.
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
     * Whether the property has lateinit modifier.
     *
     * @return `true` if the property has the `lateinit` modifier, `false` otherwise.
     */
    fun hasLateinitModifier(): Boolean

    /**
     * Whether the property has override modifier.
     *
     * @return `true` if the property has the `override` modifier, `false` otherwise.
     */
    fun hasOverrideModifier(): Boolean

    /**
     * Whether the property has abstract modifier.
     *
     * @return `true` if the property has the `abstract` modifier, `false` otherwise.
     */
    fun hasAbstractModifier(): Boolean

    /**
     * Whether the property has open modifier.
     *
     * @return `true` if the property has the `open` modifier, `false` otherwise.
     */
    fun hasOpenModifier(): Boolean

    /**
     * Whether the property has final modifier.
     *
     * @return `true` if the property has the `final` modifier, `false` otherwise.
     */
    fun hasFinalModifier(): Boolean

    /**
     * Whether the property has actual modifier.
     *
     * @return `true` if the property has the `actual` modifier, `false` otherwise.
     */
    fun hasActualModifier(): Boolean

    /**
     * Whether the property has expect modifier.
     *
     * @return `true` if the property has the `expect` modifier, `false` otherwise.
     */
    fun hasExpectModifier(): Boolean

    /**
     * Whether the property has const modifier.
     *
     * @return `true` if the property has the `const` modifier, `false` otherwise.
     */
    fun hasConstModifier(): Boolean

    /**
     * Whatever property is an extension property.
     *
     * @return `true` if the property is an extension property, `false` otherwise.
     */
    fun isExtension(): Boolean

    /**
     * Whatever property has a delegate.
     *
     * @param name the name of the delegate (optional).
     * @return `true` if the property has a delegate matching the specified name (or any delegate if [name] is `null`), `false` otherwise.
     */
    fun hasDelegate(name: String? = null): Boolean

    /**
     * Whatever property has a type.
     *
     * @param type the type to check for (optional).
     * @return `true` if the property has the specified type (or any type if [type] is `null`), `false` otherwise.
     */
    fun hasType(type: String? = null): Boolean
}
