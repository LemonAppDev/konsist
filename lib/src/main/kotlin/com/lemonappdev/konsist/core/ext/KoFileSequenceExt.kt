@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoFile
import kotlin.reflect.KClass

fun Sequence<KoFile>.withImport() = filter { it.hasImport() }

fun Sequence<KoFile>.withoutImport() = filterNot { it.hasImport() }

fun Sequence<KoFile>.withImports(vararg imports: String) = filter {
    imports.all { import -> it.hasImport(import) }
}

fun Sequence<KoFile>.withSomeImports(vararg imports: String) = filter {
    imports.any { import -> it.hasImport(import) }
}

fun Sequence<KoFile>.withoutImports(vararg imports: String) = filter {
    imports.none { import -> it.hasImport(import) }
}

fun Sequence<KoFile>.withPackage(vararg packages: String) = filter {
    packages.any { packagee -> it.hasPackage(packagee) }
}

fun Sequence<KoFile>.withoutPackage(vararg packages: String) = filter {
    packages.none { packagee -> it.hasPackage(packagee) }
}

fun Sequence<KoFile>.withPath(vararg paths: String) = filter {
    paths.any { path -> it.resideInPath(path) }
}

fun Sequence<KoFile>.withoutPath(vararg paths: String) = filter {
    paths.none { path -> it.resideInPath(path) }
}

fun Sequence<KoFile>.withAnnotation() = filter { it.annotations.isNotEmpty() }

fun Sequence<KoFile>.withoutAnnotation() = filterNot { it.annotations.isNotEmpty() }

fun Sequence<KoFile>.withAnnotations(vararg annotations: String) = filter {
    annotations.all { annotation -> it.hasAnnotation(annotation) }
}

fun Sequence<KoFile>.withSomeAnnotations(vararg annotations: String) = filter {
    annotations.any { annotation -> it.hasAnnotation(annotation) }
}

fun Sequence<KoFile>.withoutAnnotations(vararg annotations: String) = filter {
    annotations.none { annotation -> it.hasAnnotation(annotation) }
}

inline fun <reified T> Sequence<KoFile>.withAnnotationOf() = filter { it.hasAnnotation<T>() }

inline fun <reified T> Sequence<KoFile>.withoutAnnotationOf() = filterNot { it.hasAnnotation<T>() }

fun Sequence<KoFile>.withAnnotationsOf(vararg annotations: KClass<*>) = filter {
    annotations.all { annotation ->
        annotation
            .simpleName
            ?.let { name -> it.hasAnnotation(name) } ?: false
    }
}

fun Sequence<KoFile>.withSomeAnnotationsOf(vararg annotations: KClass<*>) = filter {
    annotations.any { annotation ->
        annotation
            .simpleName
            ?.let { name -> it.hasAnnotation(name) } ?: false
    }
}

fun Sequence<KoFile>.withoutAnnotationsOf(vararg annotations: KClass<*>) = filter {
    annotations.none { annotation ->
        annotation
            .simpleName
            ?.let { name -> it.hasAnnotation(name) } ?: false
    }
}

fun Sequence<KoFile>.withTypeAlias() = filter { it.hasTypeAlias() }

fun Sequence<KoFile>.withoutTypeAlias() = filterNot { it.hasTypeAlias() }

fun Sequence<KoFile>.withTypeAliases(vararg typeAliasNames: String) = filter {
    typeAliasNames.all { typeAlias -> it.hasTypeAlias(typeAlias) }
}

fun Sequence<KoFile>.withSomeTypeAliases(vararg typeAliasNames: String) = filter {
    typeAliasNames.any { typeAlias -> it.hasTypeAlias(typeAlias) }
}

fun Sequence<KoFile>.withoutTypeAliases(vararg typeAliasNames: String) = filter {
    typeAliasNames.none { typeAlias -> it.hasTypeAlias(typeAlias) }
}
