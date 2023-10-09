package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoObjectProvider

/**
 * List containing object declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @return A list containing object declarations.
 */
fun <T : KoObjectProvider> List<T>.objects(
    includeNested: Boolean = true,
): List<KoObjectDeclaration> = flatMap { it.objects(includeNested) }
