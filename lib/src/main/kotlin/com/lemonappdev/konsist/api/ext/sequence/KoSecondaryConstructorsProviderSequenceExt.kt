package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider

/**
 * Sequence containing all declarations that have secondary constructors.
 *
 * @return A sequence containing declarations with secondary constructor(s).
 */
fun <T : KoSecondaryConstructorsProvider> Sequence<T>.withSecondaryConstructors(): Sequence<T> = filter { it.hasSecondaryConstructors() }

/**
 * Sequence containing all declarations that don't have secondary constructors.
 *
 * @return A sequence containing declarations without secondary constructor(s).
 */
fun <T : KoSecondaryConstructorsProvider> Sequence<T>.withoutSecondaryConstructors(): Sequence<T> =
    filterNot { it.hasSecondaryConstructors() }
