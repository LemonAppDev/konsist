package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoClass

fun Sequence<KoClass>.withEnumModifier() = filter { it.hasEnumModifier() }

fun Sequence<KoClass>.withoutEnumModifier() = this - withEnumModifier().toSet()

fun Sequence<KoClass>.withSealedModifier() = filter { it.hasSealedModifier() }

fun Sequence<KoClass>.withoutSealedModifier() = this - withSealedModifier().toSet()

fun Sequence<KoClass>.withInnerModifier() = filter { it.hasInnerModifier() }

fun Sequence<KoClass>.withoutInnerModifier() = this - withInnerModifier().toSet()

fun Sequence<KoClass>.withValueModifier() = filter { it.hasValueModifier() }

fun Sequence<KoClass>.withoutValueModifier() = this - withValueModifier().toSet()

fun Sequence<KoClass>.withAnnotationModifier() = filter { it.hasAnnotationModifier() }

fun Sequence<KoClass>.withoutAnnotationModifier() = this - withAnnotationModifier().toSet()

fun Sequence<KoClass>.withDataModifier() = filter { it.hasDataModifier() }

fun Sequence<KoClass>.withoutDataModifier() = this - withDataModifier().toSet()