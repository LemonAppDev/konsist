@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.core.declaration.KoFileDeclarationImpl
import kotlin.reflect.KClass

fun Sequence<KoFileDeclarationImpl>.withImports(vararg imports: String) = filter {
    when {
        imports.isEmpty() -> it.hasImports()
        else -> it.hasImports(*imports)
    }
}

fun Sequence<KoFileDeclarationImpl>.withSomeImports(vararg imports: String) = filter {
    imports.any { import -> it.hasImports(import) }
}

fun Sequence<KoFileDeclarationImpl>.withoutImports(vararg imports: String) = filter {
    when {
        imports.isEmpty() -> !it.hasImports()
        else -> !it.hasImports(*imports)
    }
}

fun Sequence<KoFileDeclarationImpl>.withPackage(vararg packages: String) = filter {
    packages.any { packagee -> it.hasPackage(packagee) }
}

fun Sequence<KoFileDeclarationImpl>.withoutPackage(vararg packages: String) = filter {
    packages.none { packagee -> it.hasPackage(packagee) }
}

fun Sequence<KoFileDeclarationImpl>.withAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

fun Sequence<KoFileDeclarationImpl>.withSomeAnnotations(vararg annotations: String) = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
}

fun Sequence<KoFileDeclarationImpl>.withoutAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

inline fun <reified T> Sequence<KoFileDeclarationImpl>.withAnnotationOf() = filter { it.hasAnnotationOf<T>() }

inline fun <reified T> Sequence<KoFileDeclarationImpl>.withoutAnnotationOf() = filterNot { it.hasAnnotationOf<T>() }

fun Sequence<KoFileDeclarationImpl>.withAnnotationsOf(vararg annotations: KClass<*>) = filter { it.hasAnnotationsOf(*annotations) }

fun Sequence<KoFileDeclarationImpl>.withSomeAnnotationsOf(vararg annotations: KClass<*>) = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

fun Sequence<KoFileDeclarationImpl>.withoutAnnotationsOf(vararg annotations: KClass<*>) = filter { !it.hasAnnotationsOf(*annotations) }

fun Sequence<KoFileDeclarationImpl>.withTypeAliases(vararg typeAliasNames: String) = filter {
    when {
        typeAliasNames.isEmpty() -> it.hasTypeAliases()
        else -> it.hasTypeAliases(*typeAliasNames)
    }
}

fun Sequence<KoFileDeclarationImpl>.withSomeTypeAliases(vararg typeAliasNames: String) = filter {
    typeAliasNames.any { typeAlias -> it.hasTypeAliases(typeAlias) }
}

fun Sequence<KoFileDeclarationImpl>.withoutTypeAliases(vararg typeAliasNames: String) = filter {
    when {
        typeAliasNames.isEmpty() -> !it.hasTypeAliases()
        else -> !it.hasTypeAliases(*typeAliasNames)
    }
}
