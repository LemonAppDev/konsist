package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider

/**
 * Determines whether the declaration has a valid KDoc with a PROPERTY tag for each property.
 * PROPERTY tag values must match the property names. A declaration without properties is valid if it has no PROPERTY tags.
 * Properties of nested declarations are not taken into account.
 *
 * @return `true` if the declaration has a valid KDoc with the PROPERTY tag, `false` otherwise.
 */
fun <T : KoPropertyProvider> T.hasValidKDocPropertyTags(): Boolean =
    if (properties(includeNested = false).isNotEmpty()) {
        (
            properties(includeNested = false).map { it.name }.sorted() ==
                (this as? KoKDocProvider)
                    ?.kDoc
                    ?.propertyTags
                    ?.map { it.value }
                    ?.sorted()
        )
    } else {
        (this as? KoKDocProvider)?.kDoc?.propertyTags?.isEmpty() ?: true
    }
