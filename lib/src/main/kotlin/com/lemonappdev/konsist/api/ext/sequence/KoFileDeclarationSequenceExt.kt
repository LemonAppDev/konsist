package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have imports.
 */
fun Sequence<KoFileDeclaration>.withImports(vararg imports: String): Sequence<KoFileDeclaration> = filter {
    when {
        imports.isEmpty() -> it.hasImports()
        else -> it.hasImports(*imports)
    }
}

/**
 * Sequence containing declarations that have some imports.
 */
fun Sequence<KoFileDeclaration>.withSomeImports(vararg imports: String): Sequence<KoFileDeclaration> = filter {
    imports.any { import -> it.hasImports(import) }
}

/**
 * Sequence containing declarations that don't have imports.
 */
fun Sequence<KoFileDeclaration>.withoutImports(vararg imports: String): Sequence<KoFileDeclaration> = filter {
    when {
        imports.isEmpty() -> !it.hasImports()
        else -> !it.hasImports(*imports)
    }
}

/**
 * Sequence containing declarations that have package.
 */
fun Sequence<KoFileDeclaration>.withPackage(vararg packages: String): Sequence<KoFileDeclaration> = filter {
    packages.any { packagee -> it.hasPackage(packagee) }
}

/**
 * Sequence containing declarations that have some package.
 */
fun Sequence<KoFileDeclaration>.withoutPackage(vararg packages: String): Sequence<KoFileDeclaration> = filter {
    packages.none { packagee -> it.hasPackage(packagee) }
}

/**
 * Sequence containing declarations that have the annotations.
 */
fun Sequence<KoFileDeclaration>.withAnnotations(vararg annotations: String): Sequence<KoFileDeclaration> = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have some annotations.
 */
fun Sequence<KoFileDeclaration>.withSomeAnnotations(vararg annotations: String): Sequence<KoFileDeclaration> = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
}

/**
 * Sequence containing declarations that don't have annotations.
 */
fun Sequence<KoFileDeclaration>.withoutAnnotations(vararg annotations: String): Sequence<KoFileDeclaration> = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have annotation of type.
 */
inline fun <reified T> Sequence<KoFileDeclaration>.withAnnotationOf(): Sequence<KoFileDeclaration> = filter { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that don't have some annotations of type.
 */
inline fun <reified T> Sequence<KoFileDeclaration>.withoutAnnotationOf(): Sequence<KoFileDeclaration> =
    filterNot { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that have some annotations of type.
 */
fun Sequence<KoFileDeclaration>.withAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFileDeclaration> =
    filter { it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing declarations that have some annotations of type.
 */
fun Sequence<KoFileDeclaration>.withSomeAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFileDeclaration> = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing declarations that don't have some annotations of type.
 */
fun Sequence<KoFileDeclaration>.withoutAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFileDeclaration> =
    filter { !it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing declarations that have type alias.
 */
fun Sequence<KoFileDeclaration>.withTypeAliases(vararg typeAliasNames: String): Sequence<KoFileDeclaration> = filter {
    when {
        typeAliasNames.isEmpty() -> it.hasTypeAliases()
        else -> it.hasTypeAliases(*typeAliasNames)
    }
}

/**
 * Sequence containing declarations that have some type aliases.
 */
fun Sequence<KoFileDeclaration>.withSomeTypeAliases(vararg typeAliasNames: String): Sequence<KoFileDeclaration> = filter {
    typeAliasNames.any { typeAlias -> it.hasTypeAliases(typeAlias) }
}

/**
 * Sequence containing declarations that don't have type aliases.
 */
fun Sequence<KoFileDeclaration>.withoutTypeAliases(vararg typeAliasNames: String): Sequence<KoFileDeclaration> = filter {
    when {
        typeAliasNames.isEmpty() -> !it.hasTypeAliases()
        else -> !it.hasTypeAliases(*typeAliasNames)
    }
}
