package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationImpl

fun Sequence<KoTypeAliasDeclarationImpl>.withSourceType(vararg types: String) = filter {
    types.any { type -> it.type.sourceType == type }
}

fun Sequence<KoTypeAliasDeclarationImpl>.withoutSourceType(vararg types: String) = filter {
    types.none { type -> it.type.sourceType == type }
}

fun Sequence<KoTypeAliasDeclarationImpl>.withActualModifier() = filter { it.hasActualModifier() }

fun Sequence<KoTypeAliasDeclarationImpl>.withoutActualModifier() = filterNot { it.hasActualModifier() }
