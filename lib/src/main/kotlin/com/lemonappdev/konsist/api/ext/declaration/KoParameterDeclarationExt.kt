package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration

/**
 * Returns `true` if this parameter declaration represents the type of [T].
 */
inline fun <reified T> KoParameterDeclaration.representsTypeOf() = T::class.simpleName == type.name
