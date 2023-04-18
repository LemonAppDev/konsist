@file:Suppress("detekt.TooManyFunctions")

package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoDeclaration
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

fun Sequence<KoDeclaration>.withAnnotations(vararg annotation: String) = filter { koDeclaration ->
    annotation.all { koDeclaration.hasAnnotation(it) }
}

fun Sequence<KoDeclaration>.withoutAnnotations(vararg annotation: String) = filter { koDeclaration ->
    annotation.none { koDeclaration.hasAnnotation(it) }
}

fun Sequence<KoDeclaration>.withSomeAnnotations(vararg annotation: String) = filter { koDeclaration ->
    annotation.any { koDeclaration.hasAnnotation(it) }
}

fun Sequence<KoDeclaration>.withAnnotations(vararg annotation: KClass<*>) = filter { koDeclaration ->
    annotation.all { annotation ->
        annotation
            .simpleName
            ?.let { koDeclaration.hasAnnotation(it) } ?: false
    }
}

fun Sequence<KoDeclaration>.withoutAnnotations(vararg annotation: KClass<*>) = filter { koDeclaration ->
    annotation.none { annotation ->
        annotation
            .simpleName
            ?.let { koDeclaration.hasAnnotation(it) } ?: false
    }
}

fun Sequence<KoDeclaration>.withSomeAnnotations(vararg annotation: KClass<*>) = filter { koDeclaration ->
    annotation.any { annotation ->
        annotation
            .simpleName
            ?.let { koDeclaration.hasAnnotation(it) } ?: false
    }
}

fun Sequence<KoDeclaration>.withModifiers(vararg modifier: String) = filter { it.hasModifiers(*modifier) }

fun Sequence<KoDeclaration>.withoutModifiers(vararg modifier: String) = filter { koDeclaration ->
    modifier.none { koDeclaration.hasModifiers(it) }
}

fun Sequence<KoDeclaration>.withSomeModifiers(vararg modifier: String) = filter { koDeclaration ->
    modifier.any { koDeclaration.hasModifiers(it) }
}

fun Sequence<KoDeclaration>.withPackages(vararg packages: String) = filter { koDeclaration ->
    packages.all { koDeclaration.resideInPackages(it) }
}

fun Sequence<KoDeclaration>.withoutPackages(vararg packages: String) = filter { koDeclaration ->
    packages.all { koDeclaration.resideOutsidePackages(it) }
}

fun Sequence<KoDeclaration>.withSomePackages(vararg packages: String) = filter { koDeclaration ->
    packages.any { koDeclaration.resideInPackages(it) }
}

fun Sequence<KoDeclaration>.withPaths(vararg paths: String) = filter { koDeclaration ->
    paths.all { koDeclaration.resideInPath(it) }
}

fun Sequence<KoDeclaration>.withoutPaths(vararg paths: String) = filter { koDeclaration ->
    paths.all { koDeclaration.resideOutsidePath(it) }
}

fun Sequence<KoDeclaration>.withSomePaths(vararg paths: String) = filter { koDeclaration ->
    paths.any { koDeclaration.resideInPath(it) }
}
