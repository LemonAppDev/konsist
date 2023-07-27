package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionProvider

/**
 * Sequence containing functions declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A sequence containing function declarations.
 */
fun <T : KoFunctionProvider> Sequence<T>.functions(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoFunctionDeclaration> = flatMap { it.functions(includeNested, includeLocal) }
