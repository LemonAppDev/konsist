package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc throws tags.
 */
interface KoKDocThrowsTagProvider : KoBaseProvider {
    /**
     * List of `@throws` tags.
     */
    val throwsTags: List<KoValuedKDocTagDeclaration>

    /**
     * The number of throws tags.
     */
    val numThrowsTags: Int

    /**
     * Determines whatever the declaration has throws tags.
     */
    val hasThrowsTags: Boolean
}
