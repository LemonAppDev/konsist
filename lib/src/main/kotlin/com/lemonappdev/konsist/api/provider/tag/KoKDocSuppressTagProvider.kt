package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc suppress tag.
 */
interface KoKDocSuppressTagProvider : KoBaseProvider {
    /**
     * The `@suppress` tag.
     */
    val suppressTag: KoKDocTagDeclaration?

    /**
     * Whatever the declaration has suppress tag.
     *
     * @return `true` if the declaration has suppress tag, `false` otherwise.
     */
    fun hasSuppressTag(): Boolean
}
