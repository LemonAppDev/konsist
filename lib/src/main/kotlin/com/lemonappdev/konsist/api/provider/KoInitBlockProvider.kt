package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration

interface KoInitBlockProvider : KoProvider {
    /**
     * The init blocks of the declaration.
     */
    val initBlocks: Sequence<KoInitBlockDeclaration>?

    /**
     * The number of init blocks in declaration.
     */
    val numInitBlocks: Int

    /**
     * Whatever declaration has init blocks.
     *
     * @return `true` if the declaration has init block(s), `false` otherwise.
     */
    fun hasInitBlocks(): Boolean
}
