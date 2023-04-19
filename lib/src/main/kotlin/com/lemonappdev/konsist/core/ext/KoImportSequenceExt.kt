package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoImport

fun Sequence<KoImport>.withAlias(name: String? = null) = filter {
    if (name == null) {
        it.alias != it.name
    } else {
        it.alias == name
    }
}

fun Sequence<KoImport>.withoutAlias(name: String? = null) = this - withAlias(name).toSet()
