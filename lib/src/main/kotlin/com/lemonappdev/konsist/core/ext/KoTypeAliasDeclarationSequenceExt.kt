package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclaration

fun Sequence<KoTypeAliasDeclaration>.withSourceType(vararg types: String) = filter {
    types.any { type -> it.type.sourceType == type }
}

fun Sequence<KoTypeAliasDeclaration>.withoutSourceType(vararg types: String) = filter {
    types.none { type -> it.type.sourceType == type }
}

fun Sequence<KoTypeAliasDeclaration>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoTypeAliasDeclaration>.withoutActualModifier() = filterNot { it.hasActualModifier() }
