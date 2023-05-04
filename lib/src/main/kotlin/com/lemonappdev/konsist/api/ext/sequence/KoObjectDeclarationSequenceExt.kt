package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration

fun Sequence<KoObjectDeclaration>.withDataModifier() = filter { it.hasDataModifier() }

fun Sequence<KoObjectDeclaration>.withoutDataModifier() = filterNot { it.hasDataModifier() }
