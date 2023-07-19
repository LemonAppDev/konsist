package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.KoModifier

@Suppress("detekt.TooManyFunctions")
interface KoModifierProvider : KoProvider {
    /**
     * List of modifiers.
     */
    val modifiers: List<KoModifier>

    /**
     * Whether the declaration has modifiers.
     *
     * @param koModifiers the modifiers to check.
     * @return `true` if the declaration has all the specified modifiers (or any modifier if [koModifiers] is empty), `false` otherwise.
     */
    fun hasModifiers(vararg koModifiers: KoModifier): Boolean

    /**
     * Whether the declaration has public modifier.
     *
     * @return `true` if the declaration has the `public` modifier, `false` otherwise.
     */
    fun hasPublicModifier(): Boolean

    /**
     * Whether the declaration has public or no visibility modifier.
     *
     * @return `true` if the declaration has the `public` or no visibility modifier, `false` otherwise.
     */
    fun isPublicOrDefault(): Boolean

    /**
     * Whether the declaration has private modifier.
     *
     * @return `true` if the declaration has the `private` modifier, `false` otherwise.
     */
    fun hasPrivateModifier(): Boolean

    /**
     * Whether the declaration has protected modifier.
     *
     * @return `true` if the declaration has the `protected` modifier, `false` otherwise.
     */
    fun hasProtectedModifier(): Boolean

    /**
     * Whether the declaration has internal modifier.
     *
     * @return `true` if the declaration has the `internal` modifier, `false` otherwise.
     */
    fun hasInternalModifier(): Boolean

    /**
     * Whatever declaration has `enum` modifier.
     *
     * @return `true` if the declaration has the `enum` modifier, `false` otherwise.
     */
    fun hasEnumModifier(): Boolean

    /**
     * Whatever declaration has `sealed` modifier.
     *
     * @return `true` if the declaration has the `sealed` modifier, `false` otherwise.
     */
    fun hasSealedModifier(): Boolean

    /**
     * Whatever declaration has `inner` modifier.
     *
     * @return `true` if the declaration has the `inner` modifier, `false` otherwise.
     */
    fun hasInnerModifier(): Boolean

    /**
     * Whatever declaration has `value` modifier.
     *
     * @return `true` if the declaration has the `value` modifier, `false` otherwise.
     */
    fun hasValueModifier(): Boolean

    /**
     * Whatever declaration has `annotation` modifier.
     *
     * @return `true` if the declaration has the `annotation` modifier, `false` otherwise.
     */
    fun hasAnnotationModifier(): Boolean

    /**
     * Whatever declaration has `data` modifier.
     *
     * @return `true` if the declaration has the `data` modifier, `false` otherwise.
     */
    fun hasDataModifier(): Boolean

    /**
     * Whatever class has `actual` modifier.
     *
     * @return `true` if the class has the `actual` modifier, `false` otherwise.
     */
    fun hasActualModifier(): Boolean

    /**
     * Whatever declaration has `expect` modifier.
     *
     * @return `true` if the declaration has the `expect` modifier, `false` otherwise.
     */
    fun hasExpectModifier(): Boolean

    /**
     * Whatever declaration has `abstract` modifier.
     *
     * @return `true` if the declaration has the `abstract` modifier, `false` otherwise.
     */
    fun hasAbstractModifier(): Boolean

    /**
     * Whatever declaration has `open` modifier.
     *
     * @return `true` if the declaration has the `open` modifier, `false` otherwise.
     */
    fun hasOpenModifier(): Boolean

    /**
     * Whatever declaration has `final` modifier.
     *
     * @return `true` if the declaration has the `final` modifier, `false` otherwise.
     */
    fun hasFinalModifier(): Boolean

    /**
     * Whether the declaration has vararg modifier.
     *
     * @return `true` if the declaration has the `vararg` modifier, `false` otherwise.
     */
    fun hasVarargModifier(): Boolean

    /**
     * Whether the declaration has noinline modifier.
     *
     * @return `true` if the declaration has the `noinline` modifier, `false` otherwise.
     */
    fun hasNoInlineModifier(): Boolean

    /**
     * Whether the declaration has crossinline modifier.
     *
     * @return `true` if the declaration has the `crossinline` modifier, `false` otherwise.
     */
    fun hasCrossInlineModifier(): Boolean

    /**
     * Whether the declaration has operator modifier.
     *
     * @return `true` if the declaration has the `operator` modifier, `false` otherwise.
     */
    fun hasOperatorModifier(): Boolean

    /**
     * Whether the declaration has inline modifier.
     *
     * @return `true` if the declaration has the `inline` modifier, `false` otherwise.
     */
    fun hasInlineModifier(): Boolean

    /**
     * Whether the declaration has tailrec modifier.
     *
     * @return `true` if the declaration has the `tailrec` modifier, `false` otherwise.
     */
    fun hasTailrecModifier(): Boolean

    /**
     * Whether the declaration has infix modifier.
     *
     * @return `true` if the declaration has the `infix` modifier, `false` otherwise.
     */
    fun hasInfixModifier(): Boolean

    /**
     * Whether the declaration has external modifier.
     *
     * @return `true` if the declaration has the `external` modifier, `false` otherwise.
     */
    fun hasExternalModifier(): Boolean

    /**
     * Whether the declaration has suspend modifier.
     *
     * @return `true` if the declaration has the `suspend` modifier, `false` otherwise.
     */
    fun hasSuspendModifier(): Boolean

    /**
     * Whether the declaration has override modifier.
     *
     * @return `true` if the declaration has the `override` modifier, `false` otherwise.
     */
    fun hasOverrideModifier(): Boolean

    /**
     * Whatever declaration has a `fun` modifier.
     *
     * @return `true` if the declaration has the `fun` modifier, `false` otherwise.
     */
    fun hasFunModifier(): Boolean

    /**
     * Whether the declaration has lateinit modifier.
     *
     * @return `true` if the declaration has the `lateinit` modifier, `false` otherwise.
     */
    fun hasLateinitModifier(): Boolean

    /**
     * Whether the declaration has const modifier.
     *
     * @return `true` if the declaration has the `const` modifier, `false` otherwise.
     */
    fun hasConstModifier(): Boolean

    /**
     * Whether the declaration has a companion modifier.
     *
     * @return `true` if the declaration has the `companion` modifier, `false` otherwise.
     */
    fun hasCompanionModifier(): Boolean
}
