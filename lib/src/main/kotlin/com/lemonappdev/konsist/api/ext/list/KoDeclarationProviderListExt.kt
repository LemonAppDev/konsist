package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider

/**
 * List containing elements of all types.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A list containing all declarations.
 */
fun <T : KoDeclarationProvider> List<T>.declarations(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): List<KoBaseDeclaration> = flatMap { it.declarations(includeNested, includeLocal) }
