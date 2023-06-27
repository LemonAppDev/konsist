package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration

/**
 * Whether this function has receiver type.
 *
 * @return `true` if the function has receiver type with the specified KClass name, `false` otherwise.
 */
inline fun <reified T> KoFunctionDeclaration.hasReceiverTypeOf(): Boolean = T::class.simpleName == receiverType?.name
