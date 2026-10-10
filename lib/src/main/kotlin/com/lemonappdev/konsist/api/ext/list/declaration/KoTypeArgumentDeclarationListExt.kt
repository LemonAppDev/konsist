package com.lemonappdev.konsist.api.ext.list.declaration

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider

/**
 * Flattens the list of `KoTypeArgumentDeclaration` objects into a list of `KoDeclarationCastProvider` objects.
 *
 * This method recursively extracts all the type arguments and their source type declarations, producing a flat list
 * that includes both the outer types and their nested generic types.
 *
 * For example:
 * - For type arguments of a type like `String`, it returns `listOf()`.
 * - For type arguments of a type like `List<String>`, it returns `listOf(String)`.
 * - For type arguments of a type like `Map<List<String>, Int>`, it returns `listOf(List, String, Int)`.
 *
 * @return A flattened list of `KoDeclarationCastProvider` objects, representing the source types of all type arguments
 * and their nested types.
 */
fun <T : KoTypeArgumentDeclaration> List<T>.flatten(): List<KoDeclarationCastProvider> =
    mapNotNull { typeArg ->
        // Directly use the existing type argument declaration and flatten recursively
        val flattenedDeclarations = listOf(typeArg) + (typeArg.typeArguments?.flattenRecursively() ?: emptyList())
        flattenedDeclarations
    }.flatten()
        .mapNotNull { it.sourceDeclaration }

private fun <T : KoTypeArgumentDeclaration> List<T>.flattenRecursively(): List<KoTypeArgumentDeclaration> =
    flatMap { typeArg ->
        // Recursively flatten type arguments, if any
        listOf(typeArg) + (typeArg.typeArguments?.flattenRecursively() ?: emptyList())
    }
