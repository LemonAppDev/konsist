package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.declaration.KoValuedKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc see tags.
 */
interface KoKDocSeeTagProvider : KoBaseProvider {
    /**
     * List of `@see` tags.
     */
    val seeTags: List<KoValuedKDocTagDeclaration>

    /**
     * The number of see tags.
     */
    val numSeeTags: Int

    /**
     * Determines whatever the declaration has see tags.
     */
    val hasSeeTags: Boolean
}
