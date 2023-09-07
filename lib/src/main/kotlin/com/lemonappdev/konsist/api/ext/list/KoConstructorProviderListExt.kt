package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoConstructorProvider

/**
 * List containing constructor declarations.
 */
val <T : KoConstructorProvider> List<T>.constructors: List<KoConstructorDeclaration>
    get() = flatMap { it.constructors }

/**
 * List containing declarations with constructor.
 *
 * @return A list containing declarations with the constructor.
 */
fun <T : KoConstructorProvider> List<T>.withConstructor(): List<T> = filterNot { it.numConstructors == 0 }

/**
 * List containing declarations without constructor.
 *
 * @return A list containing declarations without the constructor.
 */
fun <T : KoConstructorProvider> List<T>.withoutConstructor(): List<T> = filter { it.numConstructors == 0 }
