package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration

/**
 * Sequence containing all imports that have an alias.
 *
 * @param names The names of aliases to include.
 * @return A sequence containing imports that have the specified aliases (or any alias if [names] is empty).
 */
fun Sequence<KoImportDeclaration>.withAlias(vararg names: String): Sequence<KoImportDeclaration> = filter {
    when {
        names.isEmpty() -> it.alias != it.name
        else -> names.any { name -> it.alias == name }
    }
}

/**
 * Sequence containing all imports that don't have an alias.
 *
 * @param names The names of aliases to exclude.
 * @return A sequence containing imports that don't have the specified aliases (or none alias if [names] is empty).
 */
fun Sequence<KoImportDeclaration>.withoutAlias(vararg names: String): Sequence<KoImportDeclaration> = filter {
    when {
        names.isEmpty() -> it.alias == it.name
        else -> names.none { name -> it.alias == name }
    }
}

/**
 * Sequence containing all imports that have a wildcard.
 *
 * @return A sequence containing imports with a wildcard.
 */
fun Sequence<KoImportDeclaration>.withWildcard(): Sequence<KoImportDeclaration> = filter { it.isWildcard }

/**
 * Sequence containing all imports that don't have a wildcard.
 *
 * @return A sequence containing imports without a wildcard.
 */
fun Sequence<KoImportDeclaration>.withoutWildcard(): Sequence<KoImportDeclaration> = filterNot { it.isWildcard }
