package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.provider.KoKDocProvider

/**
 * Sequence containing declarations with KDoc.
 *
 * @return A sequence containing declarations with KDoc.
 */
fun <T : KoKDocProvider> Sequence<T>.withKDoc(): Sequence<T> = filter { it.hasKDoc() }

/**
 * Sequence containing declarations without KDoc.
 *
 * @return A sequence containing declarations without KDoc.
 */
fun <T : KoKDocProvider> Sequence<T>.withoutKDoc(): Sequence<T> = filterNot { it.hasKDoc() }

/**
 * Sequence containing declarations with KDoc with any tag.
 *
 * @return A sequence containing declarations with KDoc with any tag.
 */
fun <T : KoKDocProvider> Sequence<T>.withKDocWithTags(): Sequence<T> =
    filter { it.kDoc?.tags?.isNotEmpty() ?: false }

/**
 * Sequence containing declarations with KDoc with all specified tags.
 *
 * @param tag The KDoc tag to check for in the declarations' KDoc.
 * @param tags The KDoc tags to check for in the declarations' KDoc.
 * @return A sequence containing declarations with specified KDoc tags.
 */
fun <T : KoKDocProvider> Sequence<T>.withKDocWithAllTags(tag: KoKDocTag, vararg tags: KoKDocTag): Sequence<T> =
    filter { it.kDoc?.hasTags(tag, *tags) ?: false }

/**
 * Sequence containing declarations with at least one of the specified KDoc tags.
 *
 * @param tag The KDoc tag to check for in the declarations' KDoc.
 * @param tags The KDoc tags to check for in the declarations' KDoc.
 * @return A sequence containing declarations with at least one of the specified KDoc tags.
 */
fun <T : KoKDocProvider> Sequence<T>.withKDocWithSomeTags(tag: KoKDocTag, vararg tags: KoKDocTag): Sequence<T> = filter {
    it.kDoc?.hasTags(tag) ?: false || tags.any { tag -> it.kDoc?.hasTags(tag) ?: false }
}

/**
 * Sequence containing declarations without KDoc with any tag.
 *
 * @return A sequence containing declarations without KDoc with any tag.
 */
fun <T : KoKDocProvider> Sequence<T>.withoutKDocWithTags(): Sequence<T> =
    filterNot { it.kDoc?.tags?.isNotEmpty() ?: false }

/**
 * Sequence containing declarations without KDoc with all specified tags.
 *
 * @param tag The KDoc tag to check for absence in the declarations' KDoc.
 * @param tags The KDoc tags to check for absence in the declarations' KDoc.
 * @return A sequence containing declarations without all specified KDoc tags.
 *
 */
fun <T : KoKDocProvider> Sequence<T>.withoutKDocWithAllTags(tag: KoKDocTag, vararg tags: KoKDocTag): Sequence<T> =
    filterNot { it.kDoc?.hasTags(tag, *tags) ?: false }

/**
 * Sequence containing declarations without at least one of the specified KDoc tags.
 *
 * @param tag The KDoc tag to check for absence in the declarations' KDoc.
 * @param tags The KDoc tags to check for absence in the declarations' KDoc.
 * @return A sequence containing declarations without at least one of the specified KDoc tags.
 */
fun <T : KoKDocProvider> Sequence<T>.withoutKDocWithSomeTags(tag: KoKDocTag, vararg tags: KoKDocTag): Sequence<T> = filter {
    it.kDoc?.hasTags(tag) == false && if (tags.isNotEmpty()) {
        tags.any { tag -> it.kDoc?.hasTags(tag) == false }
    } else {
        true
    }
}
