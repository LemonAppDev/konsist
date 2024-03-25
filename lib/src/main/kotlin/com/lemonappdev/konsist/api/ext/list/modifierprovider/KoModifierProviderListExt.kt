package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider

/**
 * List containing modifiers.
 */
val <T : KoModifierProvider> List<T>.modifiers: List<KoModifier>
    get() = flatMap { it.modifiers }

/**
 * List containing declarations with any specified modifier.
 *
 * @return A list containing declarations with any modifier.
 */
fun <T : KoModifierProvider> List<T>.withModifiers(): List<T> = filter { it.hasModifiers() }

/**
 * List containing declarations with no modifier.
 *
 * @return A list containing declarations with no modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutModifiers(): List<T> = filterNot { it.hasModifiers() }

/**
 * List containing declarations with all the specified modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A list containing declarations with all the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withModifier(
    modifier: KoModifier,
    vararg modifiers: KoModifier,
): List<T> = filter { it.hasModifier(modifier, *modifiers) }

/**
 * List containing declarations with all the specified modifiers.
 *
 * @param modifiers The modifiers to include.
 * @return A list containing declarations with all the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withModifier(modifiers: Collection<KoModifier>): List<T> =
    filter {
        when {
            modifiers.isEmpty() -> it.hasModifiers()
            else -> it.hasModifier(modifiers.first(), *modifiers.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations without all specified modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A list containing declarations without all the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withoutModifier(
    modifier: KoModifier,
    vararg modifiers: KoModifier,
): List<T> = filterNot { it.hasModifier(modifier, *modifiers) }

/**
 * List containing declarations with all the specified modifiers.
 *
 * @param modifiers The modifiers to include.
 * @return A list containing declarations with all the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withoutModifier(modifiers: Collection<KoModifier>): List<T> =
    filterNot {
        when {
            modifiers.isEmpty() -> it.hasModifiers()
            else -> it.hasModifier(modifiers.first(), *modifiers.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations with all the specified modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A list containing declarations with all the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withAllModifiers(
    modifier: KoModifier,
    vararg modifiers: KoModifier,
): List<T> = filter { it.hasAllModifiers(modifier, *modifiers) }

/**
 * List containing declarations with all the specified modifiers.
 *
 * @param modifiers The modifiers to include.
 * @return A list containing declarations with all the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withAllModifiers(modifiers: Collection<KoModifier>): List<T> =
    filter {
        when {
            modifiers.isEmpty() -> it.hasModifiers()
            else -> it.hasAllModifiers(modifiers.first(), *modifiers.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations without all specified modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A list containing declarations without all the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withoutAllModifiers(
    modifier: KoModifier,
    vararg modifiers: KoModifier,
): List<T> = filterNot { it.hasAllModifiers(modifier, *modifiers) }

/**
 * List containing declarations without all specified modifiers.
 *
 * @param modifiers The modifiers to exclude.
 * @return A list containing declarations without all the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withoutAllModifiers(modifiers: Collection<KoModifier>): List<T> =
    filterNot {
        when {
            modifiers.isEmpty() -> it.hasModifiers()
            else -> it.hasAllModifiers(modifiers.first(), *modifiers.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations with some modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A list containing declarations with at least one of the specified modifiers.
 */
@Deprecated("Will be removed in v0.16.0.", ReplaceWith("withModifier(modifier, modifiers)"))
fun <T : KoModifierProvider> List<T>.withSomeModifiers(
    modifier: KoModifier,
    vararg modifiers: KoModifier,
): List<T> =
    filter {
        it.hasModifiers(modifier) || modifiers.any { modifier -> it.hasModifiers(modifier) }
    }

/**
 * List containing declarations without some modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A list containing declarations without at least one of the specified modifiers.
 */
@Deprecated("Will be removed in v0.16.0.", ReplaceWith("withoutModifier(modifier, modifiers)"))
fun <T : KoModifierProvider> List<T>.withoutSomeModifiers(
    modifier: KoModifier,
    vararg modifiers: KoModifier,
): List<T> =
    filter {
        val missesAtLeastOneModifier =
            if (modifiers.isNotEmpty()) {
                modifiers.any { modifier -> !it.hasModifiers(modifier) }
            } else {
                true
            }

        !it.hasModifiers(modifier) && missesAtLeastOneModifier
    }
