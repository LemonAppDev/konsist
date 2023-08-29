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
fun <T : KoConstructorProvider> T.hasValidConstructorParameterKDoc(): Boolean = if (constructors.isNotEmpty()) {
    val parameters = (this as? KoPrimaryConstructorProvider)?.primaryConstructor?.parameters
    parameters?.count() == (this as? KoKDocProvider)?.kDoc?.paramTags?.count() &&
        parameters?.map { it.name } == (this as? KoKDocProvider)?.kDoc?.paramTags?.map { it.value } &&
        (this as? KoSecondaryConstructorsProvider)?.secondaryConstructors?.all { it.hasValidParameterKDoc() } ?: false
} else {
    true
}
