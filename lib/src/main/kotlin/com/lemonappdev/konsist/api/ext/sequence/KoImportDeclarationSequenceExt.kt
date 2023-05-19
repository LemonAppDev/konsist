package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration

/**
 * Sequence containing all declarations that have an alias.
 *
 * @param names The names of aliases to include. If empty, all import declarations with aliases are included.
 * @return A sequence containing import declarations with aliases.
 */
fun Sequence<KoImportDeclaration>.withAlias(vararg names: String): Sequence<KoImportDeclaration> = filter {
    when {
        names.isEmpty() -> it.alias != it.name
        else -> names.any { name -> it.alias == name }
    }
}

/**
 * Sequence containing all declarations that don't have an alias.
 *
 * @param names The names of aliases to exclude. If empty, all import declarations without aliases are included.
 * @return A sequence containing import declarations without aliases.
 */
fun Sequence<KoImportDeclaration>.withoutAlias(vararg names: String): Sequence<KoImportDeclaration> = filter {
    when {
        names.isEmpty() -> it.alias == it.name
        else -> names.none { name -> it.alias == name }
    }
}

/**
 * Sequence containing all import declarations that have a wildcard.
 *
 * @return A sequence containing import declarations with a wildcard.
 */
fun Sequence<KoImportDeclaration>.withWildcard(): Sequence<KoImportDeclaration> = filter { it.isWildcard }

/**
 * Sequence containing all import declarations that don't have a wildcard.
 *
 * @return A sequence containing import declarations without a wildcard.
 */
fun Sequence<KoImportDeclaration>.withoutWildcard(): Sequence<KoImportDeclaration> = filterNot { it.isWildcard }
