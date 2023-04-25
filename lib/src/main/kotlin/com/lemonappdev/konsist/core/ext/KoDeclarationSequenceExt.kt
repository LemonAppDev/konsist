@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.const.KoModifier
import com.lemonappdev.konsist.core.declaration.KoDeclaration
import kotlin.reflect.KClass

fun <T : KoDeclaration> Sequence<T>.withPublicModifier() = filter { it.hasPublicModifier() }

fun <T : KoDeclaration> Sequence<T>.withoutPublicModifier() = filterNot { it.hasPublicModifier() }

fun <T : KoDeclaration> Sequence<T>.withPublicOrDefaultModifier() = filter { it.isPublicOrDefault() }

fun <T : KoDeclaration> Sequence<T>.withoutPublicOrDefaultModifier() = filterNot { it.isPublicOrDefault() }

fun <T : KoDeclaration> Sequence<T>.withPrivateModifier() = filter { it.hasPrivateModifier() }

fun <T : KoDeclaration> Sequence<T>.withoutPrivateModifier() = filterNot { it.hasPrivateModifier() }

fun <T : KoDeclaration> Sequence<T>.withProtectedModifier() = filter { it.hasProtectedModifier() }

fun <T : KoDeclaration> Sequence<T>.withoutProtectedModifier() = filterNot { it.hasProtectedModifier() }

fun <T : KoDeclaration> Sequence<T>.withInternalModifier() = filter { it.hasInternalModifier() }

fun <T : KoDeclaration> Sequence<T>.withoutInternalModifier() = filterNot { it.hasInternalModifier() }

fun <T : KoDeclaration> Sequence<T>.withTopLevel() = filter { it.isTopLevel() }

fun <T : KoDeclaration> Sequence<T>.withoutTopLevel() = filterNot { it.isTopLevel() }

fun <T : KoDeclaration> Sequence<T>.withAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> it.hasAnnotations()
        else -> it.hasAnnotations(*annotations)
    }
}

fun <T : KoDeclaration> Sequence<T>.withSomeAnnotations(vararg annotations: String) = filter {
    annotations.any { annotation -> it.hasAnnotations(annotation) }
}

fun <T : KoDeclaration> Sequence<T>.withoutAnnotations(vararg annotations: String) = filter {
    when {
        annotations.isEmpty() -> !it.hasAnnotations()
        else -> !it.hasAnnotations(*annotations)
    }
}

fun <T : KoDeclaration> Sequence<T>.withAnnotationsOf(vararg annotations: KClass<*>) = filter { it.hasAnnotationsOf(*annotations) }

fun <T : KoDeclaration> Sequence<T>.withSomeAnnotationsOf(vararg annotations: KClass<*>) = filter {
    annotations.any { annotation -> it.hasAnnotationsOf(annotation) }
}

fun <T : KoDeclaration> Sequence<T>.withoutAnnotationsOf(vararg annotations: KClass<*>) = filter { !it.hasAnnotationsOf(*annotations) }

inline fun <reified T> Sequence<KoDeclaration>.withAnnotationOf() = filter { it.hasAnnotationOf<T>() }

inline fun <reified T> Sequence<KoDeclaration>.withoutAnnotationOf() = filterNot { it.hasAnnotationOf<T>() }

fun <T : KoDeclaration> Sequence<T>.withModifiers(vararg modifiers: KoModifier) = filter {
    when {
        modifiers.isEmpty() -> it.hasModifiers()
        else -> it.hasModifiers(*modifiers)
    }
}

fun <T : KoDeclaration> Sequence<T>.withSomeModifiers(vararg modifiers: KoModifier) = filter {
    modifiers.any { modifier -> it.hasModifiers(modifier) }
}

fun <T : KoDeclaration> Sequence<T>.withoutModifiers(vararg modifiers: KoModifier) = filter {
    when {
        modifiers.isEmpty() -> !it.hasModifiers()
        else -> !it.hasModifiers(*modifiers)
    }
}

fun <T : KoDeclaration> Sequence<T>.withPackage(vararg packages: String) = filter {
    packages.any { packagee -> it.resideInPackage(packagee) }
}

fun <T : KoDeclaration> Sequence<T>.withoutPackage(vararg packages: String) = filter {
    packages.all { packagee -> it.resideOutsidePackage(packagee) }
}

fun <T : KoDeclaration> Sequence<T>.withKoDoc() = filter { it.hasKoDoc() }

fun <T : KoDeclaration> Sequence<T>.withoutKoDoc() = filterNot { it.hasKoDoc() }
