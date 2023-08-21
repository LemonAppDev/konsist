package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoPrimaryConstructorProvider
import com.lemonappdev.konsist.api.provider.KoSecondaryConstructorsProvider

/**
 * Whether declaration has a valid KDoc with a PARAM tag.
 *
 * @return `true` if the declaration has a valid KDoc with the PARAM tag, `false` otherwise.
 */
fun <T> T.hasValidConstructorParameterKDoc(): Boolean
    where T : KoConstructorProvider,
          T : KoPrimaryConstructorProvider,
          T : KoSecondaryConstructorsProvider,
          T : KoKDocProvider = if (constructors.isNotEmpty()) {
    val parameters = primaryConstructor?.parameters
    parameters?.count() == kDoc?.paramTags?.count() &&
        parameters?.map { it.name } == kDoc?.paramTags?.map { it.value } &&
        secondaryConstructors.all { it.hasValidParameterKDoc() }
} else {
    true
}
