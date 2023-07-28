package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.KoModifierProvider

/**
 * Sequence containing declarations with any specified modifier.
 *
 * @return A sequence containing declarations with any modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withModifiers(): Sequence<T> = filter { it.hasModifiers() }

/**
 * Sequence containing declarations with all the specified modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A sequence containing declarations with all the specified modifiers.
 */
fun <T : KoModifierProvider> Sequence<T>.withAllModifiers(modifier: KoModifier, vararg modifiers: KoModifier): Sequence<T> = filter {
    it.hasModifiers(modifier, *modifiers)
}

/**
 * Sequence containing declarations with some modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A sequence containing declarations with at least one of the specified modifiers.
 */
fun <T : KoModifierProvider> Sequence<T>.withSomeModifiers(modifier: KoModifier, vararg modifiers: KoModifier): Sequence<T> = filter {
    it.hasModifiers(modifier) || modifiers.any { modifier -> it.hasModifiers(modifier) }
}

/**
 * Sequence containing declarations with no modifier.
 *
 * @return A sequence containing declarations with no modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutModifiers(): Sequence<T> = filterNot { it.hasModifiers() }

/**
 * Sequence containing declarations without all specified modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A sequence containing declarations without all the specified modifiers.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutAllModifiers(modifier: KoModifier, vararg modifiers: KoModifier): Sequence<T> = filterNot {
    it.hasModifiers(modifier, *modifiers)
}

/**
 * Sequence containing declarations without some modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A sequence containing declarations without at least one of the specified modifiers.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutSomeModifiers(modifier: KoModifier, vararg modifiers: KoModifier): Sequence<T> = filter {
    !it.hasModifiers(modifier) && if (modifiers.isNotEmpty()) {
        modifiers.any { modifier -> !it.hasModifiers(modifier) }
    } else {
        true
    }
}

/**
 * Sequence containing declarations with the `public` modifier.
 *
 * @return A sequence containing declarations with the `public` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withPublicModifier(): Sequence<T> = filter { it.hasPublicModifier() }

/**
 * Sequence containing declarations without `public` modifier.
 *
 * @return A sequence containing declarations without the `public` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutPublicModifier(): Sequence<T> = filterNot { it.hasPublicModifier() }

/**
 * Sequence containing declarations with the `public` or no visibility modifier.
 *
 * @return A sequence containing declarations with the `public` or no visibility modifier..
 */
fun <T : KoModifierProvider> Sequence<T>.withPublicOrDefaultModifier(): Sequence<T> = filter { it.isPublicOrDefault() }

/**
 * Sequence containing declarations without `public` or no visibility modifier.
 *
 * @return A sequence containing declarations without the `public` or no visibility modifier..
 */
fun <T : KoModifierProvider> Sequence<T>.withoutPublicOrDefaultModifier(): Sequence<T> = filterNot { it.isPublicOrDefault() }

/**
 * Sequence containing declarations with the `private` modifier.
 *
 * @return A sequence containing declarations with the `private` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withPrivateModifier(): Sequence<T> = filter { it.hasPrivateModifier() }

/**
 * Sequence containing declarations without `private` modifier.
 *
 * @return A sequence containing declarations without the `private` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutPrivateModifier(): Sequence<T> = filterNot { it.hasPrivateModifier() }

/**
 * Sequence containing declarations with the `protected` modifier.
 *
 * @return A sequence containing declarations with the `protected` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withProtectedModifier(): Sequence<T> = filter { it.hasProtectedModifier() }

/**
 * Sequence containing declarations without `protected` modifier.
 *
 * @return A sequence containing declarations without the `protected` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutProtectedModifier(): Sequence<T> = filterNot { it.hasProtectedModifier() }

/**
 * Sequence containing declarations with the `internal` modifier.
 *
 * @return A sequence containing declarations with the `internal` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withInternalModifier(): Sequence<T> = filter { it.hasInternalModifier() }

/**
 * Sequence containing declarations without `internal` modifier.
 *
 * @return A sequence containing declarations without the `internal` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutInternalModifier(): Sequence<T> = filterNot { it.hasInternalModifier() }

/**
 * Sequence containing all declarations that have `enum` modifier.
 *
 * @return A sequence containing declarations with the `enum` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withEnumModifier(): Sequence<T> = filter { it.hasEnumModifier() }

/**
 * Sequence containing all declarations that don't have `enum` modifier.
 *
 * @return A sequence containing declarations without the `enum` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutEnumModifier(): Sequence<T> = filterNot { it.hasEnumModifier() }

/**
 * Sequence containing all declarations that have `sealed` modifier.
 *
 * @return A sequence containing declarations with the `sealed` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withSealedModifier(): Sequence<T> = filter { it.hasSealedModifier() }

/**
 * Sequence containing all declarations that don't have `sealed` modifier.
 *
 * @return A sequence containing declarations without the `sealed` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutSealedModifier(): Sequence<T> = filterNot { it.hasSealedModifier() }

/**
 * Sequence containing all declarations that have `inner` modifier.
 *
 * @return A sequence containing declarations with the `inner` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withInnerModifier(): Sequence<T> = filter { it.hasInnerModifier() }

/**
 * Sequence containing all declarations that don't have `inner` modifier.
 *
 * @return A sequence containing declarations without the `inner` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutInnerModifier(): Sequence<T> = filterNot { it.hasInnerModifier() }

/**
 * Sequence containing all declarations that have `value` modifier.
 *
 * @return A sequence containing declarations with the `value` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withValueModifier(): Sequence<T> = filter { it.hasValueModifier() }

/**
 * Sequence containing all declarations that don't have `value` modifier.
 *
 * @return A sequence containing declarations without the `value` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutValueModifier(): Sequence<T> = filterNot { it.hasValueModifier() }

/**
 * Sequence containing all declarations that have `annotation` modifier.
 *
 * @return A sequence containing declarations with the `annotation` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withAnnotationModifier(): Sequence<T> = filter { it.hasAnnotationModifier() }

/**
 * Sequence containing all declarations that don't have `annotation` modifier.
 *
 * @return A sequence containing declarations without the `annotation` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutAnnotationModifier(): Sequence<T> = filterNot { it.hasAnnotationModifier() }

/**
 * Sequence containing all declarations that have `data` modifier.
 *
 * @return A sequence containing declarations with the `data` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withDataModifier(): Sequence<T> = filter { it.hasDataModifier() }

/**
 * Sequence containing all declarations that don't have `data` modifier.
 *
 * @return A sequence containing declarations without the `data` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutDataModifier(): Sequence<T> = filterNot { it.hasDataModifier() }

/**
 * Sequence containing all declarations that have `actual` modifier.
 *
 * @return A sequence containing declarations with the `actual` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withActualModifier(): Sequence<T> = filter { it.hasActualModifier() }

/**
 * Sequence containing all declarations that don't have `actual` modifier.
 *
 * @return A sequence containing declarations without the `actual` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutActualModifier(): Sequence<T> = filterNot { it.hasActualModifier() }

/**
 * Sequence containing all declarations that have `expect` modifier.
 *
 * @return A sequence containing declarations with the `expect` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withExpectModifier(): Sequence<T> = filter { it.hasExpectModifier() }

/**
 * Sequence containing all declarations that don't have `expect` modifier.
 *
 * @return A sequence containing declarations without the `expect` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutExpectModifier(): Sequence<T> = filterNot { it.hasExpectModifier() }

/**
 * Sequence containing all declarations that have `abstract` modifier.
 *
 * @return A sequence containing declarations with the `abstract` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withAbstractModifier(): Sequence<T> = filter { it.hasAbstractModifier() }

/**
 * Sequence containing all declarations that don't have `abstract` modifier.
 *
 * @return A sequence containing declarations without the `abstract` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutAbstractModifier(): Sequence<T> = filterNot { it.hasAbstractModifier() }

/**
 * Sequence containing all declarations that have `open` modifier.
 *
 * @return A sequence containing declarations with the `open` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withOpenModifier(): Sequence<T> = filter { it.hasOpenModifier() }

/**
 * Sequence containing all declarations that don't have `open` modifier.
 *
 * @return A sequence containing declarations without the `open` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutOpenModifier(): Sequence<T> = filterNot { it.hasOpenModifier() }

/**
 * Sequence containing all declarations that have `final` modifier.
 *
 * @return A sequence containing declarations with the `final` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withFinalModifier(): Sequence<T> = filter { it.hasFinalModifier() }

/**
 * Sequence containing all declarations that don't have `final` modifier.
 *
 * @return A sequence containing declarations without the `final` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutFinalModifier(): Sequence<T> = filterNot { it.hasFinalModifier() }

/**
 * Sequence containing all declarations with `vararg` modifier.
 *
 * @return A sequence containing declarations with the `vararg` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withVarargModifier(): Sequence<T> = filter { it.hasVarargModifier() }

/**
 * Sequence containing all declarations without `vararg` modifier.
 *
 * @return A sequence containing declarations without the `vararg` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutVarargModifier(): Sequence<T> = filterNot { it.hasVarargModifier() }

/**
 * Sequence containing all declarations with `noinline` modifier.
 *
 * @return A sequence containing declarations with the `noinline` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withNoInlineModifier(): Sequence<T> = filter { it.hasNoInlineModifier() }

/**
 * Sequence containing all declarations without `noinline` modifier.
 *
 * @return A sequence containing declarations without the `noinline` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutNoInlineModifier(): Sequence<T> = filterNot { it.hasNoInlineModifier() }

/**
 * Sequence containing all declarations with `crossinline` modifier.
 *
 * @return A sequence containing declarations with the `crossinline` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withCrossInlineModifier(): Sequence<T> = filter { it.hasCrossInlineModifier() }

/**
 * Sequence containing all declarations without `crossinline` modifier.
 *
 * @return A sequence containing declarations without the `crossinline` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutCrossInlineModifier(): Sequence<T> =
    filterNot { it.hasCrossInlineModifier() }

/**
 * Sequence containing declarations with `operator` modifier.
 *
 * @return A sequence containing declarations with the `operator` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withOperatorModifier(): Sequence<T> = filter { it.hasOperatorModifier() }

/**
 * Sequence containing declarations without `operator` modifier.
 *
 * @return A sequence containing declarations without the `operator` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutOperatorModifier(): Sequence<T> = filterNot { it.hasOperatorModifier() }

/**
 * Sequence containing declarations with `inline` modifier.
 *
 * @return A sequence containing declarations with the `inline` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withInlineModifier(): Sequence<T> = filter { it.hasInlineModifier() }

/**
 * Sequence containing declarations without `inline` modifier.
 *
 * @return A sequence containing declarations without the `inline` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutInlineModifier(): Sequence<T> = filterNot { it.hasInlineModifier() }

/**
 * Sequence containing declarations with `tailrec` modifier.
 *
 * @return A sequence containing declarations with the `tailrec` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withTailrecModifier(): Sequence<T> = filter { it.hasTailrecModifier() }

/**
 * Sequence containing declarations without `tailrec` modifier.
 *
 * @return A sequence containing declarations without the `tailrec` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutTailrecModifier(): Sequence<T> = filterNot { it.hasTailrecModifier() }

/**
 * Sequence containing declarations with `infix` modifier.
 *
 * @return A sequence containing declarations with the `infix` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withInfixModifier(): Sequence<T> = filter { it.hasInfixModifier() }

/**
 * Sequence containing declarations without `infix` modifier.
 *
 * @return A sequence containing declarations without the `infix` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutInfixModifier(): Sequence<T> = filterNot { it.hasInfixModifier() }

/**
 * Sequence containing declarations with `external` modifier.
 *
 * @return A sequence containing declarations with the `external` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withExternalModifier(): Sequence<T> = filter { it.hasExternalModifier() }

/**
 * Sequence containing declarations without `external` modifier.
 *
 * @return A sequence containing declarations without the `external` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutExternalModifier(): Sequence<T> = filterNot { it.hasExternalModifier() }

/**
 * Sequence containing declarations with `suspend` modifier.
 *
 * @return A sequence containing declarations with the `suspend` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withSuspendModifier(): Sequence<T> = filter { it.hasSuspendModifier() }

/**
 * Sequence containing declarations without `suspend` modifier.
 *
 * @return A sequence containing declarations without the `suspend` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutSuspendModifier(): Sequence<T> = filterNot { it.hasSuspendModifier() }

/**
 * Sequence containing declarations with `override` modifier.
 *
 * @return A sequence containing declarations with the `override` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withOverrideModifier(): Sequence<T> = filter { it.hasOverrideModifier() }

/**
 * Sequence containing declarations without `override` modifier.
 *
 * @return A sequence containing declarations without the `override` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutOverrideModifier(): Sequence<T> = filterNot { it.hasOverrideModifier() }

/**
 * Sequence containing all declarations with `fun` modifier.
 *
 * @return A sequence containing declarations with the `fun` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withFunModifier(): Sequence<T> = filter { it.hasFunModifier() }

/**
 * Sequence containing all declarations without `fun` modifier.
 *
 * @return A sequence containing declarations without the `fun` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutFunModifier(): Sequence<T> = filterNot { it.hasFunModifier() }

/**
 * Sequence containing declarations with `lateinit` modifier.
 *
 * @return A sequence containing declarations with the `lateinit` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withLateinitModifier(): Sequence<T> = filter { it.hasLateinitModifier() }

/**
 * Sequence containing declarations without `lateinit` modifier.
 *
 * @return A sequence containing declarations without the `lateinit` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutLateinitModifier(): Sequence<T> = filterNot { it.hasLateinitModifier() }

/**
 * Sequence containing declarations with `const` modifier.
 *
 * @return A sequence containing declarations with the `const` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withConstModifier(): Sequence<T> = filter { it.hasConstModifier() }

/**
 * Sequence containing declarations without `const` modifier.
 *
 * @return A sequence containing declarations without the `const` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutConstModifier(): Sequence<T> = filterNot { it.hasConstModifier() }

/**
 * Sequence containing all declarations with `companion` modifier.
 *
 * @return A sequence containing declarations with the `companion` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withCompanionModifier(): Sequence<T> = filter { it.hasCompanionModifier() }

/**
 * Sequence containing all declarations without `companion` modifier.
 *
 * @return A sequence containing declarations without the `companion` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutCompanionModifier(): Sequence<T> = filterNot { it.hasCompanionModifier() }
