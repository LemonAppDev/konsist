package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration

fun Sequence<KoParentDeclaration>.withDelegate(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasDelegate()
        else -> names.any { name -> it.hasDelegate(name) }
    }
}

fun Sequence<KoParentDeclaration>.withoutDelegate(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasDelegate()
        else -> names.none { name -> it.hasDelegate(name) }
    }
}
