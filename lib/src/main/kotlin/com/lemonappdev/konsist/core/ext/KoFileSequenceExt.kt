@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoFile
import kotlin.reflect.KClass

fun Sequence<KoFile>.withImport() = filter { it.hasImport() }

fun Sequence<KoFile>.withoutImport() = filterNot { it.hasImport() }

fun Sequence<KoFile>.withImports(vararg imports: String) = filter { koFile ->
    imports.all { koFile.hasImport(it) }
}

fun Sequence<KoFile>.withSomeImports(vararg imports: String) = filter { koFile ->
    imports.any { koFile.hasImport(it) }
}

fun Sequence<KoFile>.withoutImports(vararg imports: String) = filter { koFile ->
    imports.none { koFile.hasImport(it) }
}

fun Sequence<KoFile>.withPackage(packageName: String) = filter { it.hasPackage(packageName) ?: false }

fun Sequence<KoFile>.withoutPackage(packageName: String) = filterNot { it.hasPackage(packageName) ?: false }

fun Sequence<KoFile>.withPath(path: String) = filter { it.resideInPath(path) }

fun Sequence<KoFile>.withoutPath(path: String) = filterNot { it.resideInPath(path) }

fun Sequence<KoFile>.withAnnotation() = filter { it.annotations.isNotEmpty() }

fun Sequence<KoFile>.withoutAnnotation() = filterNot { it.annotations.isNotEmpty() }

fun Sequence<KoFile>.withAnnotations(vararg annotations: String) = filter { koFile ->
    annotations.all { koFile.hasAnnotation(it) }
}

fun Sequence<KoFile>.withSomeAnnotations(vararg annotations: String) = filter { koFile ->
    annotations.any { koFile.hasAnnotation(it) }
}

fun Sequence<KoFile>.withoutAnnotations(vararg annotations: String) = filter { koFile ->
    annotations.none { koFile.hasAnnotation(it) }
}

fun Sequence<KoFile>.withAnnotations(vararg annotations: KClass<*>) = filter { koFile ->
    annotations.all { annotation ->
        annotation
            .simpleName
            ?.let { koFile.hasAnnotation(it) } ?: false
    }
}

fun Sequence<KoFile>.withSomeAnnotations(vararg annotations: KClass<*>) = filter { koFile ->
    annotations.any { annotation ->
        annotation
            .simpleName
            ?.let { koFile.hasAnnotation(it) } ?: false
    }
}

fun Sequence<KoFile>.withoutAnnotations(vararg annotations: KClass<*>) = filter { koFile ->
    annotations.none { annotation ->
        annotation
            .simpleName
            ?.let { koFile.hasAnnotation(it) } ?: false
    }
}

fun Sequence<KoFile>.withTypeAlias() = filter { it.hasTypeAlias() }

fun Sequence<KoFile>.withoutTypeAlias() = filterNot { it.hasTypeAlias() }

fun Sequence<KoFile>.withTypeAliases(vararg typeAliasNames: String) = filter { koFile ->
    typeAliasNames.all { koFile.hasTypeAlias(it) }
}

fun Sequence<KoFile>.withSomeTypeAliases(vararg typeAliasNames: String) = filter { koFile ->
    typeAliasNames.any { koFile.hasTypeAlias(it) }
}

fun Sequence<KoFile>.withoutTypeAliases(vararg typeAliasNames: String) = filter { koFile ->
    typeAliasNames.none { koFile.hasTypeAlias(it) }
}
