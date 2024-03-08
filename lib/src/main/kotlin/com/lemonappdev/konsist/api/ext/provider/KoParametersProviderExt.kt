package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoParametersProvider

/**
 * Determines whatever declaration has a valid KDoc with a PARAM tag.
 *
 * @return `true` if the declaration has a valid KDoc with the PARAM tag, `false` otherwise.
 */
fun <T : KoParametersProvider> T.hasValidKDocParamTags(): Boolean =
    if (parameters.isNotEmpty()) {
        val kDoc =
            when (this) {
                is KoPrimaryConstructorDeclaration -> (containingDeclaration as? KoKDocProvider)?.kDoc
                is KoKDocProvider -> kDoc
                else -> null
            }

        parameters.map { it.name }.sorted() == kDoc?.paramTags?.map { it.value }?.sorted()
    } else {
        (this as? KoKDocProvider)?.kDoc?.paramTags?.isEmpty() ?: true
    }
