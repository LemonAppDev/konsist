package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoAbstractModifierProvider

/**
 * List containing elements that have `abstract` modifier.
 *
 * @return A list containing elements with the `abstract` modifier.
 */
fun <T : KoAbstractModifierProvider> List<T>.withAbstractModifier(): List<T> = filter { it.hasAbstractModifier }

/**
 * List containing elements that don't have `abstract` modifier.
 *
 * @return A list containing elements without the `abstract` modifier.
 */
fun <T : KoAbstractModifierProvider> List<T>.withoutAbstractModifier(): List<T> = filterNot { it.hasAbstractModifier }
