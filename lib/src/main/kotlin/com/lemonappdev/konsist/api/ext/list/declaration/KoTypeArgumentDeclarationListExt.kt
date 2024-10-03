package com.lemonappdev.konsist.api.ext.list.declaration

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration

fun <T : KoTypeArgumentDeclaration> List<T>.flatten(): List<KoTypeArgumentDeclaration> =
    flatMap { typeArg -> listOf(typeArg) + (typeArg.typeArguments?.flattenRecursively() ?: emptyList()) }

private fun <T : KoTypeArgumentDeclaration> List<T>.flattenRecursively(): List<KoTypeArgumentDeclaration> =
    flatMap { typeArg -> listOf(typeArg) + (typeArg.typeArguments?.flattenRecursively() ?: emptyList()) }
