package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoObjectDeclarationImpl

fun Sequence<KoObjectDeclarationImpl>.withDataModifier() = filter { it.hasDataModifier() }

fun Sequence<KoObjectDeclarationImpl>.withoutDataModifier() = filterNot { it.hasDataModifier() }
