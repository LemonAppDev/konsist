package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsMutableTypeProvider

/**
 * List containing declarations with mutable type.
 *
 * @return A list containing declarations that have mutable type.
 */
fun <T : KoIsMutableTypeProvider> List<T>.withMutableType(): List<T> = filter { it.isMutableType }

/**
 * List containing declarations without mutable type.
 *
 * @return A list containing declarations that don't have mutable type.
 */
fun <T : KoIsMutableTypeProvider> List<T>.withoutMutableType(): List<T> = filterNot { it.isMutableType }
