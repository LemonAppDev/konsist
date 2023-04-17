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

fun Sequence<KoClass>.withAbstractModifier() = filter { it.hasAbstractModifier() }

fun Sequence<KoClass>.withoutAbstractModifier() = this - withAbstractModifier().toSet()

fun Sequence<KoClass>.withOpenModifier() = filter { it.hasOpenModifier() }

fun Sequence<KoClass>.withoutOpenModifier() = this - withOpenModifier().toSet()

fun Sequence<KoClass>.withFinalModifier() = filter { it.hasFinalModifier() }

fun Sequence<KoClass>.withoutFinalModifier() = this - withFinalModifier().toSet()

fun Sequence<KoClass>.withExplicitPrimaryConstructor() = filter { it.hasExplicitPrimaryConstructor() }

fun Sequence<KoClass>.withoutExplicitPrimaryConstructor() = this - withExplicitPrimaryConstructor().toSet()

fun Sequence<KoClass>.withSecondaryConstructors() = filter { it.hasSecondaryConstructors() }

fun Sequence<KoClass>.withoutSecondaryConstructors() = this - withSecondaryConstructors().toSet()
