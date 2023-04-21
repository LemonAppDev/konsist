package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoPackage

fun Sequence<KoPackage>.withQualifiedName(vararg names: String) = filter {
    names.any { name -> it.qualifiedName == name }
}

fun Sequence<KoPackage>.withoutQualifiedName(vararg names: String) = filter {
    names.none { name -> it.qualifiedName == name }
}
