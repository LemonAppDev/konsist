package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider

/**
 * Determines whatever declaration has a valid KDoc with a PROPERTY tag.
 *
 * @return `true` if the declaration has a valid KDoc with the PROPERTY tag, `false` otherwise.
 */
fun <T : KoPropertyProvider> T.hasValidKDocPropertyTags(): Boolean = if (properties().isNotEmpty()) {
    (properties().map { it.name }.sorted() == (this as? KoKDocProvider)?.kDoc?.propertyTags?.map { it.value }?.sorted())
} else {
    (this as? KoKDocProvider)?.kDoc?.propertyTags?.isEmpty() ?: true
}
