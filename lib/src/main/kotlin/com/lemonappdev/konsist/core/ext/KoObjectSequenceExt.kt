package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoObject

fun Sequence<KoObject>.withDataModifier() = filter { it.hasDataModifier() }

fun Sequence<KoObject>.withoutDataModifier() = filterNot { it.hasDataModifier() }
