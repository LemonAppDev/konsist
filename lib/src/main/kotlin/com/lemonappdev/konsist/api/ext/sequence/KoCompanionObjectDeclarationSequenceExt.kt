package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoCompanionObjectDeclaration

/**
 * Sequence containing declarations that have name.
 */
fun Sequence<KoCompanionObjectDeclaration>.withName(): Sequence<KoCompanionObjectDeclaration> = filter { it.hasName() }

/**
 * Sequence containing declarations that don't have name.
 */
fun Sequence<KoCompanionObjectDeclaration>.withoutName(): Sequence<KoCompanionObjectDeclaration> = filterNot { it.hasName() }
