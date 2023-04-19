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

fun Sequence<KoFile>.withoutPackage(packageName: String) = filterNot { it.hasPackage(packageName) ?: false }

fun Sequence<KoFile>.withPath(path: String) = filter { it.path == path }

fun Sequence<KoFile>.withoutPath(path: String) = filterNot { it.path == path }

fun Sequence<KoFile>.withAnnotation() = filter { it.annotations.isNotEmpty() }

fun Sequence<KoFile>.withoutAnnotation() = filterNot { it.annotations.isNotEmpty() }

fun Sequence<KoFile>.withAnnotations(vararg annotation: String) = filter { koFile ->
    annotation.all { koFile.hasAnnotation(it) }
}

fun Sequence<KoFile>.withSomeAnnotations(vararg annotation: String) = filter { koFile ->
    annotation.any { koFile.hasAnnotation(it) }
}

fun Sequence<KoFile>.withoutAnnotations(vararg annotation: String) = filter { koFile ->
    annotation.none { koFile.hasAnnotation(it) }
}


