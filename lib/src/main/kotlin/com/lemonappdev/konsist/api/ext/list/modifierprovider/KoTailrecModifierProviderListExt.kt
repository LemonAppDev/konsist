package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoTailrecModifierProvider

/**
 * List containing declarations with `tailrec` modifier.
 *
 * @return A list containing declarations with the `tailrec` modifier.
 */
fun <T : KoTailrecModifierProvider> List<T>.withTailrecModifier(): List<T> = filter { it.hasTailrecModifier }

/**
 * List containing declarations without `tailrec` modifier.
 *
 * @return A list containing declarations without the `tailrec` modifier.
 */
fun <T : KoTailrecModifierProvider> List<T>.withoutTailrecModifier(): List<T> = filterNot { it.hasTailrecModifier }
