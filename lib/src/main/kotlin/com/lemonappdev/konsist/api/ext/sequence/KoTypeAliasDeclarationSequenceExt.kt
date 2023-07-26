package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration

/**
 * Sequence containing type aliases with source type.
 *
 * @param type The source type to include.
 * @param types The source types to include.
 * @return A sequence containing type aliases with the specified source types.
 */
fun Sequence<KoTypeAliasDeclaration>.withSourceType(type: String, vararg types: String): Sequence<KoTypeAliasDeclaration> = filter {
    it.type.sourceType == type || types.any { type -> it.type.sourceType == type }
}

/**
 * Sequence containing type aliases without source type.
 *
 * @param type The source type to exclude.
 * @param types The source types to exclude.
 * @return A sequence containing type aliases without specified source types.
 */
fun Sequence<KoTypeAliasDeclaration>.withoutSourceType(type: String, vararg types: String): Sequence<KoTypeAliasDeclaration> = filter {
    it.type.sourceType != type && types.none { type -> it.type.sourceType == type }
}
