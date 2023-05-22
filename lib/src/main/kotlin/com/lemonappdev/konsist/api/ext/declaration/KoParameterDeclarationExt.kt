package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration

/**
 * Returns `true` if this parameter declaration represents the type of [T].
 *
 * @return `true` if this parameter declaration represents the type of [T], otherwise `false`.
 */
inline fun <reified T> KoParameterDeclaration.representsTypeOf(): Boolean = T::class.simpleName == type.name
