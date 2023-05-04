package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoDocTag
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import kotlin.reflect.KClass

/**
 * Sequence containing declarations that have the 'public' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withPublicModifier() = filter { it.hasPublicModifier() }

/**
 * Sequence containing declarations that don't have the the 'public' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPublicModifier() = filterNot { it.hasPublicModifier() }

/**
 * Sequence containing declarations that have the 'public' or no visibility modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withPublicOrDefaultModifier() = filter { it.isPublicOrDefault() }

/**
 * Sequence containing declarations that don't have the the 'public' or no visibility modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPublicOrDefaultModifier() = filterNot { it.isPublicOrDefault() }

/**
 * Sequence containing declarations that have the 'private' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withPrivateModifier() = filter { it.hasPrivateModifier() }

/**
 * Sequence containing declarations that don't have the the 'private' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPrivateModifier() = filterNot { it.hasPrivateModifier() }

/**
 * Sequence containing declarations that have the 'protected' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withProtectedModifier() = filter { it.hasProtectedModifier() }

/**
 * Sequence containing declarations that don't have the the 'protected' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutProtectedModifier() = filterNot { it.hasProtectedModifier() }

/**
 * Sequence containing declarations that have the 'internal' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withInternalModifier() = filter { it.hasInternalModifier() }

/**
 * Sequence containing declarations that don't have the the 'internal' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutInternalModifier() = filterNot { it.hasInternalModifier() }

/**
 * Sequence containing the top level declarations.
 */
fun <T : KoDeclaration> Sequence<T>.withTopLevel() = filter { it.isTopLevel() }

/**
 * Sequence containing the non-top level declarations.
 */
fun <T : KoDeclaration> Sequence<T>.withoutTopLevel() = filterNot { it.isTopLevel() }

/**
 * Sequence containing declarations that have all annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have some annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeAnnotations(vararg annotations: String) = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
}

/**
 * Sequence containing declarations that don't have the annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withoutAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

/**
 * Sequence containing declarations that have all annotations of type.
 */
fun <T : KoDeclaration> Sequence<T>.withAnnotationsOf(vararg annotations: KClass<*>) = filter { it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing declarations that have some annotations of type.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeAnnotationsOf(vararg annotations: KClass<*>) = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Sequence containing declarations that don't have the annotations of type.
 */
fun <T : KoDeclaration> Sequence<T>.withoutAnnotationsOf(vararg annotations: KClass<*>) = filter { !it.hasAnnotationsOf(*annotations) }

/**
 * Sequence containing declarations that have all annotations of type.
 */
inline fun <reified T> Sequence<KoDeclaration>.withAnnotationOf() = filter { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that don't have the annotations of type.
 */
inline fun <reified T> Sequence<KoDeclaration>.withoutAnnotationOf() = filterNot { it.hasAnnotationOf<T>() }

/**
 * Sequence containing declarations that have all modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withModifiers(vararg modifiers: KoModifier) = filter {
    when {
        modifiers.isEmpty() -> it.hasModifiers()
        else -> it.hasModifiers(*modifiers)
    }
}

/**
 * Sequence containing declarations that have some modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeModifiers(vararg modifiers: KoModifier) = filter {
    modifiers.any { modifier -> it.hasModifiers(modifier) }
}

/**
 * Sequence containing declarations that don't have the modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withoutModifiers(vararg modifiers: KoModifier) = filter {
    when {
        modifiers.isEmpty() -> !it.hasModifiers()
        else -> !it.hasModifiers(*modifiers)
    }
}

/**
 * Sequence containing declarations that have packages.
 */
fun <T : KoDeclaration> Sequence<T>.withPackage(vararg packages: String) = filter {
    packages.any { packagee -> it.resideInPackage(packagee) }
}

/**
 * Sequence containing declarations that don't have the packages.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPackage(vararg packages: String) = filter {
    packages.all { packagee -> it.resideOutsidePackage(packagee) }
}

/**
 * Sequence containing declarations that have KDoc.
 */
fun <T : KoDeclaration> Sequence<T>.withKoDoc() = filter { it.hasKoDoc() }

/**
 * Sequence containing declarations that don't have the KDoc.
 */
fun <T : KoDeclaration> Sequence<T>.withoutKoDoc() = filterNot { it.hasKoDoc() }

/**
 * Sequence containing declarations that have KDoc tags.
 */
fun <T : KoDeclaration> Sequence<T>.withKoDocWithTags(vararg tags: KoDocTag) = filter { it.koDoc?.hasTags(*tags) ?: false }

/**
 * Sequence containing declarations that don't have the KDoc tags.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeKoDocWithTags(vararg tags: KoDocTag) = filter {
    tags.any { tag -> it.koDoc?.hasTags(tag) ?: false }
}

/**
 * Sequence containing declarations that don't have the KDoc tags.
 */
fun <T : KoDeclaration> Sequence<T>.withoutKoDocWithTags(vararg tags: KoDocTag) = filterNot { it.koDoc?.hasTags(*tags) ?: false }

/**
 * Print the declarations.
 */
fun <T : KoDeclaration> Sequence<T>.print(): Sequence<T> {
    forEach { println(it.toString()) }
    return this
}
