package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalFunctionProvider

internal interface KoLocalFunctionProviderCore :
    KoLocalFunctionProvider,
    KoLocalDeclarationProviderCore,
    KoBaseProviderCore {
    override val localFunctions: List<KoFunctionDeclaration>
        get() = localDeclarations.filterIsInstance<KoFunctionDeclaration>()

    override val numLocalFunctions: Int
        get() = localFunctions.size

    override fun countLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Int =
        localFunctions.count { predicate(it) }

    @Deprecated("Will be removed in v0.16.0", replaceWith = ReplaceWith("hasLocalFunction()"))
    override fun containsLocalFunction(predicate: (KoFunctionDeclaration) -> Boolean): Boolean =
        localFunctions.any { predicate(it) }

    override fun hasLocalFunctions(): Boolean = localFunctions.isNotEmpty()

    override fun hasLocalFunctionWithName(name: String, vararg names: String): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            localFunctions.any { localFunction -> it == localFunction.name }
        }
    }

    override fun hasLocalFunctionsWithAllNames(name: String, vararg names: String): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            localFunctions.any { localFunction -> it == localFunction.name }
        }
    }

    override fun hasLocalFunction(predicate: (KoFunctionDeclaration) -> Boolean): Boolean = localFunctions.any(predicate)

    override fun hasAllLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Boolean = localFunctions.all(predicate)
}
