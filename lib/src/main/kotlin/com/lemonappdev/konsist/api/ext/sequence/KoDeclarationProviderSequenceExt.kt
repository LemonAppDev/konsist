package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider

/**
 * Sequence containing declarations of all types.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A sequence containing all declarations.
 */
fun <T : KoDeclarationProvider> Sequence<T>.declarations(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): Sequence<KoBaseDeclaration> = flatMap { it.declarations(includeNested, includeLocal) }
