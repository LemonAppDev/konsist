package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc tags.
 */
interface KoKDocTagProvider : KoBaseProvider {
    /**
     * List of tags.
     */
    val tags: List<KoKDocTagDeclaration>

    /**
     * The number of tags.
     */
    val numTags: Int

    /**
     * Determines whatever the declaration has the given tags.
     *
     * @param tags the tags to check.
     * @return `true` if the kDoc has all the specified tags (or any tags if [tags] is empty), `false` otherwise.
     */
    @Deprecated(
        """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `hasTag`, otherwise with `hasAllTags`.
            """,
    )
    fun hasTags(vararg tags: KoKDocTag): Boolean

    /**
     * Determines whatever the declaration has tags.
     *
     * @return `true` if the declaration has tag, `false` otherwise.
     */
    fun hasTags(): Boolean

    /**
     * Determines whether the declaration has at least one specified tag.
     *
     * @param tag the tag to check.
     * @param tags the tags to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasTag(
        tag: KoKDocTag,
        vararg tags: KoKDocTag,
    ): Boolean

    /**
     * Determines whether the declaration has at least one specified tag.
     *
     * @param tags the tags to check.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasTag( tags: Collection<KoKDocTag>): Boolean

    /**
     * Determines whether the declaration has all specified tags.
     *
     * @param tag the tag to check.
     * @param tags the tags to check.
     * @return `true` if there are declarations with all the specified tags, `false` otherwise.
     */
    fun hasAllTags(
        tag: KoKDocTag,
        vararg tags: KoKDocTag,
    ): Boolean

    /**
     * Determines whether the declaration has all specified tags.
     *
     * @param tags the tags to check.
     * @return `true` if there are declarations with all the specified tags, `false` otherwise.
     */
    fun hasAllTags(tags: Collection<KoKDocTag>): Boolean
}
