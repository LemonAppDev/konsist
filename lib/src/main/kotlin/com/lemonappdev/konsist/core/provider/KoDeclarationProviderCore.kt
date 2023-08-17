package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider

internal interface KoDeclarationProviderCore : KoDeclarationProvider, KoBaseProviderCore {
    override fun containsDeclaration(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoBaseDeclaration) -> Boolean,
    ): Boolean = declarations(
        includeNested = includeNested,
        includeLocal = includeLocal,
    ).any { predicate(it) }

    override fun countDeclarations(includeNested: Boolean, includeLocal: Boolean): Int =
        declarations(includeNested, includeLocal).size

    override fun countDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoBaseDeclaration) -> Boolean,
    ): Int = declarations(includeNested, includeLocal).filter { predicate(it) }.size
}
