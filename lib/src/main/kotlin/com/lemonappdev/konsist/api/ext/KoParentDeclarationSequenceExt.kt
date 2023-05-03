package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoParentDeclarationImpl

fun Sequence<KoParentDeclarationImpl>.withDelegate(vararg names: String) = filter {
    when {
        names.isEmpty() -> it.hasDelegate()
        else -> names.any { name -> it.hasDelegate(name) }
    }
}

fun Sequence<KoParentDeclarationImpl>.withoutDelegate(vararg names: String) = filter {
    when {
        names.isEmpty() -> !it.hasDelegate()
        else -> names.none { name -> it.hasDelegate(name) }
    }
}
