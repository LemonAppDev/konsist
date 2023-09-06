package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to init block declarations.
 */
interface KoInitBlockProvider : KoBaseProvider {
    /**
     * The init blocks of the declaration.
     */
    val initBlocks: List<KoInitBlockDeclaration>

    /**
     * The number of init blocks.
     */
    val numInitBlocks: Int

    /**
     * Whatever declaration has init blocks.
     */
    val hasInitBlocks: Boolean

    /**
     * Gets the number of init blocks that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if an init block satisfies a condition.
     * @return The number of init blocks in the declaration.
     */
    fun countInitBlocks(predicate: (KoInitBlockDeclaration) -> Boolean): Int
}
