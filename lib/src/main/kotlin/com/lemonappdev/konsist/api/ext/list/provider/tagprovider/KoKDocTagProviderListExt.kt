package com.lemonappdev.konsist.api.ext.list.provider.tagprovider

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.tag.KoKDocTagProvider

/**
 * List containing tags.
 */
val <T : KoKDocTagProvider> List<T>.tags: List<KoKDocTagDeclaration>
    get() = flatMap { it.tags }

/**
 * List containing declarations with any specified tag.
 *
 * @return A list containing declarations with any tag.
 */
fun <T : KoKDocTagProvider> List<T>.withTags(): List<T> = filter { it.hasTags() }

/**
 * List containing declarations with no tag.
 *
 * @return A list containing declarations with no tag.
 */
fun <T : KoKDocTagProvider> List<T>.withoutTags(): List<T> = filterNot { it.hasTags() }

/**
 * List containing declarations with all the specified tags.
 *
 * @param tag The tag to include.
 * @param tags The tags to include.
 * @return A list containing declarations with all the specified tags.
 */
fun <T : KoKDocTagProvider> List<T>.withTag(
    tag: KoKDocTag,
    vararg tags: KoKDocTag,
): List<T> = withTag(listOf(tag, *tags))

/**
 * List containing declarations with all the specified tags.
 *
 * @param tags The tags to include.
 * @return A list containing declarations with all the specified tags.
 */
fun <T : KoKDocTagProvider> List<T>.withTag(tags: Collection<KoKDocTag>): List<T> =
    filter {
        when {
            tags.isEmpty() -> it.hasTags()
            else -> it.hasTag(tags)
        }
    }

/**
 * List containing declarations without all specified tags.
 *
 * @param tag The tag to exclude.
 * @param tags The tags to exclude.
 * @return A list containing declarations without all the specified tags.
 */
fun <T : KoKDocTagProvider> List<T>.withoutTag(
    tag: KoKDocTag,
    vararg tags: KoKDocTag,
): List<T> = withoutTag(listOf(tag, *tags))

/**
 * List containing declarations without all specified tags.
 *
 * @param tags The tags to exclude.
 * @return A list containing declarations without all the specified tags.
 */
fun <T : KoKDocTagProvider> List<T>.withoutTag(tags: Collection<KoKDocTag>): List<T> =
    filterNot {
        when {
            tags.isEmpty() -> it.hasTags()
            else -> it.hasTag(tags)
        }
    }

/**
 * List containing declarations with all the specified tags.
 *
 * @param tag The tag to include.
 * @param tags The tags to include.
 * @return A list containing declarations with all the specified tags.
 */
fun <T : KoKDocTagProvider> List<T>.withAllTags(
    tag: KoKDocTag,
    vararg tags: KoKDocTag,
): List<T> = withAllTags(listOf(tag, *tags))

/**
 * List containing declarations with all the specified tags.
 *
 * @param tags The tags to include.
 * @return A list containing declarations with all the specified tags.
 */
fun <T : KoKDocTagProvider> List<T>.withAllTags(tags: Collection<KoKDocTag>): List<T> =
    filter {
        when {
            tags.isEmpty() -> it.hasTags()
            else -> it.hasAllTags(tags)
        }
    }

/**
 * List containing declarations without all specified tags.
 *
 * @param tag The tag to exclude.
 * @param tags The tags to exclude.
 * @return A list containing declarations without all the specified tags.
 */
fun <T : KoKDocTagProvider> List<T>.withoutAllTags(
    tag: KoKDocTag,
    vararg tags: KoKDocTag,
): List<T> = withoutAllTags(listOf(tag, *tags))

/**
 * List containing declarations without all specified tags.
 *
 * @param tags The tags to exclude.
 * @return A list containing declarations without all the specified tags.
 */
fun <T : KoKDocTagProvider> List<T>.withoutAllTags(tags: Collection<KoKDocTag>): List<T> =
    filterNot {
        when {
            tags.isEmpty() -> it.hasTags()
            else -> it.hasAllTags(tags)
        }
    }