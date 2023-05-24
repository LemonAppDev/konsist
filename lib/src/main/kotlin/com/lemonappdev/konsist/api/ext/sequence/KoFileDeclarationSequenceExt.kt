package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have imports.
 */
fun Sequence<KoFile>.withImports(vararg imports: String): Sequence<KoFile> = filter {
    when {
        imports.isEmpty() -> it.hasImports()
        else -> it.hasImports(*imports)
    }
}

/**
 * Sequence containing declarations that have some imports.
 */
fun Sequence<KoFile>.withSomeImports(vararg imports: String): Sequence<KoFile> = filter {
    imports.any { import -> it.hasImports(import) }
}

/**
 * Sequence containing declarations that don't have imports.
 */
fun Sequence<KoFile>.withoutImports(vararg imports: String): Sequence<KoFile> = filter {
    when {
        imports.isEmpty() -> !it.hasImports()
        else -> !it.hasImports(*imports)
    }
}

/**
 * Sequence containing declarations that have package.
 */
fun Sequence<KoFile>.withPackage(vararg packages: String): Sequence<KoFile> = filter {
    packages.any { packagee -> it.hasPackage(packagee) }
}

/**
 * Sequence containing declarations that have some package.
 */
fun Sequence<KoFile>.withoutPackage(vararg packages: String): Sequence<KoFile> = filter {
    packages.none { packagee -> it.hasPackage(packagee) }
}

/**
 * Sequence containing declarations that have the annotations.
 */
fun Sequence<KoFile>.withAnnotations(vararg annotations: String): Sequence<KoFile> = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have some annotations.
 */
fun Sequence<KoFile>.withSomeAnnotations(vararg annotations: String): Sequence<KoFile> = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
}

/**
 * Sequence containing declarations that don't have annotations.
 */
fun Sequence<KoFile>.withoutAnnotations(vararg annotations: String): Sequence<KoFile> = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have annotation of type.
 */
inline fun <reified T> Sequence<KoFile>.withAnnotationOf(): Sequence<KoFile> = filter { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that don't have some annotations of type.
 */
inline fun <reified T> Sequence<KoFile>.withoutAnnotationOf(): Sequence<KoFile> =
    filterNot { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that have some annotations of type.
 */
fun Sequence<KoFile>.withAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFile> =
    filter { it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing declarations that have some annotations of type.
 */
fun Sequence<KoFile>.withSomeAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFile> = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing declarations that don't have some annotations of type.
 */
fun Sequence<KoFile>.withoutAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFile> =
    filter { !it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing declarations that have type alias.
 */
fun Sequence<KoFile>.withTypeAliases(vararg typeAliasNames: String): Sequence<KoFile> = filter {
    when {
        typeAliasNames.isEmpty() -> it.hasTypeAliases()
        else -> it.hasTypeAliases(*typeAliasNames)
    }
}

/**
 * Sequence containing declarations that have some type aliases.
 */
fun Sequence<KoFile>.withSomeTypeAliases(vararg typeAliasNames: String): Sequence<KoFile> = filter {
    typeAliasNames.any { typeAlias -> it.hasTypeAliases(typeAlias) }
}

/**
 * Sequence containing declarations that don't have type aliases.
 */
fun Sequence<KoFile>.withoutTypeAliases(vararg typeAliasNames: String): Sequence<KoFile> = filter {
    when {
        typeAliasNames.isEmpty() -> !it.hasTypeAliases()
        else -> !it.hasTypeAliases(*typeAliasNames)
    }
}
