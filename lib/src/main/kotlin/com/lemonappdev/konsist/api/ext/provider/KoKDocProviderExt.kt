package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.api.provider.KoReturnTypeProvider

/**
 * Whether declaration has a valid KDoc with a RETURN tag.
 *
 * @return `true` if the declaration has a valid KDoc with the RETURN tag, `false` otherwise.
 */
fun <T> T.hasValidReturnTypeKDoc(): Boolean
    where T : KoReturnTypeProvider,
          T : KoKDocProvider = if (returnType != null && returnType?.name != "Unit") {
    kDoc?.hasTags(KoKDocTag.RETURN) == true
} else {
    true
}

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

/**
 * Whether declaration has a valid KDoc with a PARAM tag.
 *
 * @return `true` if the declaration has a valid KDoc with the PARAM tag, `false` otherwise.
 */
fun <T> T.hasValidParameterKDoc(): Boolean
    where T : KoParametersProvider,
          T : KoKDocProvider = if (parameters.isNotEmpty()) {
    parameters.count() == kDoc?.paramTags?.count() && parameters.map { it.name } == kDoc?.paramTags?.map { it.value }
} else {
    true
}
