package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have the 'public' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withPublicModifier(): Sequence<T> = filter { it.hasPublicModifier() }

/**
 * Sequence containing declarations that don't have the the 'public' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPublicModifier(): Sequence<T> = filterNot { it.hasPublicModifier() }

/**
 * Sequence containing declarations that have the 'public' or no visibility modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withPublicOrDefaultModifier(): Sequence<T> = filter { it.isPublicOrDefault() }

/**
 * Sequence containing declarations that don't have the the 'public' or no visibility modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPublicOrDefaultModifier(): Sequence<T> = filterNot { it.isPublicOrDefault() }

/**
 * Sequence containing declarations that have the 'private' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withPrivateModifier(): Sequence<T> = filter { it.hasPrivateModifier() }

/**
 * Sequence containing declarations that don't have the the 'private' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPrivateModifier(): Sequence<T> = filterNot { it.hasPrivateModifier() }

/**
 * Sequence containing declarations that have the 'protected' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withProtectedModifier(): Sequence<T> = filter { it.hasProtectedModifier() }

/**
 * Sequence containing declarations that don't have the the 'protected' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutProtectedModifier(): Sequence<T> = filterNot { it.hasProtectedModifier() }

/**
 * Sequence containing declarations that have the 'internal' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withInternalModifier(): Sequence<T> = filter { it.hasInternalModifier() }

/**
 * Sequence containing declarations that don't have the the 'internal' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutInternalModifier(): Sequence<T> = filterNot { it.hasInternalModifier() }

/**
 * Sequence containing the top level declarations.
 */
fun <T : KoDeclaration> Sequence<T>.withTopLevel(): Sequence<T> = filter { it.isTopLevel() }

/**
 * Sequence containing the non-top level declarations.
 */
fun <T : KoDeclaration> Sequence<T>.withoutTopLevel(): Sequence<T> = filterNot { it.isTopLevel() }

/**
 * Sequence containing declarations that have all annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withAnnotations(vararg annotations: String): Sequence<T> = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have some annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeAnnotations(vararg annotations: String): Sequence<T> = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
}

/**
 * Sequence containing declarations that don't have the annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withoutAnnotations(vararg annotations: String): Sequence<T> = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have all annotations of type.
 */
fun <T : KoDeclaration> Sequence<T>.withAnnotationsOf(vararg annotations: KClass<*>): Sequence<T> =
    filter { it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing declarations that have some annotations of type.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeAnnotationsOf(vararg annotations: KClass<*>): Sequence<T> = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing declarations that don't have the annotations of type.
 */
fun <T : KoDeclaration> Sequence<T>.withoutAnnotationsOf(vararg annotations: KClass<*>): Sequence<T> =
    filter { !it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing declarations that have all annotations of type.
 */
inline fun <reified T> Sequence<KoDeclaration>.withAnnotationOf(): Sequence<KoDeclaration> = filter { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that don't have the annotations of type.
 */
inline fun <reified T> Sequence<KoDeclaration>.withoutAnnotationOf(): Sequence<KoDeclaration> = filterNot { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that have all modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withModifiers(vararg modifiers: KoModifier): Sequence<T> = filter {
    when {
        modifiers.isEmpty() -> it.hasModifiers()
        else -> it.hasModifiers(*modifiers)
    }
}

/**
 * Sequence containing declarations that have some modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeModifiers(vararg modifiers: KoModifier): Sequence<T> = filter {
    modifiers.any { modifier -> it.hasModifiers(modifier) }
}

/**
 * Sequence containing declarations that don't have the modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withoutModifiers(vararg modifiers: KoModifier): Sequence<T> = filter {
    when {
        modifiers.isEmpty() -> !it.hasModifiers()
        else -> !it.hasModifiers(*modifiers)
    }
}

/**
 * Sequence containing declarations that have packages.
 */
fun <T : KoDeclaration> Sequence<T>.withPackage(vararg packages: String): Sequence<T> = filter {
    packages.any { packagee -> it.resideInPackage(packagee) }
}

/**
 * Sequence containing declarations that don't have the packages.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPackage(vararg packages: String): Sequence<T> = filter {
    packages.all { packagee -> it.resideOutsidePackage(packagee) }
}

/**
 * Sequence containing declarations that have KDoc.
 */
fun <T : KoDeclaration> Sequence<T>.withKDoc(): Sequence<T> = filter { it.hasKDoc() }

/**
 * Sequence containing declarations that don't have the KDoc.
 */
fun <T : KoDeclaration> Sequence<T>.withoutKDoc(): Sequence<T> = filterNot { it.hasKDoc() }

/**
 * Sequence containing declarations that have KDoc tags.
 */
fun <T : KoDeclaration> Sequence<T>.withKDocWithTags(vararg tags: KoKDocTag): Sequence<T> = filter { it.kDoc?.hasTags(*tags) ?: false }

/**
 * Sequence containing declarations that don't have the KDoc tags.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeKDocWithTags(vararg tags: KoKDocTag): Sequence<T> = filter {
    tags.any { tag -> it.kDoc?.hasTags(tag) ?: false }
}

/**
 * Sequence containing declarations that don't have the KDoc tags.
 */
fun <T : KoDeclaration> Sequence<T>.withoutKDocWithTags(vararg tags: KoKDocTag): Sequence<T> =
    filterNot { it.kDoc?.hasTags(*tags) ?: false }

/**
 * Print the declarations.
 */
fun <T : KoDeclaration> Sequence<T>.print(): Sequence<T> {
    forEach { println(it.toString()) }
    return this
}
