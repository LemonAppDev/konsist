package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration

fun Sequence<KoImportDeclaration>.withAlias(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.alias != it.name
        else -> names.any { name -> it.alias == name }
    }
}

fun Sequence<KoImportDeclaration>.withoutAlias(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.alias == it.name
        else -> names.none { name -> it.alias == name }
    }
}
