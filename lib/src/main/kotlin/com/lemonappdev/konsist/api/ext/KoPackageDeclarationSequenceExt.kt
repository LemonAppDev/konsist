package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoPackageDeclarationImpl

fun Sequence<KoPackageDeclarationImpl>.withQualifiedName(vararg names: String) = filter {
    names.any { name -> it.qualifiedName == name }
}

fun Sequence<KoPackageDeclarationImpl>.withoutQualifiedName(vararg names: String) = filter {
    names.none { name -> it.qualifiedName == name }
}
