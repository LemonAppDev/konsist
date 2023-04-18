package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoCompanionObject

fun Sequence<KoCompanionObject>.withExplicitName() = filter { it.hasExplicitName() }

fun Sequence<KoCompanionObject>.withoutExplicitName() = filterNot { it.hasExplicitName() }
