package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoBaseDeclaration

fun Sequence<KoBaseDeclaration>.withFilePaths(vararg paths: String) = filter { koBaseDeclaration ->
    paths.all { koBaseDeclaration.hasFilePath(it) }
}

fun Sequence<KoBaseDeclaration>.withSomeFilePaths(vararg paths: String) = filter { koBaseDeclaration ->
    paths.any { koBaseDeclaration.hasFilePath(it) }
}

fun Sequence<KoBaseDeclaration>.withoutFilePaths(vararg paths: String) = filter { koBaseDeclaration ->
    paths.none { koBaseDeclaration.hasFilePath(it) }
}
