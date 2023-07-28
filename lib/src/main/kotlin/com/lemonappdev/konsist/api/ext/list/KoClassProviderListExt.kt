package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoClassProvider

/**
 * List containing class declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A list containing class declarations.
 */
fun <T : KoClassProvider> List<T>.classes(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): List<KoClassDeclaration> = flatMap { it.classes(includeNested, includeLocal) }
