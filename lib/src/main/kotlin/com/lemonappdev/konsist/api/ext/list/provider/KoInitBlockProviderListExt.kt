package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider

/**
 * List containing init block declarations.
 */
val <T : KoInitBlockProvider> List<T>.initBlocks: List<KoInitBlockDeclaration>
    get() = flatMap { it.initBlocks }

/**
 * List containing declarations that have init block(s).
 *
 * @return A list containing declarations with the init block(s).
 */
fun <T : KoInitBlockProvider> List<T>.withInitBlocks(): List<T> = filter { it.hasInitBlocks() }

/**
 * List containing declarations that don't have init block(s).
 *
 * @return A list containing declarations without the init block(s).
 */
fun <T : KoInitBlockProvider> List<T>.withoutInitBlocks(): List<T> = filterNot { it.hasInitBlocks() }

/**
 * List containing declarations that have at least one init block satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an init block declaration.
 * @return A list containing declarations with at least one init block satisfying the predicate.
 */
fun <T : KoInitBlockProvider> List<T>.withInitBlock(predicate: (KoInitBlockDeclaration) -> Boolean): List<T> =
    filter {
        it.hasInitBlock(predicate)
    }

/**
 * List containing declarations that not have init block satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by an init block declaration.
 * @return A list containing declarations without init block satisfying the provided predicate.
 */
fun <T : KoInitBlockProvider> List<T>.withoutInitBlock(predicate: (KoInitBlockDeclaration) -> Boolean): List<T> =
    filterNot {
        it.hasInitBlock(predicate)
    }

/**
 * List containing declarations that have all init blocks satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all init block declarations.
 * @return A filtered list containing declarations with all init blocks satisfying the predicate.
 */
fun <T : KoInitBlockProvider> List<T>.withAllInitBlocks(predicate: (KoInitBlockDeclaration) -> Boolean): List<T> =
    filter {
        it.hasAllInitBlocks(predicate)
    }

/**
 * List containing declarations that have at least one init block not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all init block declarations.
 * @return A list containing declarations that have at least one init block not satisfying the provided predicate.
 */
fun <T : KoInitBlockProvider> List<T>.withoutAllInitBlocks(predicate: (KoInitBlockDeclaration) -> Boolean): List<T> =
    filterNot {
        it.hasAllInitBlocks(predicate)
    }

/**
 * List containing declarations with init block declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of init block declarations.
 * @return A list containing declarations with init block declarations satisfying the predicate.
 */
fun <T : KoInitBlockProvider> List<T>.withInitBlocks(predicate: (List<KoInitBlockDeclaration>) -> Boolean): List<T> =
    filter {
        predicate(it.initBlocks)
    }

/**
 * List containing declarations without init block declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of init block declarations.
 * @return A list containing declarations without init block declarations satisfying the predicate.
 */
fun <T : KoInitBlockProvider> List<T>.withoutInitBlocks(predicate: (List<KoInitBlockDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.initBlocks) }
