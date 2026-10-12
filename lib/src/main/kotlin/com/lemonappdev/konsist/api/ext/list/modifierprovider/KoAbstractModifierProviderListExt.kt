package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoAbstractModifierProvider

/**
 * List containing declarations that have `abstract` modifier.
 *
 * @return A list containing declarations with the `abstract` modifier.
 * @see KoAbstractModifierProvider.hasAbstractModifier
 */
fun <T : KoAbstractModifierProvider> List<T>.withAbstractModifier(): List<T> = filter { it.hasAbstractModifier }

/**
 * List containing declarations that don't have `abstract` modifier.
 *
 * @return A list containing declarations without the `abstract` modifier.
 * @see KoAbstractModifierProvider.hasAbstractModifier
 */
fun <T : KoAbstractModifierProvider> List<T>.withoutAbstractModifier(): List<T> = filterNot { it.hasAbstractModifier }
