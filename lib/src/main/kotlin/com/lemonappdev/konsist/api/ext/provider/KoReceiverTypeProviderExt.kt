package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider

/**
 * Whether declaration has receiver type.
 *
 * @return `true` if the declaration has receiver type with the specified KClass name, `false` otherwise.
 */
inline fun <reified T> KoReceiverTypeProvider.hasReceiverTypeOf(): Boolean = hasReceiverTypeOf(T::class)

/**
 * Whether declaration has a valid KDoc with a RECEIVER tag.
 *
 * @return `true` if the declaration has a valid KDoc with the RECEIVER tag, `false` otherwise.
 */
fun <T : KoReceiverTypeProvider> T.hasValidKDocReceiverTag(): Boolean = if (receiverType != null) {
    (this as? KoKDocProvider)?.kDoc?.hasTags(KoKDocTag.RECEIVER) == true
} else {
    (this as? KoKDocProvider)?.kDoc?.receiverTag == null
}
