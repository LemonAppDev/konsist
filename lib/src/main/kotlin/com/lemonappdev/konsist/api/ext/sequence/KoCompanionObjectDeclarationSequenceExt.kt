package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoCompanionObjectDeclaration

fun Sequence<KoCompanionObjectDeclaration>.withName() = filter { it.hasName() }

fun Sequence<KoCompanionObjectDeclaration>.withoutName() = filterNot { it.hasName() }
