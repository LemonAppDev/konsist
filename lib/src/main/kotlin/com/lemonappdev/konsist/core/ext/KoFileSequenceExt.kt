@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoFile
import kotlin.reflect.KClass

fun Sequence<KoFile>.withImports(vararg imports: String) = filter {
    when {
        imports.isEmpty() -> it.hasImports()
        else -> it.hasImports(*imports)
    }
}

fun Sequence<KoFile>.withSomeImports(vararg imports: String) = filter {
    imports.any { import -> it.hasImports(import) }
}

fun Sequence<KoFile>.withoutImports(vararg imports: String) = filter {
    when {
        imports.isEmpty() -> !it.hasImports()
        else -> !it.hasImports(*imports)
    }
}

fun Sequence<KoFile>.withPackage(vararg packages: String) = filter {
    packages.any { packagee -> it.hasPackage(packagee) }
}

fun Sequence<KoFile>.withoutPackage(vararg packages: String) = filter {
    packages.none { packagee -> it.hasPackage(packagee) }
}

fun Sequence<KoFile>.withAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

fun Sequence<KoFile>.withSomeAnnotations(vararg annotations: String) = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
}

fun Sequence<KoFile>.withoutAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

inline fun <reified T> Sequence<KoFile>.withAnnotationOf() = filter { it.hasAnnotationOf<T>() }

inline fun <reified T> Sequence<KoFile>.withoutAnnotationOf() = filterNot { it.hasAnnotationOf<T>() }

fun Sequence<KoFile>.withAnnotationsOf(vararg annotations: KClass<*>) = filter { it.hasAnnotationsOf(*annotations) }

fun Sequence<KoFile>.withSomeAnnotationsOf(vararg annotations: KClass<*>) = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

fun Sequence<KoFile>.withoutAnnotationsOf(vararg annotations: KClass<*>) = filter { !it.hasAnnotationsOf(*annotations) }

fun Sequence<KoFile>.withTypeAliases(vararg typeAliasNames: String) = filter {
    when {
        typeAliasNames.isEmpty() -> it.hasTypeAliases()
        else -> it.hasTypeAliases(*typeAliasNames)
    }
}

fun Sequence<KoFile>.withSomeTypeAliases(vararg typeAliasNames: String) = filter {
    typeAliasNames.any { typeAlias -> it.hasTypeAliases(typeAlias) }
}

fun Sequence<KoFile>.withoutTypeAliases(vararg typeAliasNames: String) = filter {
    when {
        typeAliasNames.isEmpty() -> !it.hasTypeAliases()
        else -> !it.hasTypeAliases(*typeAliasNames)
    }
}
