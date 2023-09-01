package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeProvider

/**
 * List containing type declarations.
 */
val <T : KoTypeProvider> List<T>.types: List<KoTypeDeclaration>
    get() = map { it.type }
