package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoAnnotationModifierProvider

/**
 * List containing declarations that have `annotation` modifier.
 *
 * @return A list containing declarations with the `annotation` modifier.
 */
fun <T : KoAnnotationModifierProvider> List<T>.withAnnotationModifier(): List<T> = filter { it.hasAnnotationModifier }

/**
 * List containing declarations that don't have `annotation` modifier.
 *
 * @return A list containing declarations without the `annotation` modifier.
 */
fun <T : KoAnnotationModifierProvider> List<T>.withoutAnnotationModifier(): List<T> = filterNot { it.hasAnnotationModifier }
