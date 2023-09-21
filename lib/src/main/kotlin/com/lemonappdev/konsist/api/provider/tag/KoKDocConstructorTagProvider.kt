package com.lemonappdev.konsist.api.provider.tag

import com.lemonappdev.konsist.api.declaration.KoKDocTagDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * An interface representing a Kotlin declaration that provides access to KDoc constructor tag.
 */
interface KoKDocConstructorTagProvider : KoBaseProvider {
    /**
     * The `@constructor` tag.
     */
    val constructorTag: KoKDocTagDeclaration?

    /**
     * Whatever the declaration has constructor tag.
     *
     * @return `true` if the declaration has constructor tag, `false` otherwise.
     */
    fun hasConstructorTag(): Boolean
}
