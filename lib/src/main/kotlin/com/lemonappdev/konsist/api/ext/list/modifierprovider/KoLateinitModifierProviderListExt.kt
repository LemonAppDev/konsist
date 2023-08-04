package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoLateinitModifierProvider

/**
 * List containing elements with `lateinit` modifier.
 *
 * @return A list containing elements with the `lateinit` modifier.
 */
fun <T : KoLateinitModifierProvider> List<T>.withLateinitModifier(): List<T> = filter { it.hasLateinitModifier }

/**
 * List containing elements without `lateinit` modifier.
 *
 * @return A list containing elements without the `lateinit` modifier.
 */
fun <T : KoLateinitModifierProvider> List<T>.withoutLateinitModifier(): List<T> = filterNot { it.hasLateinitModifier }
