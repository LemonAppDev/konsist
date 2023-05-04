package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoDeclaration

inline fun <reified T> KoDeclaration.hasAnnotationOf(): Boolean {
    val qualifiedName = T::class.qualifiedName ?: return false

    return annotations.any { it.fullyQualifiedName.contains(qualifiedName) }
}
