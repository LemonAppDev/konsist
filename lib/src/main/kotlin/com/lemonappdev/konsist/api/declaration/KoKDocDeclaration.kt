package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.KoKDocTag

/**
 * Represents a Kotlin declaration.
 */
interface KoKDocDeclaration : KoPsiDeclaration {
    /**
     * Description of the declaration.
     */
    val description: String

    /**
     * List of tags.
     */
    val tags: List<KoKDocTagDeclaration>

    /**
     * List of '@param' tags.
     */
    val paramTags: List<KoValuedKDocTagDeclaration>

    /**
     * List of '@return' tags.
     */
    val returnTag: KoKDocTagDeclaration?

    /**
     * List of '@constructor' tags.
     */
    val constructorTag: KoKDocTagDeclaration?

    /**
     * List of '@receiver' tags.
     */
    val receiverTag: KoKDocTagDeclaration?

    /**
     * List of '@property' tags.
     */
    val propertyTags: List<KoValuedKDocTagDeclaration>

    /**
     * List of '@throws' tags.
     */
    val throwsTags: List<KoValuedKDocTagDeclaration>

    /**
     * List of '@exception' tags.
     */
    val exceptionTags: List<KoValuedKDocTagDeclaration>

    /**
     * List of '@sample' tags.
     */
    val sampleTags: List<KoValuedKDocTagDeclaration>

    /**
     * List of '@see' tags.
     */
    val seeTags: List<KoValuedKDocTagDeclaration>

    /**
     * List of '@author' tags.
     */
    val authorTags: List<KoKDocTagDeclaration>

    /**
     * List of '@since' tags.
     */
    val sinceTag: KoKDocTagDeclaration?

    /**
     * List of '@suppress' tags.
     */
    val suppressTag: KoKDocTagDeclaration?

    /**
     * List of '@version' tags.
     */
    val versionTag: KoKDocTagDeclaration?

    /**
     * List of '@propertySetter' tags.
     */
    val propertySetterTag: KoKDocTagDeclaration?

    /**
     * List of '@propertyGetter' tags.
     */
    val propertyGetterTag: KoKDocTagDeclaration?

    /**
     * Whether the declaration has the given tags.
     *
     * @param tags the tags to check.
     * @return `true` if the declaration has all the specified tags, `false` otherwise.
     */
    fun hasTags(vararg tags: KoKDocTag): Boolean
}
