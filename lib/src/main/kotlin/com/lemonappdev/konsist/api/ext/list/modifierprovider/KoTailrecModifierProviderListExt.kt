package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoTailrecModifierProvider

/**
 * List containing elements with `tailrec` modifier.
 *
 * @return A list containing elements with the `tailrec` modifier.
 */
fun <T : KoTailrecModifierProvider> List<T>.withTailrecModifier(): List<T> = filter { it.hasTailrecModifier }

/**
 * List containing elements without `tailrec` modifier.
 *
 * @return A list containing elements without the `tailrec` modifier.
 */
fun <T : KoTailrecModifierProvider> List<T>.withoutTailrecModifier(): List<T> = filterNot { it.hasTailrecModifier }
