@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoTag
import com.lemonappdev.konsist.core.declaration.KoDeclarationImpl
import kotlin.reflect.KClass

fun <T : KoDeclarationImpl> Sequence<T>.withPublicModifier() = filter { it.hasPublicModifier() }

fun <T : KoDeclarationImpl> Sequence<T>.withoutPublicModifier() = filterNot { it.hasPublicModifier() }

fun <T : KoDeclarationImpl> Sequence<T>.withPublicOrDefaultModifier() = filter { it.isPublicOrDefault() }

fun <T : KoDeclarationImpl> Sequence<T>.withoutPublicOrDefaultModifier() = filterNot { it.isPublicOrDefault() }

fun <T : KoDeclarationImpl> Sequence<T>.withPrivateModifier() = filter { it.hasPrivateModifier() }

fun <T : KoDeclarationImpl> Sequence<T>.withoutPrivateModifier() = filterNot { it.hasPrivateModifier() }

fun <T : KoDeclarationImpl> Sequence<T>.withProtectedModifier() = filter { it.hasProtectedModifier() }

fun <T : KoDeclarationImpl> Sequence<T>.withoutProtectedModifier() = filterNot { it.hasProtectedModifier() }

fun <T : KoDeclarationImpl> Sequence<T>.withInternalModifier() = filter { it.hasInternalModifier() }

fun <T : KoDeclarationImpl> Sequence<T>.withoutInternalModifier() = filterNot { it.hasInternalModifier() }

fun <T : KoDeclarationImpl> Sequence<T>.withTopLevel() = filter { it.isTopLevel() }

fun <T : KoDeclarationImpl> Sequence<T>.withoutTopLevel() = filterNot { it.isTopLevel() }

fun <T : KoDeclarationImpl> Sequence<T>.withAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

fun <T : KoDeclarationImpl> Sequence<T>.withSomeAnnotations(vararg annotations: String) = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
}

fun <T : KoDeclarationImpl> Sequence<T>.withoutAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

fun <T : KoDeclarationImpl> Sequence<T>.withAnnotationsOf(vararg annotations: KClass<*>) = filter { it.hasAnnotationsOf(*annotations) }

fun <T : KoDeclarationImpl> Sequence<T>.withSomeAnnotationsOf(vararg annotations: KClass<*>) = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

fun <T : KoDeclarationImpl> Sequence<T>.withoutAnnotationsOf(vararg annotations: KClass<*>) = filter { !it.hasAnnotationsOf(*annotations) }

inline fun <reified T> Sequence<KoDeclarationImpl>.withAnnotationOf() = filter { it.hasAnnotationOf<T>() }

inline fun <reified T> Sequence<KoDeclarationImpl>.withoutAnnotationOf() = filterNot { it.hasAnnotationOf<T>() }

fun <T : KoDeclarationImpl> Sequence<T>.withModifiers(vararg modifiers: KoModifier) = filter {
    when {
        modifiers.isEmpty() -> it.hasModifiers()
        else -> it.hasModifiers(*modifiers)
    }
}

fun <T : KoDeclarationImpl> Sequence<T>.withSomeModifiers(vararg modifiers: KoModifier) = filter {
    modifiers.any { modifier -> it.hasModifiers(modifier) }
}

fun <T : KoDeclarationImpl> Sequence<T>.withoutModifiers(vararg modifiers: KoModifier) = filter {
    when {
        modifiers.isEmpty() -> !it.hasModifiers()
        else -> !it.hasModifiers(*modifiers)
    }
}

fun <T : KoDeclarationImpl> Sequence<T>.withPackage(vararg packages: String) = filter {
    packages.any { packagee -> it.resideInPackage(packagee) }
}

fun <T : KoDeclarationImpl> Sequence<T>.withoutPackage(vararg packages: String) = filter {
    packages.all { packagee -> it.resideOutsidePackage(packagee) }
}

fun <T : KoDeclarationImpl> Sequence<T>.withKoDoc() = filter { it.hasKoDoc() }

fun <T : KoDeclarationImpl> Sequence<T>.withoutKoDoc() = filterNot { it.hasKoDoc() }

fun <T : KoDeclarationImpl> Sequence<T>.withKoDocWithTags(vararg tags: KoTag) = filter { it.koDoc?.hasTags(*tags) ?: false }

fun <T : KoDeclarationImpl> Sequence<T>.withSomeKoDocWithTags(vararg tags: KoTag) = filter {
    tags.any { tag -> it.koDoc?.hasTags(tag) ?: false }
}

fun <T : KoDeclarationImpl> Sequence<T>.withoutKoDocWithTags(vararg tags: KoTag) = filterNot { it.koDoc?.hasTags(*tags) ?: false }

fun <T : KoDeclarationImpl> Sequence<T>.print(): Sequence<T> {
    forEach { println(it.toString()) }
    return this
}
