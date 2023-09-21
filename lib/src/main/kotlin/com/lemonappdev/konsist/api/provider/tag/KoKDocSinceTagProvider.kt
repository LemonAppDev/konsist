package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc since tag.
 */
interface KoKDocSinceTagProvider : KoBaseProvider {
    /**
     * The `@since` tag.
     */
    val sinceTag: KoKDocTagDeclaration?

    /**
     * Whatever the declaration has since tag.
     *
     * @return `true` if the declaration has since tag, `false` otherwise.
     */
    fun hasSinceTag(): Boolean
}
