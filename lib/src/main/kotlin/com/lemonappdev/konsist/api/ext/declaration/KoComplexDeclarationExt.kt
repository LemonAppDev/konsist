package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoComplexDeclaration

/**
 * Returns `true` if this complex declaration represents the type of [T].
 */
inline fun <reified T> KoComplexDeclaration.representsTypeOf() = T::class.qualifiedName == fullyQualifiedName
