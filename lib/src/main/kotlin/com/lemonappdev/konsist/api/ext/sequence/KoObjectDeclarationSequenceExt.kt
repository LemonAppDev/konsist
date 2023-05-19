package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration

/**
 * Sequence containing all declarations that have 'data' modifier.
 *
 * @return A sequence containing object declarations with the 'data' modifier.
 */
fun Sequence<KoObjectDeclaration>.withDataModifier(): Sequence<KoObjectDeclaration> = filter { it.hasDataModifier() }

/**
 * Sequence containing all declarations that don't have 'data' modifier.
 *
 * @return A sequence containing object declarations without the 'data' modifier.
 */
fun Sequence<KoObjectDeclaration>.withoutDataModifier(): Sequence<KoObjectDeclaration> = filterNot { it.hasDataModifier() }

/**
 * Sequence containing all declarations that have 'companion' modifier.
 *
 * @return A sequence containing object declarations with the 'companion' modifier.
 */
fun Sequence<KoObjectDeclaration>.withCompanionModifier(): Sequence<KoObjectDeclaration> = filter { it.hasCompanionModifier() }

/**
 * Sequence containing all declarations that don't have 'companion' modifier.
 *
 * @return A sequence containing object declarations without the 'companion' modifier.
 */
fun Sequence<KoObjectDeclaration>.withoutCompanionModifier(): Sequence<KoObjectDeclaration> = filterNot { it.hasCompanionModifier() }
