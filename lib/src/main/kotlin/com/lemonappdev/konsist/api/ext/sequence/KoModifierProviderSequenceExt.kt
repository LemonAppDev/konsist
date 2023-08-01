package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.KoModifierProvider

/**
 * List containing declarations with any specified modifier.
 *
 * @return A list containing declarations with any modifier.
 */
fun <T : KoModifierProvider> List<T>.withModifiers(): List<T> = filter { it.hasModifiers() }

/**
 * List containing declarations with all the specified modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A list containing declarations with all the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withAllModifiers(modifier: KoModifier, vararg modifiers: KoModifier): List<T> = filter {
    it.hasModifiers(modifier, *modifiers)
}

/**
 * List containing declarations with some modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A list containing declarations with at least one of the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withSomeModifiers(modifier: KoModifier, vararg modifiers: KoModifier): List<T> = filter {
    it.hasModifiers(modifier) || modifiers.any { modifier -> it.hasModifiers(modifier) }
}

/**
 * List containing declarations with no modifier.
 *
 * @return A list containing declarations with no modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutModifiers(): List<T> = filterNot { it.hasModifiers() }

/**
 * List containing declarations without all specified modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A list containing declarations without all the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withoutAllModifiers(modifier: KoModifier, vararg modifiers: KoModifier): List<T> = filterNot {
    it.hasModifiers(modifier, *modifiers)
}

/**
 * List containing declarations without some modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A list containing declarations without at least one of the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withoutSomeModifiers(modifier: KoModifier, vararg modifiers: KoModifier): List<T> = filter {
    !it.hasModifiers(modifier) && if (modifiers.isNotEmpty()) {
        modifiers.any { modifier -> !it.hasModifiers(modifier) }
    } else {
        true
    }
}

/**
 * List containing declarations with the `public` modifier.
 *
 * @return A list containing declarations with the `public` modifier.
 */
fun <T : KoModifierProvider> List<T>.withPublicModifier(): List<T> = filter { it.hasPublicModifier }

/**
 * List containing declarations without `public` modifier.
 *
 * @return A list containing declarations without the `public` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutPublicModifier(): List<T> = filterNot { it.hasPublicModifier }

/**
 * List containing declarations with the `public` or no visibility modifier.
 *
 * @return A list containing declarations with the `public` or no visibility modifier..
 */
fun <T : KoModifierProvider> List<T>.withPublicOrDefaultModifier(): List<T> = filter { it.isPublicOrDefault }

/**
 * List containing declarations without `public` or no visibility modifier.
 *
 * @return A list containing declarations without the `public` or no visibility modifier..
 */
fun <T : KoModifierProvider> List<T>.withoutPublicOrDefaultModifier(): List<T> = filterNot { it.isPublicOrDefault }

/**
 * List containing declarations with the `private` modifier.
 *
 * @return A list containing declarations with the `private` modifier.
 */
fun <T : KoModifierProvider> List<T>.withPrivateModifier(): List<T> = filter { it.hasPrivateModifier }

/**
 * List containing declarations without `private` modifier.
 *
 * @return A list containing declarations without the `private` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutPrivateModifier(): List<T> = filterNot { it.hasPrivateModifier }

/**
 * List containing declarations with the `protected` modifier.
 *
 * @return A list containing declarations with the `protected` modifier.
 */
fun <T : KoModifierProvider> List<T>.withProtectedModifier(): List<T> = filter { it.hasProtectedModifier }

/**
 * List containing declarations without `protected` modifier.
 *
 * @return A list containing declarations without the `protected` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutProtectedModifier(): List<T> = filterNot { it.hasProtectedModifier }

/**
 * List containing declarations with the `internal` modifier.
 *
 * @return A list containing declarations with the `internal` modifier.
 */
fun <T : KoModifierProvider> List<T>.withInternalModifier(): List<T> = filter { it.hasInternalModifier }

/**
 * List containing declarations without `internal` modifier.
 *
 * @return A list containing declarations without the `internal` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutInternalModifier(): List<T> = filterNot { it.hasInternalModifier }

/**
 * List containing all declarations that have `enum` modifier.
 *
 * @return A list containing declarations with the `enum` modifier.
 */
fun <T : KoModifierProvider> List<T>.withEnumModifier(): List<T> = filter { it.hasEnumModifier }

/**
 * List containing all declarations that don't have `enum` modifier.
 *
 * @return A list containing declarations without the `enum` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutEnumModifier(): List<T> = filterNot { it.hasEnumModifier }

/**
 * List containing all declarations that have `sealed` modifier.
 *
 * @return A list containing declarations with the `sealed` modifier.
 */
fun <T : KoModifierProvider> List<T>.withSealedModifier(): List<T> = filter { it.hasSealedModifier }

/**
 * List containing all declarations that don't have `sealed` modifier.
 *
 * @return A list containing declarations without the `sealed` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutSealedModifier(): List<T> = filterNot { it.hasSealedModifier }

/**
 * List containing all declarations that have `inner` modifier.
 *
 * @return A list containing declarations with the `inner` modifier.
 */
fun <T : KoModifierProvider> List<T>.withInnerModifier(): List<T> = filter { it.hasInnerModifier }

/**
 * List containing all declarations that don't have `inner` modifier.
 *
 * @return A list containing declarations without the `inner` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutInnerModifier(): List<T> = filterNot { it.hasInnerModifier }

/**
 * List containing all declarations that have `value` modifier.
 *
 * @return A list containing declarations with the `value` modifier.
 */
fun <T : KoModifierProvider> List<T>.withValueModifier(): List<T> = filter { it.hasValueModifier }

/**
 * List containing all declarations that don't have `value` modifier.
 *
 * @return A list containing declarations without the `value` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutValueModifier(): List<T> = filterNot { it.hasValueModifier }

/**
 * List containing all declarations that have `annotation` modifier.
 *
 * @return A list containing declarations with the `annotation` modifier.
 */
fun <T : KoModifierProvider> List<T>.withAnnotationModifier(): List<T> = filter { it.hasAnnotationModifier }

/**
 * List containing all declarations that don't have `annotation` modifier.
 *
 * @return A list containing declarations without the `annotation` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutAnnotationModifier(): List<T> = filterNot { it.hasAnnotationModifier }

/**
 * List containing all declarations that have `data` modifier.
 *
 * @return A list containing declarations with the `data` modifier.
 */
fun <T : KoModifierProvider> List<T>.withDataModifier(): List<T> = filter { it.hasDataModifier }

/**
 * List containing all declarations that don't have `data` modifier.
 *
 * @return A list containing declarations without the `data` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutDataModifier(): List<T> = filterNot { it.hasDataModifier }

/**
 * List containing all declarations that have `actual` modifier.
 *
 * @return A list containing declarations with the `actual` modifier.
 */
fun <T : KoModifierProvider> List<T>.withActualModifier(): List<T> = filter { it.hasActualModifier }

/**
 * List containing all declarations that don't have `actual` modifier.
 *
 * @return A list containing declarations without the `actual` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutActualModifier(): List<T> = filterNot { it.hasActualModifier }

/**
 * List containing all declarations that have `expect` modifier.
 *
 * @return A list containing declarations with the `expect` modifier.
 */
fun <T : KoModifierProvider> List<T>.withExpectModifier(): List<T> = filter { it.hasExpectModifier }

/**
 * List containing all declarations that don't have `expect` modifier.
 *
 * @return A list containing declarations without the `expect` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutExpectModifier(): List<T> = filterNot { it.hasExpectModifier }

/**
 * List containing all declarations that have `abstract` modifier.
 *
 * @return A list containing declarations with the `abstract` modifier.
 */
fun <T : KoModifierProvider> List<T>.withAbstractModifier(): List<T> = filter { it.hasAbstractModifier }

/**
 * List containing all declarations that don't have `abstract` modifier.
 *
 * @return A list containing declarations without the `abstract` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutAbstractModifier(): List<T> = filterNot { it.hasAbstractModifier }

/**
 * List containing all declarations that have `open` modifier.
 *
 * @return A list containing declarations with the `open` modifier.
 */
fun <T : KoModifierProvider> List<T>.withOpenModifier(): List<T> = filter { it.hasOpenModifier }

/**
 * List containing all declarations that don't have `open` modifier.
 *
 * @return A list containing declarations without the `open` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutOpenModifier(): List<T> = filterNot { it.hasOpenModifier }

/**
 * List containing all declarations that have `final` modifier.
 *
 * @return A list containing declarations with the `final` modifier.
 */
fun <T : KoModifierProvider> List<T>.withFinalModifier(): List<T> = filter { it.hasFinalModifier }

/**
 * List containing all declarations that don't have `final` modifier.
 *
 * @return A list containing declarations without the `final` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutFinalModifier(): List<T> = filterNot { it.hasFinalModifier }

/**
 * List containing all declarations with `vararg` modifier.
 *
 * @return A list containing declarations with the `vararg` modifier.
 */
fun <T : KoModifierProvider> List<T>.withVarargModifier(): List<T> = filter { it.hasVarargModifier }

/**
 * List containing all declarations without `vararg` modifier.
 *
 * @return A list containing declarations without the `vararg` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutVarargModifier(): List<T> = filterNot { it.hasVarargModifier }

/**
 * List containing all declarations with `noinline` modifier.
 *
 * @return A list containing declarations with the `noinline` modifier.
 */
fun <T : KoModifierProvider> List<T>.withNoInlineModifier(): List<T> = filter { it.hasNoInlineModifier }

/**
 * List containing all declarations without `noinline` modifier.
 *
 * @return A list containing declarations without the `noinline` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutNoInlineModifier(): List<T> = filterNot { it.hasNoInlineModifier }

/**
 * List containing all declarations with `crossinline` modifier.
 *
 * @return A list containing declarations with the `crossinline` modifier.
 */
fun <T : KoModifierProvider> List<T>.withCrossInlineModifier(): List<T> = filter { it.hasCrossInlineModifier }

/**
 * List containing all declarations without `crossinline` modifier.
 *
 * @return A list containing declarations without the `crossinline` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutCrossInlineModifier(): List<T> =
    filterNot { it.hasCrossInlineModifier }

/**
 * List containing declarations with `operator` modifier.
 *
 * @return A list containing declarations with the `operator` modifier.
 */
fun <T : KoModifierProvider> List<T>.withOperatorModifier(): List<T> = filter { it.hasOperatorModifier }

/**
 * List containing declarations without `operator` modifier.
 *
 * @return A list containing declarations without the `operator` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutOperatorModifier(): List<T> = filterNot { it.hasOperatorModifier }

/**
 * List containing declarations with `inline` modifier.
 *
 * @return A list containing declarations with the `inline` modifier.
 */
fun <T : KoModifierProvider> List<T>.withInlineModifier(): List<T> = filter { it.hasInlineModifier }

/**
 * List containing declarations without `inline` modifier.
 *
 * @return A list containing declarations without the `inline` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutInlineModifier(): List<T> = filterNot { it.hasInlineModifier }

/**
 * List containing declarations with `tailrec` modifier.
 *
 * @return A list containing declarations with the `tailrec` modifier.
 */
fun <T : KoModifierProvider> List<T>.withTailrecModifier(): List<T> = filter { it.hasTailrecModifier }

/**
 * List containing declarations without `tailrec` modifier.
 *
 * @return A list containing declarations without the `tailrec` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutTailrecModifier(): List<T> = filterNot { it.hasTailrecModifier }

/**
 * List containing declarations with `infix` modifier.
 *
 * @return A list containing declarations with the `infix` modifier.
 */
fun <T : KoModifierProvider> List<T>.withInfixModifier(): List<T> = filter { it.hasInfixModifier }

/**
 * List containing declarations without `infix` modifier.
 *
 * @return A list containing declarations without the `infix` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutInfixModifier(): List<T> = filterNot { it.hasInfixModifier }

/**
 * List containing declarations with `external` modifier.
 *
 * @return A list containing declarations with the `external` modifier.
 */
fun <T : KoModifierProvider> List<T>.withExternalModifier(): List<T> = filter { it.hasExternalModifier }

/**
 * List containing declarations without `external` modifier.
 *
 * @return A list containing declarations without the `external` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutExternalModifier(): List<T> = filterNot { it.hasExternalModifier }

/**
 * List containing declarations with `suspend` modifier.
 *
 * @return A list containing declarations with the `suspend` modifier.
 */
fun <T : KoModifierProvider> List<T>.withSuspendModifier(): List<T> = filter { it.hasSuspendModifier }

/**
 * List containing declarations without `suspend` modifier.
 *
 * @return A list containing declarations without the `suspend` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutSuspendModifier(): List<T> = filterNot { it.hasSuspendModifier }

/**
 * List containing declarations with `override` modifier.
 *
 * @return A list containing declarations with the `override` modifier.
 */
fun <T : KoModifierProvider> List<T>.withOverrideModifier(): List<T> = filter { it.hasOverrideModifier }

/**
 * List containing declarations without `override` modifier.
 *
 * @return A list containing declarations without the `override` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutOverrideModifier(): List<T> = filterNot { it.hasOverrideModifier }

/**
 * List containing all declarations with `fun` modifier.
 *
 * @return A list containing declarations with the `fun` modifier.
 */
fun <T : KoModifierProvider> List<T>.withFunModifier(): List<T> = filter { it.hasFunModifier }

/**
 * List containing all declarations without `fun` modifier.
 *
 * @return A list containing declarations without the `fun` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutFunModifier(): List<T> = filterNot { it.hasFunModifier }

/**
 * List containing declarations with `lateinit` modifier.
 *
 * @return A list containing declarations with the `lateinit` modifier.
 */
fun <T : KoModifierProvider> List<T>.withLateinitModifier(): List<T> = filter { it.hasLateinitModifier }

/**
 * List containing declarations without `lateinit` modifier.
 *
 * @return A list containing declarations without the `lateinit` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutLateinitModifier(): List<T> = filterNot { it.hasLateinitModifier }

/**
 * List containing declarations with `const` modifier.
 *
 * @return A list containing declarations with the `const` modifier.
 */
fun <T : KoModifierProvider> List<T>.withConstModifier(): List<T> = filter { it.hasConstModifier }

/**
 * List containing declarations without `const` modifier.
 *
 * @return A list containing declarations without the `const` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutConstModifier(): List<T> = filterNot { it.hasConstModifier }

/**
 * List containing all declarations with `companion` modifier.
 *
 * @return A list containing declarations with the `companion` modifier.
 */
fun <T : KoModifierProvider> List<T>.withCompanionModifier(): List<T> = filter { it.hasCompanionModifier }

/**
 * List containing all declarations without `companion` modifier.
 *
 * @return A list containing declarations without the `companion` modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutCompanionModifier(): List<T> = filterNot { it.hasCompanionModifier }
