package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.provider.KoArgumentProvider

/**
 * List containing argument declarations.
 */
val <T : KoArgumentProvider> List<T>.arguments: List<KoArgumentDeclaration>
    get() = flatMap { it.arguments }

/**
 * List containing elements with any argument.
 *
 * @return A list containing elements with any argument.
 */
fun <T : KoArgumentProvider> List<T>.withArguments(): List<T> = filter { it.hasArguments() }

/**
 * List containing elements with no argument.
 *
 * @return A list containing elements with no argument.
 */
fun <T : KoArgumentProvider> List<T>.withoutArguments(): List<T> = filterNot { it.hasArguments() }
