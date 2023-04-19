package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoInterface

fun Sequence<KoInterface>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoInterface>.withoutActualModifier() = filterNot { it.hasActualModifier() }

fun Sequence<KoInterface>.withExpectModifier() = filter { it.hasExpectModifier() }

fun Sequence<KoInterface>.withoutExpectModifier() = filterNot { it.hasExpectModifier() }
