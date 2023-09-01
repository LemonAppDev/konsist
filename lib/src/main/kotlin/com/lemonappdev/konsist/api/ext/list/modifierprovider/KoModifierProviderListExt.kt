@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider

/**
 * List containing modifiers.
 */
val <T : KoModifierProvider> List<T>.modifiers: List<KoModifier>
    get() = flatMap { it.modifiers }

/**
 * List containing elements with any specified modifier.
 *
 * @return A list containing elements with any modifier.
 */
fun <T : KoModifierProvider> List<T>.withModifiers(): List<T> = filter { it.hasModifiers() }

/**
 * List containing elements with all the specified modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A list containing elements with all the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withAllModifiers(modifier: KoModifier, vararg modifiers: KoModifier): List<T> = filter {
    it.hasModifiers(modifier, *modifiers)
}

/**
 * List containing elements with some modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A list containing elements with at least one of the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withSomeModifiers(modifier: KoModifier, vararg modifiers: KoModifier): List<T> = filter {
    it.hasModifiers(modifier) || modifiers.any { modifier -> it.hasModifiers(modifier) }
}

/**
 * List containing elements with no modifier.
 *
 * @return A list containing elements with no modifier.
 */
fun <T : KoModifierProvider> List<T>.withoutModifiers(): List<T> = filterNot { it.hasModifiers() }

/**
 * List containing elements without all specified modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A list containing elements without all the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withoutAllModifiers(modifier: KoModifier, vararg modifiers: KoModifier): List<T> = filterNot {
    it.hasModifiers(modifier, *modifiers)
}

/**
 * List containing elements without some modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A list containing elements without at least one of the specified modifiers.
 */
fun <T : KoModifierProvider> List<T>.withoutSomeModifiers(modifier: KoModifier, vararg modifiers: KoModifier): List<T> = filter {
    !it.hasModifiers(modifier) && if (modifiers.isNotEmpty()) {
        modifiers.any { modifier -> !it.hasModifiers(modifier) }
    } else {
        true
    }
}
