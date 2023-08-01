package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoAnnotationModifierProvider

/**
 * List containing all declarations that have `annotation` modifier.
 *
 * @return A list containing declarations with the `annotation` modifier.
 */
fun <T : KoAnnotationModifierProvider> List<T>.withAnnotationModifier(): List<T> = filter { it.hasAnnotationModifier }

/**
 * List containing all declarations that don't have `annotation` modifier.
 *
 * @return A list containing declarations without the `annotation` modifier.
 */
fun <T : KoAnnotationModifierProvider> List<T>.withoutAnnotationModifier(): List<T> = filterNot { it.hasAnnotationModifier }
