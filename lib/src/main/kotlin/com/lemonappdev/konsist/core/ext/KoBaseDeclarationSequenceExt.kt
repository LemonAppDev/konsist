package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoBaseDeclaration

fun Sequence<KoBaseDeclaration>.withFilePath(vararg paths: String) = filter {
    paths.any { path -> it.hasFilePath(path) }
}

fun Sequence<KoBaseDeclaration>.withoutFilePath(vararg paths: String) = filter {
    paths.none { path -> it.hasFilePath(path) }
}
