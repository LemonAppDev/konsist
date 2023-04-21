package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoTypeAlias

fun Sequence<KoTypeAlias>.withSourceType(vararg types: String) = filter {
    types.any { type -> it.type.sourceType == type }
}

fun Sequence<KoTypeAlias>.withoutSourceType(vararg types: String) = filter {
    types.none { type -> it.type.sourceType == type }
}

fun Sequence<KoTypeAlias>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoTypeAlias>.withoutActualModifier() = filterNot { it.hasActualModifier() }
