package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoTypeProjectionModifierProvider

/**
 * List containing declarations with `out` modifier.
 *
 * @return A list containing declarations with the `out` modifier.
 */
fun <T : KoTypeProjectionModifierProvider> List<T>.withOutModifier(): List<T> = filter { it.hasOutModifier }

/**
 * List containing declarations without `out` modifier.
 *
 * @return A list containing declarations without the `out` modifier.
 */
fun <T : KoTypeProjectionModifierProvider> List<T>.withoutOutModifier(): List<T> = filterNot { it.hasOutModifier }
/**
 * List containing declarations with `in` modifier.
 *
 * @return A list containing declarations with the `in` modifier.
 */
fun <T : KoTypeProjectionModifierProvider> List<T>.withInModifier(): List<T> = filter { it.hasInModifier }

/**
 * List containing declarations without `in` modifier.
 *
 * @return A list containing declarations without the `in` modifier.
 */
fun <T : KoTypeProjectionModifierProvider> List<T>.withoutInModifier(): List<T> = filterNot { it.hasInModifier }
