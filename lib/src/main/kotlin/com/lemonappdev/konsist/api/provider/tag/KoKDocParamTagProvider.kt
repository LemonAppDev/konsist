package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc param tags.
 */
interface KoKDocParamTagProvider : KoBaseProvider {
    /**
     * List of `@param` tags.
     */
    val paramTags: List<KoValuedKDocTagDeclaration>

    /**
     * The number of param tags.
     */
    val numParamTags: Int

    /**
     * Determines whatever the declaration has param tags.
     */
    val hasParamTags: Boolean
}
