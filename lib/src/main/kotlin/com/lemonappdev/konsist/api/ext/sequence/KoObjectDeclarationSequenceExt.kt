package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration

/**
 * Sequence containing all objects with `data` modifier.
 *
 * @return A sequence containing objects with the `data` modifier.
 */
fun Sequence<KoObjectDeclaration>.withDataModifier(): Sequence<KoObjectDeclaration> = filter { it.hasDataModifier() }

/**
 * Sequence containing all objects without `data` modifier.
 *
 * @return A sequence containing objects without the `data` modifier.
 */
fun Sequence<KoObjectDeclaration>.withoutDataModifier(): Sequence<KoObjectDeclaration> = filterNot { it.hasDataModifier() }

/**
 * Sequence containing all objects with `companion` modifier.
 *
 * @return A sequence containing objects with the `companion` modifier.
 */
fun Sequence<KoObjectDeclaration>.withCompanionModifier(): Sequence<KoObjectDeclaration> = filter { it.hasCompanionModifier() }

/**
 * Sequence containing all objects without `companion` modifier.
 *
 * @return A sequence containing objects without the `companion` modifier.
 */
fun Sequence<KoObjectDeclaration>.withoutCompanionModifier(): Sequence<KoObjectDeclaration> = filterNot { it.hasCompanionModifier() }
