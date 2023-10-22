package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc property tags.
 */
interface KoKDocPropertyTagProvider : KoBaseProvider {
    /**
     * List of `@property` tags.
     */
    val propertyTags: List<KoValuedKDocTagDeclaration>

    /**
     * The number of property tags.
     */
    val numPropertyTags: Int

    /**
     * Determines whatever the declaration has property tags.
     */
    val hasPropertyTags: Boolean
}
