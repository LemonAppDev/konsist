package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.KoModifier

/**
 * An interface representing a Kotlin declaration that provides access to its modifiers.
 */
@Suppress("detekt.TooManyFunctions")
interface KoModifierProvider : KoBaseProvider {
    /**
     * Sequence of modifiers.
     */
    val modifiers: Sequence<KoModifier>

    /**
     * Whether the declaration has modifiers.
     *
     * @param koModifiers the modifiers to check.
     * @return `true` if the declaration has all the specified modifiers (or any modifier if [koModifiers] is empty), `false` otherwise.
     */
    fun hasModifiers(vararg koModifiers: KoModifier): Boolean

    /**
     * Whether the declaration has public modifier.
     */
    val hasPublicModifier: Boolean

    /**
     * Whether the declaration has public or no visibility modifier.
     */
    val isPublicOrDefault: Boolean

    /**
     * Whether the declaration has private modifier.
     */
    val hasPrivateModifier: Boolean

    /**
     * Whether the declaration has protected modifier.
     */
    val hasProtectedModifier: Boolean

    /**
     * Whether the declaration has internal modifier.
     */
    val hasInternalModifier: Boolean

    /**
     * Whatever declaration has `enum` modifier.
     */
    val hasEnumModifier: Boolean

    /**
     * Whatever declaration has `sealed` modifier.
     */
    val hasSealedModifier: Boolean

    /**
     * Whatever declaration has `inner` modifier.
     */
    val hasInnerModifier: Boolean

    /**
     * Whatever declaration has `value` modifier.
     */
    val hasValueModifier: Boolean

    /**
     * Whatever declaration has `annotation` modifier.
     */
    val hasAnnotationModifier: Boolean

    /**
     * Whatever declaration has `data` modifier.
     */
    val hasDataModifier: Boolean

    /**
     * Whatever class has `actual` modifier.
     */
    val hasActualModifier: Boolean

    /**
     * Whatever declaration has `expect` modifier.
     */
    val hasExpectModifier: Boolean

    /**
     * Whatever declaration has `abstract` modifier.
     */
    val hasAbstractModifier: Boolean

    /**
     * Whatever declaration has `open` modifier.
     */
    val hasOpenModifier: Boolean

    /**
     * Whatever declaration has `final` modifier.
     */
    val hasFinalModifier: Boolean

    /**
     * Whether the declaration has vararg modifier.
     */
    val hasVarargModifier: Boolean

    /**
     * Whether the declaration has noinline modifier.
     */
    val hasNoInlineModifier: Boolean

    /**
     * Whether the declaration has crossinline modifier.
     */
    val hasCrossInlineModifier: Boolean

    /**
     * Whether the declaration has operator modifier.
     */
    val hasOperatorModifier: Boolean

    /**
     * Whether the declaration has inline modifier.
     */
    val hasInlineModifier: Boolean

    /**
     * Whether the declaration has tailrec modifier.
     */
    val hasTailrecModifier: Boolean

    /**
     * Whether the declaration has infix modifier.
     */
    val hasInfixModifier: Boolean

    /**
     * Whether the declaration has external modifier.
     */
    val hasExternalModifier: Boolean

    /**
     * Whether the declaration has suspend modifier.
     */
    val hasSuspendModifier: Boolean

    /**
     * Whether the declaration has override modifier.
     */
    val hasOverrideModifier: Boolean

    /**
     * Whatever declaration has a `fun` modifier.
     */
    val hasFunModifier: Boolean

    /**
     * Whether the declaration has lateinit modifier.
     */
    val hasLateinitModifier: Boolean

    /**
     * Whether the declaration has const modifier.
     */
    val hasConstModifier: Boolean

    /**
     * Whether the declaration has a companion modifier.
     */
    val hasCompanionModifier: Boolean
}
