package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoCompanionObject

fun Sequence<KoCompanionObject>.withName() = filter { it.hasName() }

fun Sequence<KoCompanionObject>.withoutName() = filterNot { it.hasName() }
