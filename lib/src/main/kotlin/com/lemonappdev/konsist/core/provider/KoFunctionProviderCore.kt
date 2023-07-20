package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoFunctionProviderCore : KoFunctionProvider, KoDeclarationProviderCore, KoBaseProviderCore {
    override fun functions(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoFunctionDeclaration> = KoDeclarationProviderCoreUtil.getKoDeclarations(declarations(), includeNested, includeLocal)

    override fun containsFunction(
        name: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = functions(includeNested, includeLocal).any { it.name == name }

    override fun numFunctions(includeNested: Boolean, includeLocal: Boolean): Int =
        functions(includeNested, includeLocal).toList().size
}
