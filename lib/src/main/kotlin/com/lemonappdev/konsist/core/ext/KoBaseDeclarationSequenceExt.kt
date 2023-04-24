package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoBaseDeclaration

fun <T : KoBaseDeclaration> Sequence<T>.withFilePath(vararg paths: String) = filter {
    paths.any { path -> it.resideInFilePath(path) }
}

fun <T : KoBaseDeclaration> Sequence<T>.withoutFilePath(vararg paths: String) = filter {
    paths.none { path -> it.resideInFilePath(path) }
}

fun <T : KoBaseDeclaration> Sequence<T>.withProjectFilePath(vararg paths: String) = filter {
    paths.any { path -> it.resideInProjectFilePath(path) }
}

fun <T : KoBaseDeclaration> Sequence<T>.withoutProjectFilePath(vararg paths: String) = filter {
    paths.none { path -> it.resideInProjectFilePath(path) }
}
