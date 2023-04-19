package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoImport

fun Sequence<KoImport>.withAlias(name: String? = null) = filter {
    when (name) {
        null -> it.alias != it.name
        else -> it.alias == name
    }
}

fun Sequence<KoImport>.withoutAlias(name: String? = null) = this - withAlias(name).toSet()
