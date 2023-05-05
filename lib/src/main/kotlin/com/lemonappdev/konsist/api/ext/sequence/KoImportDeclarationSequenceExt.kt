package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration

/**
 * Sequence containing all declarations that have an alias.
 */
fun Sequence<KoImportDeclaration>.withAlias(vararg names: String): Sequence<KoImportDeclaration> = filter {
    when {
        names.isEmpty() -> it.alias != it.name
        else -> names.any { name -> it.alias == name }
    }
}

/**
 * Sequence containing all declarations that don't have an alias.
 */
fun Sequence<KoImportDeclaration>.withoutAlias(vararg names: String): Sequence<KoImportDeclaration> = filter {
    when {
        names.isEmpty() -> it.alias == it.name
        else -> names.none { name -> it.alias == name }
    }
}
