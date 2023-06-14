package com.lemonappdev.konsist.api.ext.container

import com.lemonappdev.konsist.api.container.KoFile

/**
 * Returns `true` if this file declaration has an annotation of [T].
 *
 * @return `true` if this file declaration has an annotation of [T], `false` otherwise.
 */
inline fun <reified T> KoFile.hasAnnotationOf(): Boolean {
    val qualifiedName = T::class.qualifiedName ?: return false

    return annotations.any {
        if (qualifiedName.startsWith("kotlin.")) {
            it.name == T::class.simpleName
        } else {
            it.fullyQualifiedName.contains(qualifiedName)
        }
    }
}
