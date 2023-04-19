package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoFile

fun Sequence<KoFile>.withImports(vararg import: String) = filter { koFile ->
    import.all { koFile.hasImport(it) }
}

fun Sequence<KoFile>.withSomeImports(vararg import: String) = filter { koFile ->
    import.any { koFile.hasImport(it) }
}

fun Sequence<KoFile>.withoutImports(vararg import: String) = filter { koFile ->
    import.none { koFile.hasImport(it) }
}

fun Sequence<KoFile>.withPackage(packageName: String) = filter { it.hasPackage(packageName) ?: false }

fun Sequence<KoFile>.withoutPackage(packageName: String) = filterNot { it.hasPackage(packageName) ?: false}