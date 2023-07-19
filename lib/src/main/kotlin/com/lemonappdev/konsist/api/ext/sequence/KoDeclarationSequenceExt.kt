package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.ext.provider.hasAnnotationOf
import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.api.provider.KoResideInOrOutsidePackageProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoTopLevelProvider
import kotlin.reflect.KClass

/**
 * Sequence containing declarations with the `public` modifier.
 *
 * @return A sequence containing declarations with the `public` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withPublicModifier(): Sequence<T> = filter { it.hasPublicModifier() }

/**
 * Sequence containing declarations without `public` modifier.
 *
 * @return A sequence containing declarations without the `public` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutPublicModifier(): Sequence<T> = filterNot { it.hasPublicModifier() }

/**
 * Sequence containing declarations with the `public` or no visibility modifier.
 *
 * @return A sequence containing declarations with the `public` or no visibility modifier..
 */
fun <T : KoModifierProvider> Sequence<T>.withPublicOrDefaultModifier(): Sequence<T> = filter { it.isPublicOrDefault() }

/**
 * Sequence containing declarations without `public` or no visibility modifier.
 *
 * @return A sequence containing declarations without the `public` or no visibility modifier..
 */
fun <T : KoModifierProvider> Sequence<T>.withoutPublicOrDefaultModifier(): Sequence<T> = filterNot { it.isPublicOrDefault() }

/**
 * Sequence containing declarations with the `private` modifier.
 *
 * @return A sequence containing declarations with the `private` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withPrivateModifier(): Sequence<T> = filter { it.hasPrivateModifier() }

/**
 * Sequence containing declarations without `private` modifier.
 *
 * @return A sequence containing declarations without the `private` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutPrivateModifier(): Sequence<T> = filterNot { it.hasPrivateModifier() }

/**
 * Sequence containing declarations with the `protected` modifier.
 *
 * @return A sequence containing declarations with the `protected` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withProtectedModifier(): Sequence<T> = filter { it.hasProtectedModifier() }

/**
 * Sequence containing declarations without `protected` modifier.
 *
 * @return A sequence containing declarations without the `protected` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutProtectedModifier(): Sequence<T> = filterNot { it.hasProtectedModifier() }

/**
 * Sequence containing declarations with the `internal` modifier.
 *
 * @return A sequence containing declarations with the `internal` modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withInternalModifier(): Sequence<T> = filter { it.hasInternalModifier() }

/**
 * Sequence containing declarations without `internal` modifier.
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
 * Sequence containing declarations with any annotation.
 *
 * @return A sequence containing declarations with any annotation.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withAnnotations(): Sequence<T> = filter { it.hasAnnotations() }

/**
 * Sequence containing declarations with all annotations.
 *
 * @param annotation The annotation to include.
 * @param annotations The annotations to include.
 * @return A sequence containing declarations with all the specified annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withAllAnnotations(
    annotation: String,
    vararg annotations: String,
): Sequence<T> = filter {
    it.hasAnnotations(annotation, *annotations)
}

/**
 * Sequence containing declarations with some annotations.
 *
 * @param annotation The annotation to include.
 * @param annotations The annotations to include.
 * @return A sequence containing declarations with at least one of the specified annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withSomeAnnotations(annotation: String, vararg annotations: String): Sequence<T> =
    filter {
        it.hasAnnotations(annotation) || annotations.any { annotation -> it.hasAnnotations(annotation) }
    }

/**
 * Sequence containing declarations with no annotation.
 *
 * @return A sequence containing declarations with no annotation.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withoutAnnotations(): Sequence<T> = filterNot { it.hasAnnotations() }

/**
 * Sequence containing declarations without all the specified annotations.
 *
 * @param annotation The annotation to exclude.
 * @param annotations The annotations to exclude.
 * @return A sequence containing declarations without any of the specified annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withoutAllAnnotations(
    annotation: String,
    vararg annotations: String,
): Sequence<T> = filterNot {
    it.hasAnnotations(annotation, *annotations)
}

/**
 * Sequence containing declarations without some annotations.
 *
 * @param annotation The annotation to exclude.
 * @param annotations The annotations to exclude.
 * @return A sequence containing declarations without at least one of the specified annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withoutSomeAnnotations(
    annotation: String,
    vararg annotations: String,
): Sequence<T> = filter {
    !it.hasAnnotations(annotation) && if (annotations.isNotEmpty()) {
        annotations.any { annotation -> !it.hasAnnotations(annotation) }
    } else {
        true
    }
}

/**
 * Sequence containing declarations with all annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to include.
 * @param annotations The Kotlin classes representing annotations to include.
 * @return A sequence containing declarations with all the specified annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withAllAnnotationsOf(
    annotation: KClass<*>,
    vararg annotations: KClass<*>,
): Sequence<T> =
    filter { it.hasAnnotationsOf(annotation, *annotations) }

/**
 * Sequence containing declarations with some annotations of type.
 *
 * @param annotation The Kotlin class representing annotation to include.
 * @param annotations The Kotlin classes representing annotations to include.
 * @return A sequence containing declarations with at least one of the specified the annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withSomeAnnotationsOf(
    annotation: KClass<*>,
    vararg annotations: KClass<*>,
): Sequence<T> = filter {
    it.hasAnnotationsOf(annotation) || annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing declarations without specified annotations.
 *
 * @param annotation The Kotlin class representing annotation to exclude.
 * @param annotations The Kotlin classes representing annotations to exclude.
 * @return A sequence containing declarations without any of the specified annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withoutAllAnnotationsOf(
    annotation: KClass<*>,
    vararg annotations: KClass<*>,
): Sequence<T> =
    filterNot { it.hasAnnotationsOf(annotation, *annotations) }

/**
 * Sequence containing declarations without some annotations.
 *
 * @param annotation The Kotlin class representing annotation to exclude.
 * @param annotations The Kotlin classes representing annotations to exclude.
 * @return A sequence containing declarations without at least one of the specified annotations.
 */
fun <T : KoAnnotationProvider> Sequence<T>.withoutSomeAnnotationsOf(
    annotation: KClass<*>,
    vararg annotations: KClass<*>,
): Sequence<T> = filter {
    !it.hasAnnotationsOf(annotation) && if (annotations.isNotEmpty()) {
        annotations.any { annotation -> !it.hasAnnotationsOf(annotation) }
    } else {
        true
    }
}

/**
 * Sequence containing declarations with all annotations of type.
 *
 * @return A sequence containing declarations with the specified annotation.
 */
inline fun <reified T> Sequence<KoAnnotationProvider>.withAnnotationOf(): Sequence<KoAnnotationProvider> =
    filter { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations without annotations of type.
 *
 * @return A sequence containing declarations without specified annotation.
 */
inline fun <reified T> Sequence<KoAnnotationProvider>.withoutAnnotationOf(): Sequence<KoAnnotationProvider> =
    filterNot { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations with any specified modifier.
 *
 * @return A sequence containing declarations with any modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withModifiers(): Sequence<T> = filter { it.hasModifiers() }

/**
 * Sequence containing declarations with all the specified modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A sequence containing declarations with all the specified modifiers.
 */
fun <T : KoModifierProvider> Sequence<T>.withAllModifiers(modifier: KoModifier, vararg modifiers: KoModifier): Sequence<T> = filter {
    it.hasModifiers(modifier, *modifiers)
}

/**
 * Sequence containing declarations with some modifiers.
 *
 * @param modifier The modifier to include.
 * @param modifiers The modifiers to include.
 * @return A sequence containing declarations with at least one of the specified modifiers.
 */
fun <T : KoModifierProvider> Sequence<T>.withSomeModifiers(modifier: KoModifier, vararg modifiers: KoModifier): Sequence<T> = filter {
    it.hasModifiers(modifier) || modifiers.any { modifier -> it.hasModifiers(modifier) }
}

/**
 * Sequence containing declarations with no modifier.
 *
 * @return A sequence containing declarations with no modifier.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutModifiers(): Sequence<T> = filterNot { it.hasModifiers() }

/**
 * Sequence containing declarations without all specified modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A sequence containing declarations without all the specified modifiers.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutAllModifiers(modifier: KoModifier, vararg modifiers: KoModifier): Sequence<T> = filterNot {
    it.hasModifiers(modifier, *modifiers)
}

/**
 * Sequence containing declarations without some modifiers.
 *
 * @param modifier The modifier to exclude.
 * @param modifiers The modifiers to exclude.
 * @return A sequence containing declarations without at least one of the specified modifiers.
 */
fun <T : KoModifierProvider> Sequence<T>.withoutSomeModifiers(modifier: KoModifier, vararg modifiers: KoModifier): Sequence<T> = filter {
    !it.hasModifiers(modifier) && if (modifiers.isNotEmpty()) {
        modifiers.any { modifier -> !it.hasModifiers(modifier) }
    } else {
        true
    }
}

/**
 * Sequence containing declarations with package.
 *
 * @param packages The packages to include.
 * @return A sequence containing declarations that reside in any of the specified packages (or any package if [packages] is empty).
 */
fun <T : KoResideInOrOutsidePackageProvider> Sequence<T>.withPackage(vararg packages: String): Sequence<T> = filter {
    when {
        packages.isEmpty() -> it.packagee != null
        else -> packages.any { packagee -> it.resideInPackage(packagee) }
    }
}

/**
 * Sequence containing declarations without package.
 *
 * @param packages The packages to exclude.
 * @return A sequence containing declarations that don't reside in any of the specified packages (or none package if [packages] is empty).
 */
fun <T : KoResideInOrOutsidePackageProvider> Sequence<T>.withoutPackage(vararg packages: String): Sequence<T> = filter {
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
fun <T : KoTextProvider> Sequence<T>.print(): Sequence<T> {
    forEach { println(it.toString()) }
    return this
}
