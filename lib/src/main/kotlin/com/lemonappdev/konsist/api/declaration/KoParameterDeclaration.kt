package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Represents a Kotlin parameter declaration.
 */
interface KoParameterDeclaration :
    KoDeclaration ,
    KoBaseDeclaration,
    KoParentProvider {
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
     *
     * @return `true` if the parameter has the `vararg` modifier, `false` otherwise.
     */
    fun hasVarargModifier(): Boolean

    /**
     * Whether the parameter has noinline modifier.
     *
     * @return `true` if the parameter has the `noinline` modifier, `false` otherwise.
     */
    fun hasNoInlineModifier(): Boolean

    /**
     * Whether the parameter has crossinline modifier.
     *
     * @return `true` if the parameter has the `crossinline` modifier, `false` otherwise.
     */
    fun hasCrossInlineModifier(): Boolean

    /**
     * Whether the parameter ha the default value.
     *
     * @param value the default value to check (optional).
     * @return `true` if the parameter has the specified default value (or any default value if [value] is `null`), `false` otherwise.
     */
    fun hasDefaultValue(value: String? = null): Boolean

    /**
     * Whether the parameter type represents the specified type.
     *
     * @param name the type to compare. It can be either a simple name or a fully qualified name.
     * @return `true` if this type represents the specified type, `false` otherwise.
     */
    fun representsType(name: String): Boolean
}
