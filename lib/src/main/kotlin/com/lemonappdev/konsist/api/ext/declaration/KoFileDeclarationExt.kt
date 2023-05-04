package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration

/**
 * Returns `true` if this file declaration has an annotation of [T].
 */
inline fun <reified T> KoFileDeclaration.hasAnnotationOf(): Boolean {
    val qualifiedName = T::class.qualifiedName ?: return false

    return annotations.any { it.fullyQualifiedName.contains(qualifiedName) }
}
