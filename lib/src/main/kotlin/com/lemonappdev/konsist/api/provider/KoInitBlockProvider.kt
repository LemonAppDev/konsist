package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to init block declarations.
 */
interface KoInitBlockProvider : KoBaseProvider {
    /**
     * The init blocks of the declaration.
     */
    val initBlocks: Sequence<KoInitBlockDeclaration>?

    /**
     * The number of init blocks.
     */
    val numInitBlocks: Int

    /**
     * Whatever declaration has init blocks.
     *
     * @return `true` if the declaration has init block(s), `false` otherwise.
     */
    fun hasInitBlocks(): Boolean
}
