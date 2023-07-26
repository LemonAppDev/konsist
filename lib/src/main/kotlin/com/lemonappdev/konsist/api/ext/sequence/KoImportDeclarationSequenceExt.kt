package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration

/**
 * Sequence containing all imports with a wildcard.
 *
 * @return A sequence containing imports with a wildcard.
 */
fun Sequence<KoImportDeclaration>.withWildcard(): Sequence<KoImportDeclaration> = filter { it.isWildcard }

/**
 * Sequence containing all imports without a wildcard.
 *
 * @return A sequence containing imports without a wildcard.
 */
fun Sequence<KoImportDeclaration>.withoutWildcard(): Sequence<KoImportDeclaration> = filterNot { it.isWildcard }
