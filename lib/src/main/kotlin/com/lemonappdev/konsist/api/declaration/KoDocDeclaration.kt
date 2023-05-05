package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.KoDocTag

/**
 * Represents a Kotlin declaration.
 */
interface KoDocDeclaration : KoPsiDeclaration {
    /**
     * Description of the declaration.
     */
    val description: String

    /**
     * List of tags.
     */
    val tags: List<KoDocTagDeclaration>

    /**
     * List of '@param' tags.
     */
    val paramTags: List<KoValuedDocTagDeclaration>

    /**
     * List of '@return' tags.
     */
    val returnTag: KoDocTagDeclaration?

    /**
     * List of '@constructor' tags.
     */
    val constructorTag: KoDocTagDeclaration?

    /**
     * List of '@receiver' tags.
     */
    val receiverTag: KoDocTagDeclaration?

    /**
     * List of '@property' tags.
     */
    val propertyTags: List<KoValuedDocTagDeclaration>

    /**
     * List of '@throws' tags.
     */
    val throwsTags: List<KoValuedDocTagDeclaration>

    /**
     * List of '@exception' tags.
     */
    val exceptionTags: List<KoValuedDocTagDeclaration>

    /**
     * List of '@sample' tags.
     */
    val sampleTags: List<KoValuedDocTagDeclaration>

    /**
     * List of '@see' tags.
     */
    val seeTags: List<KoValuedDocTagDeclaration>

    /**
     * List of '@author' tags.
     */
    val authorTags: List<KoDocTagDeclaration>

    /**
     * List of '@since' tags.
     */
    val sinceTag: KoDocTagDeclaration?

    /**
     * List of '@suppress' tags.
     */
    val suppressTag: KoDocTagDeclaration?

    /**
     * List of '@version' tags.
     */
    val versionTag: KoDocTagDeclaration?

    /**
     * List of '@propertySetter' tags.
     */
    val propertySetterTag: KoDocTagDeclaration?

    /**
     * List of '@propertyGetter' tags.
     */
    val propertyGetterTag: KoDocTagDeclaration?

    /**
     * Whather the declaration has the given tags.
     */
    fun hasTags(vararg tags: KoDocTag): Boolean
}
