package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionProvider

/**
 * List containing function declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A list containing function declarations.
 */
fun <T : KoFunctionProvider> List<T>.functions(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<KoFunctionDeclaration> = flatMap { it.functions(includeNested, includeLocal) }
