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

    @Deprecated("Will be removed in v0.16.0", replaceWith = ReplaceWith("hasFunction()"))
    override fun containsFunction(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoFunctionDeclaration) -> Boolean,
    ): Boolean = functions(includeNested, includeLocal).any { predicate(it) }

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
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            functions(includeNested, includeLocal).any { function -> it == function.name }
        }
    }

    override fun hasFunctionsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
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
