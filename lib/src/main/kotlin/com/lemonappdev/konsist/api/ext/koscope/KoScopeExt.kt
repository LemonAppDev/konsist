package com.lemonappdev.konsist.api.ext.koscope

import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * The declarations of type [T] present in the scope.
 *
 * @param includeNested specifies whether to include nested declarations, by default `true`.
 * @param includeLocal specifies whether to include local declarations, by default `true`.
 * @return A list of declarations of type [T] present in the scope.
 */
inline fun <reified T : KoBaseProvider> KoScope.declarationsOf(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    declarations(includeNested, includeLocal)
        .filterIsInstance<T>()
