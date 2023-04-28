package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoObjectDeclaration

fun Sequence<KoObjectDeclaration>.withDataModifier() = filter { it.hasDataModifier() }

fun Sequence<KoObjectDeclaration>.withoutDataModifier() = filterNot { it.hasDataModifier() }
