package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to KDoc tags.
 */
interface KoKDocTagsProvider : KoBaseProvider {
    /**
     * List of tags.
     */
    val tags: List<KoKDocTagDeclaration>

    /**
     * The number of tags.
     */
    val numTags: Int

    /**
     * List of `@param` tags.
     */
    val paramTags: List<KoValuedKDocTagDeclaration>

    /**
     * The `@return` tag.
     */
    val returnTag: KoKDocTagDeclaration?

    /**
     * The `@constructor` tag.
     */
    val constructorTag: KoKDocTagDeclaration?

    /**
     * The `@receiver` tag.
     */
    val receiverTag: KoKDocTagDeclaration?

    /**
     * List of `@property` tags.
     */
    val propertyTags: List<KoValuedKDocTagDeclaration>

    /**
     * List of `@throws` tags.
     */
    val throwsTags: List<KoValuedKDocTagDeclaration>

    /**
     * List of `@exception` tags.
     */
    val exceptionTags: List<KoValuedKDocTagDeclaration>

    /**
     * List of `@sample` tags.
     */
    val sampleTags: List<KoValuedKDocTagDeclaration>

    /**
     * List of `@see` tags.
     */
    val seeTags: List<KoValuedKDocTagDeclaration>

    /**
     * List of `@author` tags.
     */
    val authorTags: List<KoKDocTagDeclaration>

    /**
     * The `@since` tag.
     */
    val sinceTag: KoKDocTagDeclaration?

    /**
     * The `@suppress` tag.
     */
    val suppressTag: KoKDocTagDeclaration?

    /**
     * The `@version` tag.
     */
    val versionTag: KoKDocTagDeclaration?

    /**
     * The `@propertySetter` tag.
     */
    val propertySetterTag: KoKDocTagDeclaration?

    /**
     * The `@propertyGetter` tag.
     */
    val propertyGetterTag: KoKDocTagDeclaration?

    /**
     * Whether the declaration has the given tags.
     *
     * @param tags the tags to check.
     * @return `true` if the kDoc has all the specified tags (or any tags if [tags] is empty), `false` otherwise.
     */
    fun hasTags(vararg tags: KoKDocTag): Boolean
}
