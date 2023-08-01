package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.provider.KoInterfaceProvider

/**
 * List containing interface declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @return A list containing interface declarations.
 */
fun <T : KoInterfaceProvider> List<T>.interfaces(
    includeNested: Boolean = false,
): List<KoInterfaceDeclaration> = flatMap { it.interfaces(includeNested) }
