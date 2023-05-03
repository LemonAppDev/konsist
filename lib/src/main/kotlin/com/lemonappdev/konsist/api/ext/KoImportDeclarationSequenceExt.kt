package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoImportDeclarationImpl

fun Sequence<KoImportDeclarationImpl>.withAlias(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.alias != it.name
        else -> names.any { name -> it.alias == name }
    }
}

fun Sequence<KoImportDeclarationImpl>.withoutAlias(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.alias == it.name
        else -> names.none { name -> it.alias == name }
    }
}
