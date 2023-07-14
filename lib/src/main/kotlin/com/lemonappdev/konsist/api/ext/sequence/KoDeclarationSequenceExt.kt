package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have the `public` modifier.
 *
 * @return A sequence containing declarations with the `public` modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withPublicModifier(): Sequence<T> = filter { it.hasPublicModifier() }

/**
 * Sequence containing declarations that don't have the `public` modifier.
 *
 * @return A sequence containing declarations without the `public` modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPublicModifier(): Sequence<T> = filterNot { it.hasPublicModifier() }

/**
 * Sequence containing declarations that have the `public` or no visibility modifier.
 *
 * @return A sequence containing declarations with the `public` or no visibility modifier..
 */
fun <T : KoDeclaration> Sequence<T>.withPublicOrDefaultModifier(): Sequence<T> = filter { it.isPublicOrDefault() }

/**
 * Sequence containing declarations that don't have the `public` or no visibility modifier.
 *
 * @return A sequence containing declarations without the `public` or no visibility modifier..
 */
fun <T : KoDeclaration> Sequence<T>.withoutPublicOrDefaultModifier(): Sequence<T> = filterNot { it.isPublicOrDefault() }

/**
 * Sequence containing declarations that have the `private` modifier.
 *
 * @return A sequence containing declarations with the `private` modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withPrivateModifier(): Sequence<T> = filter { it.hasPrivateModifier() }

/**
 * Sequence containing declarations that don't have the `private` modifier.
 *
 * @return A sequence containing declarations without the `private` modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPrivateModifier(): Sequence<T> = filterNot { it.hasPrivateModifier() }

/**
 * Sequence containing declarations that have the `protected` modifier.
 *
 * @return A sequence containing declarations with the `protected` modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withProtectedModifier(): Sequence<T> = filter { it.hasProtectedModifier() }

/**
 * Sequence containing declarations that don't have the `protected` modifier.
 *
 * @return A sequence containing declarations without the `protected` modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutProtectedModifier(): Sequence<T> = filterNot { it.hasProtectedModifier() }

/**
 * Sequence containing declarations that have the `internal` modifier.
 *
 * @return A sequence containing declarations with the `internal` modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withInternalModifier(): Sequence<T> = filter { it.hasInternalModifier() }

/**
 * Sequence containing declarations that don't have the `internal` modifier.
 *
 * @return A sequence containing declarations without the `internal` modifier.
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
 * Sequence containing declarations that have any annotation.
 *
 * @return A sequence containing declarations that have any annotation.
 */
fun <T : KoDeclaration> Sequence<T>.withAnnotations(): Sequence<T> = filter { it.hasAnnotations() }

/**
 * Sequence containing declarations that have all annotations.
 *
 * @param annotation The annotation to include.
 * @param annotations The annotations to include.
 * @return A sequence containing declarations that have all the specified annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withAllAnnotations(annotation: String, vararg annotations: String): Sequence<T> = filter {
    it.hasAnnotations(annotation, *annotations)
}

/**
 * Sequence containing declarations that have some annotations.
 *
 * @param annotation The annotation to include.
 * @param annotations The annotations to include.
 * @return A sequence containing declarations that have at least one of the specified annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeAnnotations(annotation: String, vararg annotations: String): Sequence<T> = filter {
    it.hasAnnotations(annotation) || annotations.any { annotation -> it.hasAnnotations(annotation) }
}

/**
 * Sequence containing declarations that have no annotation.
 *
 * @return A sequence containing declarations that have no annotation.
 */
fun <T : KoDeclaration> Sequence<T>.withoutAnnotations(): Sequence<T> = filterNot { it.hasAnnotations() }

/**
 * Sequence containing declarations that don't have all the specified annotations.
 *
 * @param annotation The annotation to exclude.
 * @param annotations The annotations to exclude.
 * @return A sequence containing declarations that don't have any of the specified annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withoutAllAnnotations(annotation: String, vararg annotations: String): Sequence<T> = filterNot {
    it.hasAnnotations(annotation, *annotations)
}

/**
 * Sequence containing declarations that don't have some annotations.
 *
 * @param annotation The annotation to exclude.
 * @param annotations The annotations to exclude.
 * @return A sequence containing declarations that don't have at least one of the specified annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withoutSomeAnnotations(annotation: String, vararg annotations: String): Sequence<T> = filter {
    !it.hasAnnotations(annotation) && if (annotations.isNotEmpty()) {
        annotations.any { annotation -> !it.hasAnnotations(annotation) }
    } else {
        true
    }
}

/**
 * Sequence containing declarations that have all annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to include.
 * @param annotations The Kotlin classes representing annotations to include.
 * @return A sequence containing declarations that have all the specified annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withAllAnnotationsOf(annotation: KClass<*>, vararg annotations: KClass<*>): Sequence<T> =
    filter { it.hasAnnotationsOf(annotation, *annotations) }

/**
 * Sequence containing declarations that have some annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to include.
 * @param annotations The Kotlin classes representing annotations to include.
 * @return A sequence containing declarations that have at least one of the specified the annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeAnnotationsOf(annotation: KClass<*>, vararg annotations: KClass<*>): Sequence<T> = filter {
    it.hasAnnotationsOf(annotation) || annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing declarations that don't have the specified annotations.
 *
 * @param annotation The Kotlin class representing annotation to exclude.
 * @param annotations The Kotlin classes representing annotations to exclude.
 * @return A sequence containing declarations that don't have any of the specified annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withoutAllAnnotationsOf(annotation: KClass<*>, vararg annotations: KClass<*>): Sequence<T> =
    filterNot { it.hasAnnotationsOf(annotation, *annotations) }

/**
 * Sequence containing declarations that don't have some annotations.
 *
 * @param annotation The Kotlin class representing annotation to exclude.
 * @param annotations The Kotlin classes representing annotations to exclude.
 * @return A sequence containing declarations that don't have at least one of the specified annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withoutSomeAnnotationsOf(annotation: KClass<*>, vararg annotations: KClass<*>): Sequence<T> = filter {
    !it.hasAnnotationsOf(annotation) && if (annotations.isNotEmpty()) {
        annotations.any { annotation -> !it.hasAnnotationsOf(annotation) }
    } else {
        true
    }
}

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
 * Sequence containing declarations that have any modifier.
 *
 * @return A sequence containing declarations that have any modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withModifiers(): Sequence<T> = filter { it.hasModifiers() }

/**
 * Sequence containing declarations that have all the specified modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A sequence containing declarations that have all the specified modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withAllModifiers(modifier: KoModifier, vararg modifiers: KoModifier): Sequence<T> = filter {
    it.hasModifiers(modifier, *modifiers)
}

/**
 * Sequence containing declarations that have some modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A sequence containing declarations that have at least one of the specified modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeModifiers(modifier: KoModifier, vararg modifiers: KoModifier): Sequence<T> = filter {
    it.hasModifiers(modifier) || modifiers.any { modifier -> it.hasModifiers(modifier) }
}

/**
 * Sequence containing declarations that have no modifier.
 *
 * @return A sequence containing declarations that have no modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutModifiers(): Sequence<T> = filterNot { it.hasModifiers() }

/**
 * Sequence containing declarations that don't have all specified modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A sequence containing declarations that don't have all the specified modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withoutAllModifiers(modifier: KoModifier, vararg modifiers: KoModifier): Sequence<T> = filterNot {
    it.hasModifiers(modifier, *modifiers)
}

/**
 * Sequence containing declarations that don't have some modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A sequence containing declarations that don't have at least one of the specified modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withoutSomeModifiers(modifier: KoModifier, vararg modifiers: KoModifier): Sequence<T> = filter {
    !it.hasModifiers(modifier) && if (modifiers.isNotEmpty()) {
        modifiers.any { modifier -> !it.hasModifiers(modifier) }
    } else {
        true
    }
}

/**
 * Sequence containing declarations that have package.
 *
 * @param packages The packages to include.
 * @return A sequence containing declarations that reside in any of the specified packages (or any package if [packages] is empty).
 */
fun <T : KoDeclaration> Sequence<T>.withPackage(vararg packages: String): Sequence<T> = filter {
    when {
        packages.isEmpty() -> it.packagee != null
        else -> packages.any { packagee -> it.resideInPackage(packagee) }
    }
}

/**
 * Sequence containing declarations that don't have the package.
 *
 * @param packages The packages to exclude.
 * @return A sequence containing declarations that don't reside in any of the specified packages (or none package if [packages] is empty).
 */
fun <T : KoDeclaration> Sequence<T>.withoutPackage(vararg packages: String): Sequence<T> = filter {
    when {
        packages.isEmpty() -> it.packagee == null
        else -> packages.all { packagee -> it.resideOutsidePackage(packagee) }
    }
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
