package com.lemonappdev.konsist.api.ext.declaration

import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider

/**
 * Whether this declaration has receiver type.
 *
 * @return `true` if the declaration has receiver type with the specified KClass name, `false` otherwise.
 */
inline fun <reified T> KoReceiverTypeProvider.hasReceiverTypeOf(): Boolean = T::class.simpleName == receiverType?.name
