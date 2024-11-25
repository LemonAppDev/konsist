package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoIsMutableTypeProvider

/**
 * Returns a list of declarations with mutable types.
 *
 * A mutable type is considered any type whose name starts with "Mutable",
 * such as `MutableList`, `MutableStateFlow`, `MutableLiveData`, etc.
 *
 * @return A list of declarations that have a mutable type.
 */
fun <T : KoIsMutableTypeProvider> List<T>.withMutableType(): List<T> = filter { it.isMutableType }

/**
 * Returns a list of declarations without mutable types.
 *
 * A mutable type is considered any type whose name starts with "Mutable",
 * such as `MutableList`, `MutableStateFlow`, `MutableLiveData`, etc.
 *
 * @return A list of declarations that have not a mutable type.
 */
fun <T : KoIsMutableTypeProvider> List<T>.withoutMutableType(): List<T> = filterNot { it.isMutableType }
