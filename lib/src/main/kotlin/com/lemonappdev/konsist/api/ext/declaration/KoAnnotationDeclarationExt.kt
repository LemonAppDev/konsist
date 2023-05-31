package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration

/**
 * Returns `true` if this annotation represents the type of [T].
 *
 * @return `true` if this annotation represents the type of [T], `false` otherwise.
 */
inline fun <reified T> KoAnnotationDeclaration.representsTypeOf(): Boolean = T::class.qualifiedName == fullyQualifiedName
