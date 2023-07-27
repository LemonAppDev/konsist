package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoClassProvider


/**
 * Sequence containing class declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A sequence containing class declarations.
 */
fun <T : KoClassProvider> Sequence<T>.classes(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoClassDeclaration> = flatMap { it.classes(includeNested, includeLocal) }
