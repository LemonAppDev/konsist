package com.lemonappdev.konsist.api.ext.provider

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider

/**
 * The declarations of type [T] present in the declaration.
 *
 * @param includeNested specifies whether to include nested declarations, by default `false`.
 * @param includeLocal specifies whether to include local declarations, by default `false`.
 * @return A list of declarations of type [T] present in the declaration.
 */
inline fun <reified T : KoBaseProvider> KoDeclarationProvider.declarationsOf(
    includeNested: Boolean = false,
    includeLocal: Boolean = false,
): List<T> =
    declarations(includeNested, includeLocal)
        .filterIsInstance<T>()
