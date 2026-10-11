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
