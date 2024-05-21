package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoFunctionProviderCore : KoFunctionProvider, KoDeclarationProviderCore, KoBaseProviderCore {
    override fun functions(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoFunctionDeclaration> =
        KoDeclarationProviderCoreUtil.getKoDeclarations(
            declarations(includeNested = false, includeLocal = false),
            includeNested,
            includeLocal,
        )

    override fun numFunctions(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int = functions(includeNested, includeLocal).size

    override fun countFunctions(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoFunctionDeclaration) -> Boolean,
    ): Int = functions(includeNested, includeLocal).count { predicate(it) }

    override fun hasFunctions(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = functions(includeNested, includeLocal).isNotEmpty()

    override fun hasFunctionWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = hasFunctionWithName(listOf(name, *names), includeNested, includeLocal)

    override fun hasFunctionWithName(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasFunctions(includeNested, includeLocal)
            else ->
                names.any {
                    functions(includeNested, includeLocal).any { function -> it == function.name }
                }
        }

    override fun hasFunctionsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = hasFunctionsWithAllNames(listOf(name, *names), includeNested, includeLocal)

    override fun hasFunctionsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasFunctions(includeNested, includeLocal)
            else ->
                names.all {
                    functions(includeNested, includeLocal).any { function -> it == function.name }
                }
        }

    override fun hasFunction(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoFunctionDeclaration) -> Boolean,
    ): Boolean = functions(includeNested, includeLocal).any(predicate)

    override fun hasAllFunctions(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoFunctionDeclaration) -> Boolean,
    ): Boolean = functions(includeNested, includeLocal).all(predicate)
}
