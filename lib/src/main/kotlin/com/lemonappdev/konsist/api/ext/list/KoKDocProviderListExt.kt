package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocDeclaration
import com.lemonappdev.konsist.api.provider.KoKDocProvider

/**
 * List containing KDoc declarations.
 */
val <T : KoKDocProvider> List<T>.kDocs: List<KoKDocDeclaration>
    get() = mapNotNull { it.kDoc }

/**
 * List containing elements with KDoc.
 *
 * @return A list containing elements with KDoc.
 */
fun <T : KoKDocProvider> List<T>.withKDoc(): List<T> = filter { it.hasKDoc }

/**
 * List containing elements without KDoc.
 *
 * @return A list containing elements without KDoc.
 */
fun <T : KoKDocProvider> List<T>.withoutKDoc(): List<T> = filterNot { it.hasKDoc }

/**
 * List containing elements with KDoc with any tag.
 *
 * @return A list containing elements with KDoc with any tag.
 */
fun <T : KoKDocProvider> List<T>.withKDocWithTags(): List<T> =
    filter { it.kDoc?.hasTags() ?: false }

/**
 * List containing elements with KDoc with all specified tags.
 *
 * @param tag The KDoc tag to check for in the declarations' KDoc.
 * @param tags The KDoc tags to check for in the declarations' KDoc.
 * @return A list containing elements with specified KDoc tags.
 */
fun <T : KoKDocProvider> List<T>.withKDocWithAllTags(tag: KoKDocTag, vararg tags: KoKDocTag): List<T> =
    filter { it.kDoc?.hasTags(tag, *tags) ?: false }

/**
 * List containing elements with at least one of the specified KDoc tags.
 *
 * @param tag The KDoc tag to check for in the declarations' KDoc.
 * @param tags The KDoc tags to check for in the declarations' KDoc.
 * @return A list containing elements with at least one of the specified KDoc tags.
 */
fun <T : KoKDocProvider> List<T>.withKDocWithSomeTags(tag: KoKDocTag, vararg tags: KoKDocTag): List<T> = filter {
    it.kDoc?.hasTags(tag) ?: false || tags.any { tag -> it.kDoc?.hasTags(tag) ?: false }
}

/**
 * List containing elements without KDoc with any tag.
 *
 * @return A list containing elements without KDoc with any tag.
 */
fun <T : KoKDocProvider> List<T>.withoutKDocWithTags(): List<T> =
    filterNot { it.kDoc?.hasTags() ?: false }

/**
 * List containing elements without KDoc with all specified tags.
 *
 * @param tag The KDoc tag to check for absence in the declarations' KDoc.
 * @param tags The KDoc tags to check for absence in the declarations' KDoc.
 * @return A list containing elements without all specified KDoc tags.
 *
 */
fun <T : KoKDocProvider> List<T>.withoutKDocWithAllTags(tag: KoKDocTag, vararg tags: KoKDocTag): List<T> =
    filterNot { it.kDoc?.hasTags(tag, *tags) ?: false }

/**
 * List containing elements without at least one of the specified KDoc tags.
 *
 * @param tag The KDoc tag to check for absence in the declarations' KDoc.
 * @param tags The KDoc tags to check for absence in the declarations' KDoc.
 * @return A list containing elements without at least one of the specified KDoc tags.
 */
fun <T : KoKDocProvider> List<T>.withoutKDocWithSomeTags(tag: KoKDocTag, vararg tags: KoKDocTag): List<T> = filter {
    it.kDoc?.hasTags(tag) == false && if (tags.isNotEmpty()) {
        tags.any { tag -> it.kDoc?.hasTags(tag) == false }
    } else {
        true
    }
}
