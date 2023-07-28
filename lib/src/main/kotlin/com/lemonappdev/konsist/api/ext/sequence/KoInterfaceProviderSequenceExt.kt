package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoInterfaceProvider

/**
 * Sequence containing interface declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @return A sequence containing interface declarations.
 */
fun <T : KoInterfaceProvider> Sequence<T>.interfaces(
    includeNested: Boolean = false,
): Sequence<KoInterfaceDeclaration> = flatMap { it.interfaces(includeNested) }
