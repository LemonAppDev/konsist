package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoClass

fun Sequence<KoClass>.withEnumModifier() = filter { it.hasEnumModifier() }

fun Sequence<KoClass>.withoutEnumModifier() = this - withEnumModifier().toSet()

fun Sequence<KoClass>.withSealedModifier() = filter { it.hasSealedModifier() }

fun Sequence<KoClass>.withoutSealedModifier() = this - withSealedModifier().toSet()
