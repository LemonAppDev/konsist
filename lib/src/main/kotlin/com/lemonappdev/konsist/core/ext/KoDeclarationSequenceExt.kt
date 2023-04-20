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

fun Sequence<KoDeclaration>.withAnnotations(vararg annotations: String) = filter { koDeclaration ->
    annotations.all { koDeclaration.hasAnnotation(it) }
}

fun Sequence<KoDeclaration>.withSomeAnnotations(vararg annotations: String) = filter { koDeclaration ->
    annotations.any { koDeclaration.hasAnnotation(it) }
}

fun Sequence<KoDeclaration>.withoutAnnotations(vararg annotations: String) = filter { koDeclaration ->
    annotations.none { koDeclaration.hasAnnotation(it) }
}

fun Sequence<KoDeclaration>.withAnnotations(vararg annotations: KClass<*>) = filter { koDeclaration ->
    annotations.all { annotation ->
        annotation
            .simpleName
            ?.let { koDeclaration.hasAnnotation(it) } ?: false
    }
}

fun Sequence<KoDeclaration>.withSomeAnnotations(vararg annotations: KClass<*>) = filter { koDeclaration ->
    annotations.any { annotation ->
        annotation
            .simpleName
            ?.let { koDeclaration.hasAnnotation(it) } ?: false
    }
}

fun Sequence<KoDeclaration>.withoutAnnotations(vararg annotations: KClass<*>) = filter { koDeclaration ->
    annotations.none { annotation ->
        annotation
            .simpleName
            ?.let { koDeclaration.hasAnnotation(it) } ?: false
    }
}

fun Sequence<KoDeclaration>.withModifiers(vararg modifiers: KoModifier) = filter { it.hasModifiers(*modifiers) }

fun Sequence<KoDeclaration>.withSomeModifiers(vararg modifiers: KoModifier) = filter { koDeclaration ->
    modifiers.any { koDeclaration.hasModifiers(it) }
}

fun Sequence<KoDeclaration>.withoutModifiers(vararg modifiers: KoModifier) = filter { koDeclaration ->
    modifiers.none { koDeclaration.hasModifiers(it) }
}

fun Sequence<KoDeclaration>.withPackage(vararg packages: String) = filter { koDeclaration ->
    packages.any { koDeclaration.resideInPackage(it) }
}

fun Sequence<KoDeclaration>.withoutPackage(vararg packages: String) = filter { koDeclaration ->
    packages.all { koDeclaration.resideOutsidePackage(it) }
}

fun Sequence<KoDeclaration>.withPath(vararg paths: String) = filter { koDeclaration ->
    paths.any { koDeclaration.resideInPath(it) }
}

fun Sequence<KoDeclaration>.withoutPath(vararg paths: String) = filter { koDeclaration ->
    paths.all { koDeclaration.resideOutsidePath(it) }
}
