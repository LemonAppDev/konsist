package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsMutableTypeProvider

/**
 * List containing declarations with mutable type.
 *
 * @return A list containing declarations that have a mutable type.
 * @see KoIsMutableTypeProvider.isMutableType
 */
fun <T : KoIsMutableTypeProvider> List<T>.withMutableType(): List<T> = filter { it.isMutableType }

/**
 * List containing declarations without mutable type.
 *
 * @return A list containing declarations that do not have a mutable type.
 * @see KoIsMutableTypeProvider.isMutableType
 */
fun <T : KoIsMutableTypeProvider> List<T>.withoutMutableType(): List<T> = filterNot { it.isMutableType }

/**
 * List containing declarations with mutable type argument.
 *
 * @return A list containing declarations that have a mutable type argument at any nesting level.
 * @see KoIsMutableTypeProvider.hasMutableTypeArgument
 */
fun <T : KoIsMutableTypeProvider> List<T>.withMutableTypeArgument(): List<T> = filter { it.hasMutableTypeArgument }

/**
 * List containing declarations without mutable type argument.
 *
 * @return A list containing declarations that do not have a mutable type argument at any nesting level.
 * @see KoIsMutableTypeProvider.hasMutableTypeArgument
 */
fun <T : KoIsMutableTypeProvider> List<T>.withoutMutableTypeArgument(): List<T> = filterNot { it.hasMutableTypeArgument }

/**
 * List containing declarations with immutable type.
 *
 * @return A list containing declarations that have an immutable type.
 * @see KoIsMutableTypeProvider.isImmutableType
 */
fun <T : KoIsMutableTypeProvider> List<T>.withImmutableType(): List<T> = filter { it.isImmutableType }

/**
 * List containing declarations without immutable type.
 *
 * @return A list containing declarations that do not have an immutable type.
 * @see KoIsMutableTypeProvider.isImmutableType
 */
fun <T : KoIsMutableTypeProvider> List<T>.withoutImmutableType(): List<T> = filterNot { it.isImmutableType }

/**
 * List containing declarations with immutable type argument.
 *
 * @return A list containing declarations that have an immutable type argument at any nesting level.
 * @see KoIsMutableTypeProvider.hasImmutableTypeArgument
 */
fun <T : KoIsMutableTypeProvider> List<T>.withImmutableTypeArgument(): List<T> = filter { it.hasImmutableTypeArgument }

/**
 * List containing declarations without immutable type argument.
 *
 * @return A list containing declarations that do not have an immutable type argument at any nesting level.
 * @see KoIsMutableTypeProvider.hasImmutableTypeArgument
 */
fun <T : KoIsMutableTypeProvider> List<T>.withoutImmutableTypeArgument(): List<T> = filterNot { it.hasImmutableTypeArgument }
