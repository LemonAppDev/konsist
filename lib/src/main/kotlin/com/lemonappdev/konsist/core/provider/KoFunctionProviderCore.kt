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
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoFunctionDeclaration) -> Boolean
    ): Boolean = functions(includeNested, includeLocal).any { predicate(it) }

    override fun numFunctions(includeNested: Boolean, includeLocal: Boolean): Int =
        functions(includeNested, includeLocal).size
}
