package com.lemonappdev.konsist.api.ext.koscope

import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.provider.KoBaseProvider

/**
 * The declarations of type [T] present in the scope.
 *
 * @param includeNested specifies whether to include nested declarations, by default `false`.
 * @param includeLocal specifies whether to include local declarations, by default `false`.
 * @return A list of declarations of type [T] present in the scope.
 */
inline fun <reified T: KoBaseProvider> KoScope.declarationsOf(includeNested: Boolean = false, includeLocal: Boolean = false): List<T> =
    declarations(includeNested, includeLocal)
        .filterIsInstance<T>()
