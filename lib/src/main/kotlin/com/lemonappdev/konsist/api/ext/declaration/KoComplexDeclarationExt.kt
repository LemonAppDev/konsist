package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration

/**
 * Returns `true` if this complex declaration represents the type of [T].
 *
 * @return `true` if this complex declaration represents the type of [T], `false` otherwise.
 */
inline fun <reified T> KoComplexDeclaration.representsTypeOf(): Boolean = T::class.qualifiedName == fullyQualifiedName
