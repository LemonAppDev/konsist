package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import kotlin.reflect.KClass

/**
 * Sequence containing files that have imports.
 *
 * @param imports The import(s) to include.
 * @return A sequence containing files that have the specified import(s) (or any import if [imports] is empty).
 */
fun Sequence<KoFileDeclaration>.withImports(vararg imports: String): Sequence<KoFileDeclaration> = filter {
    when {
        imports.isEmpty() -> it.hasImports()
        else -> it.hasImports(*imports)
    }
}

/**
 * Sequence containing files that have some imports.
 *
 * @param imports The import(s) to include.
 * @return A sequence containing files that have at least one of the specified import(s).
 */
fun Sequence<KoFileDeclaration>.withSomeImports(vararg imports: String): Sequence<KoFileDeclaration> = filter {
    imports.any { import -> it.hasImports(import) }
}

/**
 * Sequence containing files that don't have imports.
 *
 * @param imports The import(s) to exclude.
 * @return A sequence containing files that don't have the specified import(s) (or none import if [imports] is empty).
 */
fun Sequence<KoFileDeclaration>.withoutImports(vararg imports: String): Sequence<KoFileDeclaration> = filter {
    when {
        imports.isEmpty() -> !it.hasImports()
        else -> !it.hasImports(*imports)
    }
}

/**
 * Sequence containing files that have package.
 *
 * @param packages The package names to include.
 * @return A sequence containing files that have a package matching any of the specified package names.
 */
fun Sequence<KoFileDeclaration>.withPackage(vararg packages: String): Sequence<KoFileDeclaration> = filter {
    packages.any { packagee -> it.hasPackage(packagee) }
}

/**
 * Sequence containing files that have some package.
 *
 * @param packages The package names to exclude.
 * @return A sequence containing files that don't have a package matching any of the specified package names.
 */
fun Sequence<KoFileDeclaration>.withoutPackage(vararg packages: String): Sequence<KoFileDeclaration> = filter {
    packages.none { packagee -> it.hasPackage(packagee) }
}

/**
 * Sequence containing files that have the annotations.
 *
 * @param annotations The annotations to include.
 * @return A sequence containing files that have all the specified annotations (or any annotation if [annotations] is empty).
 */
fun Sequence<KoFileDeclaration>.withAnnotations(vararg annotations: String): Sequence<KoFileDeclaration> = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing files that have some annotations.
 *
 * @param annotations The annotations to include.
 * @return A sequence containing files that have at least one of the specified annotations.
 */
fun Sequence<KoFileDeclaration>.withSomeAnnotations(vararg annotations: String): Sequence<KoFileDeclaration> = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
}

/**
 * Sequence containing files that don't have annotations.
 *
 * @param annotations The annotations to exclude.
 * @return A sequence containing files that don't have all the specified annotations (or none annotation if [annotations] is empty).
 */
fun Sequence<KoFileDeclaration>.withoutAnnotations(vararg annotations: String): Sequence<KoFileDeclaration> = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing files that have annotation of type.
 *
 * @return A sequence containing files that have the specified annotation.
 */
inline fun <reified T> Sequence<KoFileDeclaration>.withAnnotationOf(): Sequence<KoFileDeclaration> = filter { it.hasAnnotationOf<T>() }

/**
 * Sequence containing files that don't have some annotations of type.
 *
 * @return A sequence containing files that don't have the specified annotation.
 */
inline fun <reified T> Sequence<KoFileDeclaration>.withoutAnnotationOf(): Sequence<KoFileDeclaration> =
    filterNot { it.hasAnnotationOf<T>() }

/**
 * Sequence containing files that have some annotations of type.
 *
 * @param annotations The Kotlin class(es) representing annotation(s) to include.
 * @return A sequence containing files that have all the specified annotations.
 */
fun Sequence<KoFileDeclaration>.withAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFileDeclaration> =
    filter { it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing files that have some annotations of type.
 *
 * @param annotations The Kotlin class(es) representing annotation(s) to include.
 * @return A sequence containing files that have at least one of the specified the annotations.
 */
fun Sequence<KoFileDeclaration>.withSomeAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFileDeclaration> = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing files that don't have some annotations of type.
 *
 * @param annotations The Kotlin class(es) representing annotation(s) to exclude.
 * @return A sequence containing files that don't have all the specified annotations.
 */
fun Sequence<KoFileDeclaration>.withoutAnnotationsOf(vararg annotations: KClass<*>): Sequence<KoFileDeclaration> =
    filter { !it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing files that have type alias.
 *
 * @param typeAliasNames The type alias name(s) to include.
 * @return A sequence containing files that have all the specified type alias(es) (or any type alias if [typeAliasNames] is empty).
 */
fun Sequence<KoFileDeclaration>.withTypeAliases(vararg typeAliasNames: String): Sequence<KoFileDeclaration> = filter {
    when {
        typeAliasNames.isEmpty() -> it.hasTypeAliases()
        else -> it.hasTypeAliases(*typeAliasNames)
    }
}

/**
 * Sequence containing files that have some type aliases.
 *
 * @param typeAliasNames The type alias name(s) to include.
 * @return A sequence containing files that have at least one of the specified type alias(es).
 */
fun Sequence<KoFileDeclaration>.withSomeTypeAliases(vararg typeAliasNames: String): Sequence<KoFileDeclaration> = filter {
    typeAliasNames.any { typeAlias -> it.hasTypeAliases(typeAlias) }
}

/**
 * Sequence containing files that don't have type aliases.
 *
 * @param typeAliasNames The type alias name(s) to exclude.
 * @return A sequence containing files that don't have all the specified type alias(es) (or none type alias if [typeAliasNames] is empty).
 */
fun Sequence<KoFileDeclaration>.withoutTypeAliases(vararg typeAliasNames: String): Sequence<KoFileDeclaration> = filter {
    when {
        typeAliasNames.isEmpty() -> !it.hasTypeAliases()
        else -> !it.hasTypeAliases(*typeAliasNames)
    }
}
