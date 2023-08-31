package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider

/**
 * Whether declaration has a valid KDoc with a PROPERTY tag.
 *
 * @return `true` if the declaration has a valid KDoc with the PROPERTY tag, `false` otherwise.
 */
fun <T : KoPropertyProvider> T.hasValidKDocPropertyTags(): Boolean = if (properties().isNotEmpty()) {
    (properties().count() == (this as? KoKDocProvider)?.kDoc?.propertyTags?.count()) &&
        (properties().map { it.name } == kDoc?.propertyTags?.map { it.value })
} else {
    true
}
