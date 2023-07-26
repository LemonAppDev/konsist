package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.ext.provider.hasReceiverTypeOf
import kotlin.reflect.KClass


/**
 * Sequence containing functions that have implementation.
 *
 * @return A sequence containing functions with the implementation.
 */
fun Sequence<KoFunctionDeclaration>.withImplementation(): Sequence<KoFunctionDeclaration> = filter { it.hasImplementation() }

/**
 * Sequence containing functions without implementation.
 *
 * @return A sequence containing functions without the implementation.
 */
fun Sequence<KoFunctionDeclaration>.withoutImplementation(): Sequence<KoFunctionDeclaration> = filterNot { it.hasImplementation() }
