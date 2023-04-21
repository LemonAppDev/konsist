package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoImport

fun Sequence<KoImport>.withAlias(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.alias != it.name
        else -> names.any { name -> it.alias == name }
    }
}

fun Sequence<KoImport>.withoutAlias(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.alias == it.name
        else -> names.none { name -> it.alias == name }
    }
}
