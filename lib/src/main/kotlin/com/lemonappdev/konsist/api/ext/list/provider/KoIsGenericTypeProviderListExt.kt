package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.provider.KoIsGenericTypeProvider

/**
 * List containing declarations with generic type.
 *
 * @return A list containing declarations with the generic types.
 */
@Deprecated("Will be removed in version 0.18.0", ReplaceWith("withGenericTypeDeclaration"))
fun <T : KoIsGenericTypeProvider> List<T>.withGenericType(): List<T> = filter { it.isGenericType }

/**
 * List containing declarations without generic type.
 *
 * @return A list containing declarations without the generic types.
 */
@Deprecated("Will be removed in version 0.18.0", ReplaceWith("withoutGenericTypeDeclaration"))
fun <T : KoIsGenericTypeProvider> List<T>.withoutGenericType(): List<T> = filterNot { it.isGenericType }
