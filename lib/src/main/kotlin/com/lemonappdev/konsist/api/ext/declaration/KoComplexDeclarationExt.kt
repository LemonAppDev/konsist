package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration

/**
 * Returns `true` if this complex declaration represents the type of [T].
 *
 * @return `true` if this complex declaration represents the type of [T], otherwise `false`.
 */
inline fun <reified T> KoComplexDeclaration.representsTypeOf(): Boolean = T::class.qualifiedName == fullyQualifiedName
