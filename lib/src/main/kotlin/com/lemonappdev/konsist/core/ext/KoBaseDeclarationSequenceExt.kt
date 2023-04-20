package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoBaseDeclaration

fun Sequence<KoBaseDeclaration>.withFilePaths(vararg path: String) = filter { koBaseDeclaration ->
    path.all { koBaseDeclaration.hasFilePath(it) }
}

fun Sequence<KoBaseDeclaration>.withSomeFilePaths(vararg path: String) = filter { koBaseDeclaration ->
    path.any { koBaseDeclaration.hasFilePath(it) }
}

fun Sequence<KoBaseDeclaration>.withoutFilePaths(vararg path: String) = filter { koBaseDeclaration ->
    path.none { koBaseDeclaration.hasFilePath(it) }
}
