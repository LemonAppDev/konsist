package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration

fun Sequence<KoObjectDeclaration>.withDataModifier() = filter { it.hasDataModifier() }

fun Sequence<KoObjectDeclaration>.withoutDataModifier() = filterNot { it.hasDataModifier() }
