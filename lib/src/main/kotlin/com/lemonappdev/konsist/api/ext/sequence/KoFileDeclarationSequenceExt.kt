package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have imports.
 */
fun Sequence<KoFileDeclaration>.withImports(vararg imports: String) = filter {
    when {
        imports.isEmpty() -> it.hasImports()
        else -> it.hasImports(*imports)
    }
}

/**
 * Sequence containing declarations that have some imports.
 */
fun Sequence<KoFileDeclaration>.withSomeImports(vararg imports: String) = filter {
    imports.any { import -> it.hasImports(import) }
}

/**
 * Sequence containing declarations that don't have imports.
 */
fun Sequence<KoFileDeclaration>.withoutImports(vararg imports: String) = filter {
    when {
        imports.isEmpty() -> !it.hasImports()
        else -> !it.hasImports(*imports)
    }
}

/**
 * Sequence containing declarations that have package.
 */
fun Sequence<KoFileDeclaration>.withPackage(vararg packages: String) = filter {
    packages.any { packagee -> it.hasPackage(packagee) }
}

/**
 * Sequence containing declarations that have some package.
 */
fun Sequence<KoFileDeclaration>.withoutPackage(vararg packages: String) = filter {
    packages.none { packagee -> it.hasPackage(packagee) }
}

/**
 * Sequence containing declarations that have the annotations.
 */
fun Sequence<KoFileDeclaration>.withAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have some annotations.
 */
fun Sequence<KoFileDeclaration>.withSomeAnnotations(vararg annotations: String) = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
}

/**
 * Sequence containing declarations that don't have annotations.
 */
fun Sequence<KoFileDeclaration>.withoutAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have annotation of type.
 */
inline fun <reified T> Sequence<KoFileDeclaration>.withAnnotationOf() = filter { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that don't have some annotations of type.
 */
inline fun <reified T> Sequence<KoFileDeclaration>.withoutAnnotationOf() = filterNot { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that have some annotations of type.
 */
fun Sequence<KoFileDeclaration>.withAnnotationsOf(vararg annotations: KClass<*>) = filter { it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing declarations that have some annotations of type.
 */
fun Sequence<KoFileDeclaration>.withSomeAnnotationsOf(vararg annotations: KClass<*>) = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing declarations that don't have some annotations of type.
 */
fun Sequence<KoFileDeclaration>.withoutAnnotationsOf(vararg annotations: KClass<*>) = filter { !it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing declarations that have type alias.
 */
fun Sequence<KoFileDeclaration>.withTypeAliases(vararg typeAliasNames: String) = filter {
    when {
        typeAliasNames.isEmpty() -> it.hasTypeAliases()
        else -> it.hasTypeAliases(*typeAliasNames)
    }
}

/**
 * Sequence containing declarations that have some type aliases.
 */
fun Sequence<KoFileDeclaration>.withSomeTypeAliases(vararg typeAliasNames: String) = filter {
    typeAliasNames.any { typeAlias -> it.hasTypeAliases(typeAlias) }
}

/**
 * Sequence containing declarations that don't have type aliases.
 */
fun Sequence<KoFileDeclaration>.withoutTypeAliases(vararg typeAliasNames: String) = filter {
    when {
        typeAliasNames.isEmpty() -> !it.hasTypeAliases()
        else -> !it.hasTypeAliases(*typeAliasNames)
    }
}
