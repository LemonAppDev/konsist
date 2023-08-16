package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoFunctionProviderCore : KoFunctionProvider, KoDeclarationProviderCore, KoBaseProviderCore {
    override fun functions(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoFunctionDeclaration> =
        KoDeclarationProviderCoreUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    override fun containsFunction(
        name: String,
        vararg modifiers: KoModifier,
        includeNested: Boolean,
        includeLocal: Boolean
    ): Boolean = if (modifiers.isEmpty()) {
        functions(includeNested, includeLocal).any { it.name == name }
    } else {
        functions(includeNested, includeLocal).any { it.name == name && it.hasModifiers(*modifiers) }
    }

    override fun containsFunction(
        regex: Regex,
        vararg modifiers: KoModifier,
        includeNested: Boolean,
        includeLocal: Boolean
    ): Boolean = if (modifiers.isEmpty()) {
        functions(includeNested, includeLocal).any { it.hasNameMatching(regex) }
    } else {
        functions(includeNested, includeLocal).any { it.hasNameMatching(regex) && it.hasModifiers(*modifiers) }
    }

    override fun numFunctions(includeNested: Boolean, includeLocal: Boolean): Int =
        functions(includeNested, includeLocal).size
}
