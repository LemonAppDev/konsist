@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.const.KoModifier
import com.lemonappdev.konsist.core.declaration.KoDeclaration
import kotlin.reflect.KClass

fun Sequence<KoDeclaration>.withPublicModifier() = filter { it.hasPublicModifier() }

fun Sequence<KoDeclaration>.withoutPublicModifier() = filterNot { it.hasPublicModifier() }

fun Sequence<KoDeclaration>.withPublicOrDefaultModifier() = filter { it.isPublicOrDefault() }

fun Sequence<KoDeclaration>.withoutPublicOrDefaultModifier() = filterNot { it.isPublicOrDefault() }

fun Sequence<KoDeclaration>.withPrivateModifier() = filter { it.hasPrivateModifier() }

fun Sequence<KoDeclaration>.withoutPrivateModifier() = filterNot { it.hasPrivateModifier() }

fun Sequence<KoDeclaration>.withProtectedModifier() = filter { it.hasProtectedModifier() }

fun Sequence<KoDeclaration>.withoutProtectedModifier() = filterNot { it.hasProtectedModifier() }

fun Sequence<KoDeclaration>.withInternalModifier() = filter { it.hasInternalModifier() }

fun Sequence<KoDeclaration>.withoutInternalModifier() = filterNot { it.hasInternalModifier() }

fun Sequence<KoDeclaration>.withTopLevel() = filter { it.isTopLevel() }

fun Sequence<KoDeclaration>.withoutTopLevel() = filterNot { it.isTopLevel() }

fun Sequence<KoDeclaration>.withAnnotations(vararg annotations: String) = filter {
    annotations.all { annotation -> it.hasAnnotation(annotation) }
}

fun Sequence<KoDeclaration>.withSomeAnnotations(vararg annotations: String) = filter {
    annotations.any { annotation -> it.hasAnnotation(annotation) }
}

fun Sequence<KoDeclaration>.withoutAnnotations(vararg annotations: String) = filter {
    annotations.none { annotation -> it.hasAnnotation(annotation) }
}

fun Sequence<KoDeclaration>.withAnnotations(vararg annotations: KClass<*>) = filter {
    annotations.all { annotation ->
        annotation
            .simpleName
            ?.let { name -> it.hasAnnotation(name) } ?: false
    }
}

fun Sequence<KoDeclaration>.withSomeAnnotations(vararg annotations: KClass<*>) = filter {
    annotations.any { annotation ->
        annotation
            .simpleName
            ?.let { name -> it.hasAnnotation(name) } ?: false
    }
}

fun Sequence<KoDeclaration>.withoutAnnotations(vararg annotations: KClass<*>) = filter {
    annotations.none { annotation ->
        annotation
            .simpleName
            ?.let { name -> it.hasAnnotation(name) } ?: false
    }
}

fun Sequence<KoDeclaration>.withModifiers(vararg modifiers: KoModifier) = filter { it.hasModifiers(*modifiers) }

fun Sequence<KoDeclaration>.withSomeModifiers(vararg modifiers: KoModifier) = filter {
    modifiers.any { modifier -> it.hasModifiers(modifier) }
}

fun Sequence<KoDeclaration>.withoutModifiers(vararg modifiers: KoModifier) = filter {
    modifiers.none { modifier -> it.hasModifiers(modifier) }
}

fun Sequence<KoDeclaration>.withPackage(vararg packages: String) = filter {
    packages.any { packagee -> it.resideInPackage(packagee) }
}

fun Sequence<KoDeclaration>.withoutPackage(vararg packages: String) = filter {
    packages.all { packagee -> it.resideOutsidePackage(packagee) }
}
