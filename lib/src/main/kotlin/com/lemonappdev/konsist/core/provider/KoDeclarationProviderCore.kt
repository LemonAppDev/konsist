package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider

internal interface KoDeclarationProviderCore : KoDeclarationProvider, KoBaseProviderCore {
    override fun numDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int = declarations(includeNested, includeLocal).size

    override fun countDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoBaseDeclaration) -> Boolean,
    ): Int = declarations(includeNested, includeLocal).count { predicate(it) }

    override fun numPublicDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int =
        declarations(includeNested, includeLocal)
            .filterIsInstance<KoVisibilityModifierProvider>()
            .count { it.hasPublicModifier }

    override fun numPublicOrDefaultDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int =
        declarations(includeNested, includeLocal)
            .filterIsInstance<KoVisibilityModifierProvider>()
            .count { it.hasPublicOrDefaultModifier }

    override fun numPrivateDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int =
        declarations(includeNested, includeLocal)
            .filterIsInstance<KoVisibilityModifierProvider>()
            .count { it.hasPrivateModifier }

    override fun numProtectedDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int =
        declarations(includeNested, includeLocal)
            .filterIsInstance<KoVisibilityModifierProvider>()
            .count { it.hasProtectedModifier }

    override fun numInternalDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int =
        declarations(includeNested, includeLocal)
            .filterIsInstance<KoVisibilityModifierProvider>()
            .count { it.hasInternalModifier }

    override fun hasDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = declarations(includeNested, includeLocal).isNotEmpty()

    override fun hasDeclaration(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoBaseDeclaration) -> Boolean,
    ): Boolean = declarations(includeNested, includeLocal).any(predicate)

    override fun hasAllDeclarations(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoBaseDeclaration) -> Boolean,
    ): Boolean = declarations(includeNested, includeLocal).all(predicate)
}
