package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsMutableTypeProvider

/**
 * List containing declarations with mutable type.
 *
 * Any type whose name starts with "Mutable" is considered mutable,
 * such as `MutableList`, `MutableStateFlow`, `MutableLiveData`, etc.
 *
 * @return A list containing declarations that have a mutable type.
 */
fun <T : KoIsMutableTypeProvider> List<T>.withMutableType(): List<T> = filter { it.isMutableType }

/**
 * List containing declarations without mutable type.
 *
 * Any type whose name starts with "Mutable" is considered mutable,
 * such as `MutableList`, `MutableStateFlow`, `MutableLiveData`, etc.
 *
 * @return A list containing declarations that do not have a mutable type.
 */
fun <T : KoIsMutableTypeProvider> List<T>.withoutMutableType(): List<T> = filterNot { it.isMutableType }
