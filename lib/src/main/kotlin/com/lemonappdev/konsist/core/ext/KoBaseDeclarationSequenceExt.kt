package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoBaseDeclaration

fun Sequence<KoBaseDeclaration>.withFilePath(vararg paths: String) = filter {
    paths.any { path -> it.resideInFilePath(path) }
}

fun Sequence<KoBaseDeclaration>.withoutFilePath(vararg paths: String) = filter {
    paths.none { path -> it.resideInFilePath(path) }
}

fun Sequence<KoBaseDeclaration>.withProjectFilePath(vararg paths: String) = filter {
    paths.any { path -> it.resideInProjectFilePath(path) }
}

fun Sequence<KoBaseDeclaration>.withoutProjectFilePath(vararg paths: String) = filter {
    paths.none { path -> it.resideInProjectFilePath(path) }
}
