package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import kotlin.reflect.KClass

fun Sequence<KoFileDeclaration>.withImports(vararg imports: String) = filter {
    when {
        imports.isEmpty() -> it.hasImports()
        else -> it.hasImports(*imports)
    }
}

fun Sequence<KoFileDeclaration>.withSomeImports(vararg imports: String) = filter {
    imports.any { import -> it.hasImports(import) }
}

fun Sequence<KoFileDeclaration>.withoutImports(vararg imports: String) = filter {
    when {
        imports.isEmpty() -> !it.hasImports()
        else -> !it.hasImports(*imports)
    }
}

fun Sequence<KoFileDeclaration>.withPackage(vararg packages: String) = filter {
    packages.any { packagee -> it.hasPackage(packagee) }
}

fun Sequence<KoFileDeclaration>.withoutPackage(vararg packages: String) = filter {
    packages.none { packagee -> it.hasPackage(packagee) }
}

fun Sequence<KoFileDeclaration>.withAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

fun Sequence<KoFileDeclaration>.withSomeAnnotations(vararg annotations: String) = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
}

fun Sequence<KoFileDeclaration>.withoutAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

inline fun <reified T> Sequence<KoFileDeclaration>.withAnnotationOf() = filter { it.hasAnnotationOf<T>() }

inline fun <reified T> Sequence<KoFileDeclaration>.withoutAnnotationOf() = filterNot { it.hasAnnotationOf<T>() }

fun Sequence<KoFileDeclaration>.withAnnotationsOf(vararg annotations: KClass<*>) = filter { it.hasAnnotationsOf(*annotations) }

fun Sequence<KoFileDeclaration>.withSomeAnnotationsOf(vararg annotations: KClass<*>) = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

fun Sequence<KoFileDeclaration>.withoutAnnotationsOf(vararg annotations: KClass<*>) = filter { !it.hasAnnotationsOf(*annotations) }

fun Sequence<KoFileDeclaration>.withTypeAliases(vararg typeAliasNames: String) = filter {
    when {
        typeAliasNames.isEmpty() -> it.hasTypeAliases()
        else -> it.hasTypeAliases(*typeAliasNames)
    }
}

fun Sequence<KoFileDeclaration>.withSomeTypeAliases(vararg typeAliasNames: String) = filter {
    typeAliasNames.any { typeAlias -> it.hasTypeAliases(typeAlias) }
}

fun Sequence<KoFileDeclaration>.withoutTypeAliases(vararg typeAliasNames: String) = filter {
    when {
        typeAliasNames.isEmpty() -> !it.hasTypeAliases()
        else -> !it.hasTypeAliases(*typeAliasNames)
    }
}
