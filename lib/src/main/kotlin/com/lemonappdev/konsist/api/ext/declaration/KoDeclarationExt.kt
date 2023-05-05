package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoDeclaration

/**
 * Returns `true` if this declaration represents the type of [T].
 */
inline fun <reified T> KoDeclaration.hasAnnotationOf(): Boolean {
    /**
     * Returns qualified name of [T].
     */
    val qualifiedName = T::class.qualifiedName ?: return false

    /**
     * Returns annotations of this declaration.
     */
    return annotations.any { it.fullyQualifiedName.contains(qualifiedName) }
}
