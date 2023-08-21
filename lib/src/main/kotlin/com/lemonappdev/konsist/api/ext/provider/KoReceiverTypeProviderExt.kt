package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider

/**
 * Whether declaration has receiver type.
 *
 * @return `true` if the declaration has receiver type with the specified KClass name, `false` otherwise.
 */
inline fun <reified T> KoReceiverTypeProvider.hasReceiverTypeOf(): Boolean = T::class.simpleName == receiverType?.name

/**
 * Whether declaration has a valid KDoc with a RECEIVER tag.
 *
 * @return `true` if the declaration has a valid KDoc with the RECEIVER tag, `false` otherwise.
 */
fun <T> T.hasValidReceiverTypeKDoc(): Boolean
        where T : KoReceiverTypeProvider,
              T : KoKDocProvider = if (receiverType != null) {
    kDoc?.hasTags(KoKDocTag.RECEIVER) == true
} else {
    true
}
