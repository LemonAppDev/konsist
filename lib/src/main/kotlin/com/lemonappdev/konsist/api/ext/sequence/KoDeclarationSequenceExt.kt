package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have the 'public' modifier.
 *
 * @return A sequence containing declarations with the 'public' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withPublicModifier(): Sequence<T> = filter { it.hasPublicModifier() }

/**
 * Sequence containing declarations that don't have the 'public' modifier.
 *
 * @return A sequence containing declarations without the 'public' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPublicModifier(): Sequence<T> = filterNot { it.hasPublicModifier() }

/**
 * Sequence containing declarations that have the 'public' or no visibility modifier.
 *
 * @return A sequence containing declarations with the 'public' or no visibility modifier..
 */
fun <T : KoDeclaration> Sequence<T>.withPublicOrDefaultModifier(): Sequence<T> = filter { it.isPublicOrDefault() }

/**
 * Sequence containing declarations that don't have the 'public' or no visibility modifier.
 *
 * @return A sequence containing declarations without the 'public' or no visibility modifier..
 */
fun <T : KoDeclaration> Sequence<T>.withoutPublicOrDefaultModifier(): Sequence<T> = filterNot { it.isPublicOrDefault() }

/**
 * Sequence containing declarations that have the 'private' modifier.
 *
 * @return A sequence containing declarations with the 'private' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withPrivateModifier(): Sequence<T> = filter { it.hasPrivateModifier() }

/**
 * Sequence containing declarations that don't have the 'private' modifier.
 *
 * @return A sequence containing declarations without the 'private' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPrivateModifier(): Sequence<T> = filterNot { it.hasPrivateModifier() }

/**
 * Sequence containing declarations that have the 'protected' modifier.
 *
 * @return A sequence containing declarations with the 'protected' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withProtectedModifier(): Sequence<T> = filter { it.hasProtectedModifier() }

/**
 * Sequence containing declarations that don't have the 'protected' modifier.
 *
 * @return A sequence containing declarations without the 'protected' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutProtectedModifier(): Sequence<T> = filterNot { it.hasProtectedModifier() }

/**
 * Sequence containing declarations that have the 'internal' modifier.
 *
 * @return A sequence containing declarations with the 'internal' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withInternalModifier(): Sequence<T> = filter { it.hasInternalModifier() }

/**
 * Sequence containing declarations that don't have the 'internal' modifier.
 *
 * @return A sequence containing declarations without the 'internal' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutInternalModifier(): Sequence<T> = filterNot { it.hasInternalModifier() }

/**
 * Sequence containing the top level declarations.
 *
 * @return A sequence containing the top-level declarations.
 */
fun <T : KoDeclaration> Sequence<T>.withTopLevel(): Sequence<T> = filter { it.isTopLevel() }

/**
 * Sequence containing the non-top level declarations.
 *
 * @return A sequence containing the non-top level declarations.
 */
fun <T : KoDeclaration> Sequence<T>.withoutTopLevel(): Sequence<T> = filterNot { it.isTopLevel() }

/**
 * Sequence containing declarations that have all annotations.
 *
 * @param annotations The annotations to include. If empty, all declarations with annotations are included.
 * @return A sequence containing declarations that have all the specified annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withAnnotations(vararg annotations: String): Sequence<T> = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have some annotations.
 *
 * @param annotations The annotations to include.
 * @return A sequence containing declarations that have at least one of the specified annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeAnnotations(vararg annotations: String): Sequence<T> = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
}

/**
 * Sequence containing declarations that don't have the annotations.
 *
 * @param annotations The annotations to exclude. If empty, all declarations without annotations are included.
 * @return A sequence containing declarations that don't have any of the specified annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withoutAnnotations(vararg annotations: String): Sequence<T> = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have all annotations of type.
 *
 * @param annotations The Kotlin class(es) representing annotation(s) to include.
 * @return A sequence containing declarations that have all the specified annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withAnnotationsOf(vararg annotations: KClass<*>): Sequence<T> =
    filter { it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing declarations that have some annotations of type.
 *
 * @param annotations The Kotlin class(es) representing annotation(s) to include.
 * @return A sequence containing declarations that have at least one of the specified the annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeAnnotationsOf(vararg annotations: KClass<*>): Sequence<T> = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing declarations that don't have the annotations of type.
 *
 * @param annotations The Kotlin class(es) representing annotation(s) to exclude.
 * @return A sequence containing declarations that don't have any of the specified annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withoutAnnotationsOf(vararg annotations: KClass<*>): Sequence<T> =
    filter { !it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing declarations that have all annotations of type.
 *
 * @return A sequence containing declarations that have the specified annotation.
 */
inline fun <reified T> Sequence<KoDeclaration>.withAnnotationOf(): Sequence<KoDeclaration> = filter { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that don't have the annotations of type.
 *
 * @return A sequence containing declarations that don't have the specified annotation.
 */
inline fun <reified T> Sequence<KoDeclaration>.withoutAnnotationOf(): Sequence<KoDeclaration> = filterNot { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that have all modifiers.
 *
 * @param modifiers The modifiers to include. If empty, all declarations with modifiers are included.
 * @return A sequence containing declarations that have all the specified modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withModifiers(vararg modifiers: KoModifier): Sequence<T> = filter {
    when {
        modifiers.isEmpty() -> it.hasModifiers()
        else -> it.hasModifiers(*modifiers)
    }
}

/**
 * Sequence containing declarations that have some modifiers.
 *
 * @param modifiers The modifiers to include.
 * @return A sequence containing declarations that have at least one of the specified modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeModifiers(vararg modifiers: KoModifier): Sequence<T> = filter {
    modifiers.any { modifier -> it.hasModifiers(modifier) }
}

/**
 * Sequence containing declarations that don't have the modifiers.
 *
 * @param modifiers The modifiers to exclude. If empty, all declarations without modifiers are included.
 * @return A sequence containing declarations that don't have any of the specified modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withoutModifiers(vararg modifiers: KoModifier): Sequence<T> = filter {
    when {
        modifiers.isEmpty() -> !it.hasModifiers()
        else -> !it.hasModifiers(*modifiers)
    }
}

/**
 * Sequence containing declarations that have packages.
 *
 * @param packages The packages to include.
 * @return A sequence containing declarations that reside in any of the specified packages.

 */
fun <T : KoDeclaration> Sequence<T>.withPackage(vararg packages: String): Sequence<T> = filter {
    packages.any { packagee -> it.resideInPackage(packagee) }
}

/**
 * Sequence containing declarations that don't have the packages.
 *
 * @param packages The packages to exclude.
 * @return A sequence containing declarations that don't reside in any of the specified packages.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPackage(vararg packages: String): Sequence<T> = filter {
    packages.all { packagee -> it.resideOutsidePackage(packagee) }
}

/**
 * Print the declarations.
 *
 * @return The original sequence of declarations.
 */
fun <T : KoDeclaration> Sequence<T>.print(): Sequence<T> {
    forEach { println(it.toString()) }
    return this
}
