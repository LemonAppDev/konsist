package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoPsiDeclarationImpl

fun <T : KoPsiDeclarationImpl> Sequence<T>.withFilePath(vararg paths: String) = filter {
    paths.any { path -> it.resideInFilePath(path) }
}

fun <T : KoPsiDeclarationImpl> Sequence<T>.withoutFilePath(vararg paths: String) = filter {
    paths.none { path -> it.resideInFilePath(path) }
}

fun <T : KoPsiDeclarationImpl> Sequence<T>.withProjectFilePath(vararg paths: String) = filter {
    paths.any { path -> it.resideInProjectFilePath(path) }
}

fun <T : KoPsiDeclarationImpl> Sequence<T>.withoutProjectFilePath(vararg paths: String) = filter {
    paths.none { path -> it.resideInProjectFilePath(path) }
}
