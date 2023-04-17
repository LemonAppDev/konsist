package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoObject

fun Sequence<KoObject>.withDataModifier() = filter { it.hasDataModifier() }

fun Sequence<KoObject>.withoutDataModifier() = this - withDataModifier().toSet()
