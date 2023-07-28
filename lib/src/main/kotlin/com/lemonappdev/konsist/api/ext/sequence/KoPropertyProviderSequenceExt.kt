package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoPropertyProvider

/**
 * Sequence containing properties declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A sequence containing property declarations.
 */
fun <T : KoPropertyProvider> Sequence<T>.properties(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoPropertyDeclaration> = flatMap { it.properties(includeNested, includeLocal) }
