@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoFile
import kotlin.reflect.KClass

fun Sequence<KoFile>.withImport() = filter { it.hasImport() }

fun Sequence<KoFile>.withoutImport() = filterNot { it.hasImport() }

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

fun Sequence<KoFile>.withAnnotations(vararg annotation: KClass<*>) = filter { koFile ->
    annotation.all { annotation ->
        annotation
            .simpleName
            ?.let { koFile.hasAnnotation(it) } ?: false
    }
}

fun Sequence<KoFile>.withSomeAnnotations(vararg annotation: KClass<*>) = filter { koFile ->
    annotation.any { annotation ->
        annotation
            .simpleName
            ?.let { koFile.hasAnnotation(it) } ?: false
    }
}

fun Sequence<KoFile>.withoutAnnotations(vararg annotation: KClass<*>) = filter { koFile ->
    annotation.none { annotation ->
        annotation
            .simpleName
            ?.let { koFile.hasAnnotation(it) } ?: false
    }
}

fun Sequence<KoFile>.withTypeAlias() = filter { it.hasTypeAlias() }

fun Sequence<KoFile>.withoutTypeAlias() = filterNot { it.hasTypeAlias() }

fun Sequence<KoFile>.withTypeAliases(vararg typealiasName: String) = filter { koFile ->
    typealiasName.all { koFile.hasTypeAlias(it) }
}

fun Sequence<KoFile>.withSomeTypeAliases(vararg typealiasName: String) = filter { koFile ->
    typealiasName.any { koFile.hasTypeAlias(it) }
}

fun Sequence<KoFile>.withoutTypeAliases(vararg typealiasName: String) = filter { koFile ->
    typealiasName.none { koFile.hasTypeAlias(it) }
}
