package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoObjectProvider

/**
 * Sequence containing object declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @return A sequence containing object declarations.
 */
fun <T : KoObjectProvider> Sequence<T>.objects(
    includeNested: Boolean = false,
): Sequence<KoObjectDeclaration> = flatMap { it.objects(includeNested) }
