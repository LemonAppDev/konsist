package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.KoKDocTag
import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
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
     * Whatever the declaration has author tags.
     *
     * @return `true` if the declaration has author tag, `false` otherwise.
     */
    fun hasAuthorTags(): Boolean
}
