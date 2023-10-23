package com.lemonappdev.konsist.api.provider

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
     * Determines whatever declaration has init blocks.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasInitBlocks()"))
    val hasInitBlocks: Boolean

    /**
     * Returns the number of init blocks that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if an init block satisfies a condition.
     * @return The number of init blocks in the declaration.
     */
    fun countInitBlocks(predicate: (KoInitBlockDeclaration) -> Boolean): Int

    /**
     * Determines whatever declaration has init blocks.
     *
     * @return `true` if the declaration has init block, `false` otherwise.
     */
    fun hasInitBlocks(): Boolean

    /**
     * Determines whether the declaration has at least one init block that satisfies the provided predicate.
     *
     * @param predicate A function that defines the condition to be met by a init block declaration.
     * @return `true` if there is a matching declaration, `false` otherwise.
     */
    fun hasInitBlock(predicate: (KoInitBlockDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has all init blocks that satisfy the provided predicate.
     *
     * Note that if the init blocks contains no elements, the function returns `true` because there are no elements in it
     * that do not match the predicate.
     *
     * @param predicate A function that defines the condition to be met by init block declarations.
     * @return `true` if all init block declarations satisfy the predicate, `false` otherwise.
     */
    fun hasAllInitBlocks(predicate: (KoInitBlockDeclaration) -> Boolean): Boolean
}
