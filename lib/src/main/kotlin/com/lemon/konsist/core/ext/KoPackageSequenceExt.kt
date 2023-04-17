package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoPackage

fun Sequence<KoPackage>.withQualifiedName(name: String) = filter { it.qualifiedName == name }

fun Sequence<KoPackage>.withoutQualifiedName(name: String) = this - withQualifiedName(name).toSet()
