package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc author tags.
 */
interface KoKDocAuthorTagProvider : KoBaseProvider {
    /**
     * List of `@author` tags.
     */
    val authorTags: List<KoKDocTagDeclaration>

    /**
     * The number of author tags.
     */
    val numAuthorTags: Int

    /**
     * Determines whatever the declaration has author tags.
     */
    val hasAuthorTags: Boolean
}
