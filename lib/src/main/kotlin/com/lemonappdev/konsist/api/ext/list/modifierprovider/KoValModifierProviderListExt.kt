package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoValModifierProvider

/**
 * List containing declarations with `val` modifier.
 *
 * @return A list containing declarations with the `val` modifier.
 */
@Deprecated("Will be removed in version 0.18.0", ReplaceWith("withVal"))
fun <T : KoValModifierProvider> List<T>.withValModifier(): List<T> = filter { it.hasValModifier }

/**
 * List containing declarations without `val` modifier.
 *
 * @return A list containing declarations without the `val` modifier.
 */
@Deprecated("Will be removed in version 0.18.0", ReplaceWith("withoutVal"))
fun <T : KoValModifierProvider> List<T>.withoutValModifier(): List<T> = filterNot { it.hasValModifier }
