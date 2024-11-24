package com.lemonappdev.konsist.api.ext.list.declaration

import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider

/**
 * Flattens the list of `KoTypeArgumentDeclaration` objects into a list of `KoBaseTypeDeclaration` objects.
 *
 * This method recursively extracts all the type arguments and their source type declarations, producing a flat list
 * that includes both the outer types and their nested generic types.
 *
 * For example:
 * - For a type argument like `String`, it returns `listOf(String)`.
 * - For a type argument like `List<String>`, it returns `listOf(List, String)`.
 * - For a type argument like `Map<List<String>, Int>`, it returns `listOf(Map, List, String, Int)`.
 *
 * @return A flattened list of `KoBaseTypeDeclaration` objects, representing the source types of all type arguments and their nested types.
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
