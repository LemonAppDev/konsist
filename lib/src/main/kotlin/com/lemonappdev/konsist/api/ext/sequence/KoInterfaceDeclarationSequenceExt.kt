package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration

/**
 * Sequence containing all interfaces with `actual` modifier.
 *
 * @return A sequence containing interfaces with the `actual` modifier.
 */
fun Sequence<KoInterfaceDeclaration>.withActualModifier(): Sequence<KoInterfaceDeclaration> = filter { it.hasActualModifier() }

/**
 * Sequence containing all interfaces without `actual` modifier.
 *
 * @return A sequence containing interfaces without the `actual` modifier.
 */
fun Sequence<KoInterfaceDeclaration>.withoutActualModifier(): Sequence<KoInterfaceDeclaration> = filterNot { it.hasActualModifier() }

/**
 * Sequence containing all interfaces with `expect` modifier.
 *
 * @return A sequence containing interfaces with the `expect` modifier.
 */
fun Sequence<KoInterfaceDeclaration>.withExpectModifier(): Sequence<KoInterfaceDeclaration> = filter { it.hasExpectModifier() }

/**
 * Sequence containing all interfaces without `expect` modifier.
 *
 * @return A sequence containing interfaces without the `expect` modifier.
 */
fun Sequence<KoInterfaceDeclaration>.withoutExpectModifier(): Sequence<KoInterfaceDeclaration> = filterNot { it.hasExpectModifier() }

/**
 * Sequence containing all interfaces with `fun` modifier.
 *
 * @return A sequence containing interfaces with the `fun` modifier.
 */
fun Sequence<KoInterfaceDeclaration>.withFunModifier(): Sequence<KoInterfaceDeclaration> = filter { it.hasFunModifier() }

/**
 * Sequence containing all interfaces without `fun` modifier.
 *
 * @return A sequence containing interfaces without the `fun` modifier.
 */
fun Sequence<KoInterfaceDeclaration>.withoutFunModifier(): Sequence<KoInterfaceDeclaration> = filterNot { it.hasFunModifier() }
