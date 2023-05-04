package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoDocTag
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.ext.declaration.hasAnnotationOf
import kotlin.reflect.KClass

/**
 * Filter declarations with the 'public' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withPublicModifier() = filter { it.hasPublicModifier() }

/**
 * Filter declarations without the 'public' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPublicModifier() = filterNot { it.hasPublicModifier() }

/**
 * Filter declarations with the 'public' or no visibility modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withPublicOrDefaultModifier() = filter { it.isPublicOrDefault() }

/**
 * Filter declarations without the 'public' or no visibility modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPublicOrDefaultModifier() = filterNot { it.isPublicOrDefault() }

/**
 * Filter declarations with the 'private' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withPrivateModifier() = filter { it.hasPrivateModifier() }

/**
 * Filter declarations without the 'private' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPrivateModifier() = filterNot { it.hasPrivateModifier() }

/**
 * Filter declarations with the 'protected' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withProtectedModifier() = filter { it.hasProtectedModifier() }

/**
 * Filter declarations without the 'protected' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutProtectedModifier() = filterNot { it.hasProtectedModifier() }

/**
 * Filter declarations with the 'internal' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withInternalModifier() = filter { it.hasInternalModifier() }

/**
 * Filter declarations without the 'internal' modifier.
 */
fun <T : KoDeclaration> Sequence<T>.withoutInternalModifier() = filterNot { it.hasInternalModifier() }

/**
 * Filter the top level declarations.
 */
fun <T : KoDeclaration> Sequence<T>.withTopLevel() = filter { it.isTopLevel() }

/**
 * Filter the non-top level declarations.
 */
fun <T : KoDeclaration> Sequence<T>.withoutTopLevel() = filterNot { it.isTopLevel() }

/**
 * Filter declarations with all annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

/**
 * Filter declarations with some annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeAnnotations(vararg annotations: String) = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
}

/**
 * Filter declarations without annotations.
 */
fun <T : KoDeclaration> Sequence<T>.withoutAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

/**
 * Filter declarations with all annotations of type.
 */
fun <T : KoDeclaration> Sequence<T>.withAnnotationsOf(vararg annotations: KClass<*>) = filter { it.hasAnnotationsOf(*annotations) }

/**
 * Filter declarations with some annotations of type.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeAnnotationsOf(vararg annotations: KClass<*>) = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

/**
 * Filter declarations without annotations of type.
 */
fun <T : KoDeclaration> Sequence<T>.withoutAnnotationsOf(vararg annotations: KClass<*>) = filter { !it.hasAnnotationsOf(*annotations) }

/**
 * Filter declarations with all annotations of type.
 */
inline fun <reified T> Sequence<KoDeclaration>.withAnnotationOf() = filter { it.hasAnnotationOf<T>() }

/**
 * Filter declarations without annotations of type.
 */
inline fun <reified T> Sequence<KoDeclaration>.withoutAnnotationOf() = filterNot { it.hasAnnotationOf<T>() }

/**
 * Filter declarations with all modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withModifiers(vararg modifiers: KoModifier) = filter {
    when {
        modifiers.isEmpty() -> it.hasModifiers()
        else -> it.hasModifiers(*modifiers)
    }
}

/**
 * Filter declarations with some modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeModifiers(vararg modifiers: KoModifier) = filter {
    modifiers.any { modifier -> it.hasModifiers(modifier) }
}

/**
 * Filter declarations without modifiers.
 */
fun <T : KoDeclaration> Sequence<T>.withoutModifiers(vararg modifiers: KoModifier) = filter {
    when {
        modifiers.isEmpty() -> !it.hasModifiers()
        else -> !it.hasModifiers(*modifiers)
    }
}

/**
 * Filter declarations with packages.
 */
fun <T : KoDeclaration> Sequence<T>.withPackage(vararg packages: String) = filter {
    packages.any { packagee -> it.resideInPackage(packagee) }
}

/**
 * Filter declarations without packages.
 */
fun <T : KoDeclaration> Sequence<T>.withoutPackage(vararg packages: String) = filter {
    packages.all { packagee -> it.resideOutsidePackage(packagee) }
}

/**
 * Filter declarations with KDoc.
 */
fun <T : KoDeclaration> Sequence<T>.withKoDoc() = filter { it.hasKoDoc() }

/**
 * Filter declarations without KDoc.
 */
fun <T : KoDeclaration> Sequence<T>.withoutKoDoc() = filterNot { it.hasKoDoc() }

/**
 * Filter declarations with KDoc tags.
 */
fun <T : KoDeclaration> Sequence<T>.withKoDocWithTags(vararg tags: KoDocTag) = filter { it.koDoc?.hasTags(*tags) ?: false }

/**
 * Filter declarations without KDoc tags.
 */
fun <T : KoDeclaration> Sequence<T>.withSomeKoDocWithTags(vararg tags: KoDocTag) = filter {
    tags.any { tag -> it.koDoc?.hasTags(tag) ?: false }
}

/**
 * Filter declarations without KDoc tags.
 */
fun <T : KoDeclaration> Sequence<T>.withoutKoDocWithTags(vararg tags: KoDocTag) = filterNot { it.koDoc?.hasTags(*tags) ?: false }

/**
 * Print the declarations.
 */
fun <T : KoDeclaration> Sequence<T>.print(): Sequence<T> {
    forEach { println(it.toString()) }
    return this
}
