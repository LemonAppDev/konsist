package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoCompanionObjectDeclarationImpl

fun Sequence<KoCompanionObjectDeclarationImpl>.withName() = filter { it.hasName() }

fun Sequence<KoCompanionObjectDeclarationImpl>.withoutName() = filterNot { it.hasName() }
