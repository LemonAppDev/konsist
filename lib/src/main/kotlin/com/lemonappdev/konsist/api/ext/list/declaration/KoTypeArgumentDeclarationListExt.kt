package com.lemonappdev.konsist.api.ext.list.declaration

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.core.declaration.KoTypeArgumentDeclarationCore

fun <T : KoTypeArgumentDeclaration> List<T>.flatten(): List<KoBaseTypeDeclaration> =
    flatMap { typeArg ->
        val baseDeclaration = if (typeArg.typeArguments?.isNotEmpty() == true) {
            KoTypeArgumentDeclarationCore(
                typeArg.sourceDeclaration.name,
                typeArg.typeArguments,
                typeArg.sourceDeclaration,
            )
        } else {
            typeArg
        }

        listOf(baseDeclaration) + (typeArg.typeArguments?.flattenRecursively() ?: emptyList())
    }
        .map { it.sourceDeclaration }

private fun <T : KoTypeArgumentDeclaration> List<T>.flattenRecursively(): List<KoTypeArgumentDeclaration> =
    flatMap { typeArg -> listOf(typeArg) + (typeArg.typeArguments?.flattenRecursively() ?: emptyList()) }
