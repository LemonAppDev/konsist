package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPsiDeclaration

/**
 * Sequence containing declarations that have file path.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withFilePath(vararg paths: String): Sequence<T> = filter {
    paths.any { path -> it.resideInFilePath(path) }
}

/**
 * Sequence containing declarations that don't have file path.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withoutFilePath(vararg paths: String): Sequence<T> = filter {
    paths.none { path -> it.resideInFilePath(path) }
}

/**
 * Sequence containing declarations that have project file path.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withProjectFilePath(vararg paths: String): Sequence<T> = filter {
    paths.any { path -> it.resideInProjectFilePath(path) }
}

/**
 * Sequence containing declarations that don't have project file path.
 */
fun <T : KoPsiDeclaration> Sequence<T>.withoutProjectFilePath(vararg paths: String): Sequence<T> = filter {
    paths.none { path -> it.resideInProjectFilePath(path) }
}
