package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoCompanionObject

fun Sequence<KoCompanionObject>.withExplicitName() = filter { it.hasExplicitName() }

fun Sequence<KoCompanionObject>.withoutExplicitName() = filterNot { it.hasExplicitName() }
