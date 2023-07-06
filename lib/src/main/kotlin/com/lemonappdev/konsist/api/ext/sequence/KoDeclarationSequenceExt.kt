package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import com.lemonappdev.konsist.api.provider.KoAnnotationDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoPackageDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have the `public` modifier.
 *
 * @return A sequence containing declarations with the `public` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withPublicModifier(): Sequence<T> = filter { it.hasPublicModifier() }

/**
 * Sequence containing declarations that don't have the `public` modifier.
 *
 * @return A sequence containing declarations without the `public` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutPublicModifier(): Sequence<T> = filterNot { it.hasPublicModifier() }

/**
 * Sequence containing declarations that have the `public` or no visibility modifier.
 *
 * @return A sequence containing declarations with the `public` or no visibility modifier..
 */
fun <T : KoModifierProvider> Sequence<T>.withPublicOrDefaultModifier(): Sequence<T> = filter { it.isPublicOrDefault() }

/**
 * Sequence containing declarations that don't have the `public` or no visibility modifier.
 *
 * @return A sequence containing declarations without the `public` or no visibility modifier..
 */
fun <T : KoModifierProvider> Sequence<T>.withoutPublicOrDefaultModifier(): Sequence<T> = filterNot { it.isPublicOrDefault() }

/**
 * Sequence containing declarations that have the `private` modifier.
 *
 * @return A sequence containing declarations with the `private` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withPrivateModifier(): Sequence<T> = filter { it.hasPrivateModifier() }

/**
 * Sequence containing declarations that don't have the `private` modifier.
 *
 * @return A sequence containing declarations without the `private` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutPrivateModifier(): Sequence<T> = filterNot { it.hasPrivateModifier() }

/**
 * Sequence containing declarations that have the `protected` modifier.
 *
 * @return A sequence containing declarations with the `protected` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withProtectedModifier(): Sequence<T> = filter { it.hasProtectedModifier() }

/**
 * Sequence containing declarations that don't have the `protected` modifier.
 *
 * @return A sequence containing declarations without the `protected` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutProtectedModifier(): Sequence<T> = filterNot { it.hasProtectedModifier() }

/**
 * Sequence containing declarations that have the `internal` modifier.
 *
 * @return A sequence containing declarations with the `internal` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withInternalModifier(): Sequence<T> = filter { it.hasInternalModifier() }

/**
 * Sequence containing declarations that don't have the `internal` modifier.
 *
 * @return A sequence containing declarations without the `internal` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutInternalModifier(): Sequence<T> = filterNot { it.hasInternalModifier() }

/**
 * Sequence containing the top level declarations.
 *
 * @return A sequence containing the top-level declarations.
 */
fun <T : KoTopLevelProvider> Sequence<T>.withTopLevel(): Sequence<T> = filter { it.isTopLevel() }

/**
 * Sequence containing the non-top level declarations.
 *
 * @return A sequence containing the non-top level declarations.
 */
fun <T : KoTopLevelProvider> Sequence<T>.withoutTopLevel(): Sequence<T> = filterNot { it.isTopLevel() }

/**
 * Sequence containing declarations that have all annotations.
 *
 * @param annotations The annotations to include.
 * @return A sequence containing declarations that have all the specified annotations (or any annotation if [annotations] is empty).
 */
fun <T : KoAnnotationDeclarationProvider> Sequence<T>.withAnnotations(vararg annotations: String): Sequence<T> = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have some annotations.
 *
 * @param annotation The annotation to include.
 * @param annotations The annotations to include.
 * @return A sequence containing declarations that have at least one of the specified annotations.
 */
fun <T : KoAnnotationDeclarationProvider> Sequence<T>.withSomeAnnotations(annotation: String, vararg annotations: String): Sequence<T> = filter {
    it.hasAnnotations(annotation) || annotations.any { annotation -> it.hasAnnotations(annotation) }
}

/**
 * Sequence containing declarations that don't have the annotations.
 *
 * @param annotations The annotations to exclude.
 * @return A sequence containing declarations that don't have any of the specified annotations
 * (or none annotation if [annotations] is empty).
 */
fun <T : KoAnnotationDeclarationProvider> Sequence<T>.withoutAnnotations(vararg annotations: String): Sequence<T> = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have all annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to include.
 * @param annotations The Kotlin classes representing annotations to include.
 * @return A sequence containing declarations that have all the specified annotations.
 */
fun <T : KoAnnotationDeclarationProvider> Sequence<T>.withAnnotationsOf(annotation: KClass<*>, vararg annotations: KClass<*>): Sequence<T> =
    filter { it.hasAnnotationsOf(annotation, *annotations) }

/**
 * Sequence containing declarations that have some annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to include.
 * @param annotations The Kotlin classes representing annotations to include.
 * @return A sequence containing declarations that have at least one of the specified the annotations.
 */
fun <T : KoAnnotationDeclarationProvider> Sequence<T>.withSomeAnnotationsOf(annotation: KClass<*>, vararg annotations: KClass<*>): Sequence<T> = filter {
    it.hasAnnotationsOf(annotation) || annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing declarations that don't have the annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to exclude.
 * @param annotations The Kotlin classes representing annotations to exclude.
 * @return A sequence containing declarations that don't have any of the specified annotations.
 */
fun <T : KoAnnotationDeclarationProvider> Sequence<T>.withoutAnnotationsOf(annotation: KClass<*>, vararg annotations: KClass<*>): Sequence<T> =
    filter { !it.hasAnnotationsOf(annotation, *annotations) }

/**
 * Sequence containing declarations that have all annotations of type.
 *
 * @return A sequence containing declarations that have the specified annotation.
 */
inline fun <reified T> Sequence<KoAnnotationDeclarationProvider>.withAnnotationOf(): Sequence<KoAnnotationDeclarationProvider> = filter { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that don't have the annotations of type.
 *
 * @return A sequence containing declarations that don't have the specified annotation.
 */
inline fun <reified T> Sequence<KoAnnotationDeclarationProvider>.withoutAnnotationOf(): Sequence<KoAnnotationDeclarationProvider> = filterNot { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that have all modifiers.
 *
 * @param modifiers The modifiers to include.
 * @return A sequence containing declarations that have all the specified modifiers (or any modifier if [modifiers] is empty).
 */
fun <T : KoModifierProvider> Sequence<T>.withModifiers(vararg modifiers: KoModifier): Sequence<T> = filter {
    when {
        modifiers.isEmpty() -> it.hasModifiers()
        else -> it.hasModifiers(*modifiers)
    }
}

/**
 * Sequence containing declarations that have some modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A sequence containing declarations that have at least one of the specified modifiers.
 */
fun <T : KoModifierProvider> Sequence<T>.withSomeModifiers(modifier: KoModifier, vararg modifiers: KoModifier): Sequence<T> = filter {
    it.hasModifiers(modifier) || modifiers.any { modifier -> it.hasModifiers(modifier) }
}

/**
 * Sequence containing declarations that don't have the modifiers.
 *
 * @param modifiers The modifiers to exclude.
 * @return A sequence containing declarations that don't have any of the specified modifiers (or none modifier if [modifiers] is empty).
 */
fun <T : KoModifierProvider> Sequence<T>.withoutModifiers(vararg modifiers: KoModifier): Sequence<T> = filter {
    when {
        modifiers.isEmpty() -> !it.hasModifiers()
        else -> !it.hasModifiers(*modifiers)
    }
}

/**
 * Sequence containing declarations that have packages.
 *
 * @param packagee The package to include.
 * @param packages The packages to include.
 * @return A sequence containing declarations that reside in any of the specified packages.

 */
fun <T : KoPackageDeclarationProvider> Sequence<T>.withPackage(packagee: String, vararg packages: String): Sequence<T> = filter {
    it.resideInPackage(packagee) || packages.any { packagee -> it.resideInPackage(packagee) }
}

/**
 * Sequence containing declarations that don't have the packages.
 *
 * @param packagee The package to exclude.
 * @param packages The packages to exclude.
 * @return A sequence containing declarations that don't reside in any of the specified packages.
 */
fun <T : KoPackageDeclarationProvider> Sequence<T>.withoutPackage(packagee: String, vararg packages: String): Sequence<T> = filter {
    it.resideOutsidePackage(packagee) && packages.all { packagee -> it.resideOutsidePackage(packagee) }
}

/**
 * Print the declarations.
 *
 * @return The original sequence of declarations.
 */
fun <T : KoTextProvider> Sequence<T>.print(): Sequence<T> {
    forEach { println(it.toString()) }
    return this
}
