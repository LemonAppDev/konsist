package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoParametersProvider

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
