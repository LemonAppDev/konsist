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
): List<T> = withModifier(listOf(modifier, *modifiers))

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
            else -> it.hasModifier(modifiers)
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
): List<T> = withoutModifier(listOf(modifier, *modifiers))

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
            else -> it.hasModifier(modifiers)
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
): List<T> = withAllModifiers(listOf(modifier, *modifiers))

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
            else -> it.hasAllModifiers(modifiers)
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
): List<T> = withoutAllModifiers(listOf(modifier, *modifiers))

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
            else -> it.hasAllModifiers(modifiers)
        }
    }
