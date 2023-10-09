package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoPropertyProvider

/**
 * List containing property declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A list containing property declarations.
 */
fun <T : KoPropertyProvider> List<T>.properties(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<KoPropertyDeclaration> = flatMap { it.properties(includeNested, includeLocal) }
