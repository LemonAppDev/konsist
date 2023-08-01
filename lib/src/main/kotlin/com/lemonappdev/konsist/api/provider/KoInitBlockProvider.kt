package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to init block declarations.
 */
interface KoInitBlockProvider : KoBaseProvider {
    /**
     * The init blocks of the declaration.
     */
    val initBlocks: List<KoInitBlockDeclaration>?

    /**
     * The number of init blocks in declaration.
     */
    val numInitBlocks: Int

    /**
     * Whatever declaration has init blocks.
     */
    val hasInitBlocks: Boolean
}
