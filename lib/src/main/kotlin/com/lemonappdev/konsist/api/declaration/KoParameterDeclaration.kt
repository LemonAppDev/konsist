package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin parameter declaration.
 */
interface KoParameterDeclaration : KoDeclaration {
    /**
     * Type of the parameter.
     */
    val type: KoTypeDeclaration

    /**
     * Default value of the parameter.
     */
    val defaultValue: String?

    /**
     * Whether the parameter has vararg modifier.
     */
    fun hasVarargModifier(): Boolean

    /**
     * Whether the parameter has noinline modifier.
     */
    fun hasNoInlineModifier(): Boolean

    /**
     * Whether the parameter has crossinline modifier.
     */
    fun hasCrossInlineModifier(): Boolean

    /**
     * Whether the parameter ha the default value.
     */
    fun hasDefaultValue(value: String? = null): Boolean

    /**
     * Whether this type represents the specified type.
     */
    fun representsType(type: String): Boolean
}
