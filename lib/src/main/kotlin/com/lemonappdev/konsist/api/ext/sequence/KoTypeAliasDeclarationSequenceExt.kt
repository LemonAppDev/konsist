package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration

/**
 * Sequence containing type aliases that have source type.
 *
 * @param types The source types to include.
 * @return A sequence containing type aliases that have the specified source types.
 */
fun Sequence<KoTypeAliasDeclaration>.withSourceType(vararg types: String): Sequence<KoTypeAliasDeclaration> = filter {
    types.any { type -> it.type.sourceType == type }
}

/**
 * Sequence containing type aliases that don't have source type.
 *
 * @param types The source types to exclude.
 * @return A sequence containing type aliases that don't have the specified source types.
 */
fun Sequence<KoTypeAliasDeclaration>.withoutSourceType(vararg types: String): Sequence<KoTypeAliasDeclaration> = filter {
    types.none { type -> it.type.sourceType == type }
}

/**
 * Sequence containing type aliases that have `actual` modifier.
 *
 * @return A sequence containing type aliases that have the `actual` modifier.
 */
fun Sequence<KoTypeAliasDeclaration>.withActualModifier(): Sequence<KoTypeAliasDeclaration> = filter { it.hasActualModifier() }

/**
 * Sequence containing type aliases that don't have `actual` modifier.
 *
 * @return A sequence containing type aliases that don't have the `actual` modifier.
 */
fun Sequence<KoTypeAliasDeclaration>.withoutActualModifier(): Sequence<KoTypeAliasDeclaration> = filterNot { it.hasActualModifier() }
