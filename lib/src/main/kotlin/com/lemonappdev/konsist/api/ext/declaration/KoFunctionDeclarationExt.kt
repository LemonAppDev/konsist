package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration

/**
 * Whether this function has receiver.
 *
 * @return `true` if the function has receiver with the specified KClass name, `false` otherwise.
 */
inline fun <reified T> KoFunctionDeclaration.hasReceiverOf(): Boolean = T::class.simpleName == receiver?.name
