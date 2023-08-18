package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider

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

    override fun countPublic(includeNested: Boolean, includeLocal: Boolean): Int =
        declarations(includeNested, includeLocal)
            .filterIsInstance<KoVisibilityModifierProvider>()
            .count { it.hasPublicModifier }

    override fun countPublicOrDefault(includeNested: Boolean, includeLocal: Boolean): Int =
        declarations(includeNested, includeLocal)
            .filterIsInstance<KoVisibilityModifierProvider>()
            .count { it.isPublicOrDefault }

    override fun countPrivate(includeNested: Boolean, includeLocal: Boolean): Int =
        declarations(includeNested, includeLocal)
            .filterIsInstance<KoVisibilityModifierProvider>()
            .count { it.hasPrivateModifier }

    override fun countProtected(includeNested: Boolean, includeLocal: Boolean): Int =
        declarations(includeNested, includeLocal)
            .filterIsInstance<KoVisibilityModifierProvider>()
            .count { it.hasProtectedModifier }

    override fun countInternal(includeNested: Boolean, includeLocal: Boolean): Int =
        declarations(includeNested, includeLocal)
            .filterIsInstance<KoVisibilityModifierProvider>()
            .count { it.hasInternalModifier }
}
