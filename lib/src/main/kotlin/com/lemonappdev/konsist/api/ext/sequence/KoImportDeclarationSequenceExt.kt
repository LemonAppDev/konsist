package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration

/**
 * Sequence containing all imports with an alias.
 *
 * @param names The names of aliases to include.
 * @return A sequence containing imports with the specified aliases (or any alias if [names] is empty).
 */
fun Sequence<KoImportDeclaration>.withAlias(vararg names: String): Sequence<KoImportDeclaration> = filter {
    when {
        names.isEmpty() -> it.alias != it.name
        else -> names.any { name -> it.alias == name }
    }
}

/**
 * Sequence containing all imports without an alias.
 *
 * @param names The names of aliases to exclude.
 * @return A sequence containing imports without specified aliases (or none alias if [names] is empty).
 */
fun Sequence<KoImportDeclaration>.withoutAlias(vararg names: String): Sequence<KoImportDeclaration> = filter {
    when {
        names.isEmpty() -> it.alias == it.name
        else -> names.none { name -> it.alias == name }
    }
}

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
