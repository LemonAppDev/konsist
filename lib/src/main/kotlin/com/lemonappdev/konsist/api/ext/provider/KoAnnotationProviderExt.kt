package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider

/**
 * Returns `true` if declaration represents the type of [T].
 *
 * @return `true` if declaration represents the type of [T], `false` otherwise.
 */
inline fun <reified T> KoAnnotationProvider.hasAnnotationOf(): Boolean {
    /**
     * Returns qualified name of [T].
     */
    val qualifiedName = T::class.qualifiedName ?: return false

    /**
     * Returns annotations of this declaration.
     */

    return annotations.any {
        if (qualifiedName.startsWith("kotlin.")) {
            it.name == T::class.simpleName
        } else {
            it.fullyQualifiedName.contains(qualifiedName)
        }
    }
}
