package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoVarAndValProvider

/**
 * Sequence containing declarations with `var` modifier.
 *
 * @return A sequence containing declarations with the `var` modifier.
 */
fun <T: KoVarAndValProvider> Sequence<T>.withVarModifier(): Sequence<T> = filter { it.isVar }

/**
 * Sequence containing declarations without `var` modifier.
 *
 * @return A sequence containing declarations without the `var` modifier.
 */
fun <T: KoVarAndValProvider> Sequence<T>.withoutVarModifier(): Sequence<T> = filterNot { it.isVar }

/**
 * Sequence containing declarations with `val` modifier.
 *
 * @return A sequence containing declarations with the `val` modifier.
 */
fun <T: KoVarAndValProvider> Sequence<T>.withValModifier(): Sequence<T> = filter { it.isVal }

/**
 * Sequence containing declarations without `val` modifier.
 *
 * @return A sequence containing declarations without the `val` modifier.
 */
fun <T: KoVarAndValProvider> Sequence<T>.withoutValModifier(): Sequence<T> = filterNot { it.isVal }
