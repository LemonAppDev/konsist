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

    override fun countLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Int = localFunctions.count { predicate(it) }

    override fun hasLocalFunctions(): Boolean = localFunctions.isNotEmpty()

    override fun hasLocalFunctionWithName(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = hasLocalFunctionWithName(listOf(name, *names), ignoreCase)

    override fun hasLocalFunctionWithName(
        names: Collection<String>,
        ignoreCase: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasLocalFunctions()
            else ->
                names.any {
                    localFunctions.any { localFunction -> localFunction.hasName(it, ignoreCase) }
                }
        }

    override fun hasLocalFunctionsWithAllNames(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = hasLocalFunctionsWithAllNames(listOf(name, *names), ignoreCase)

    override fun hasLocalFunctionsWithAllNames(
        names: Collection<String>,
        ignoreCase: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasLocalFunctions()
            else ->
                names.all {
                    localFunctions.any { localFunction -> localFunction.hasName(it, ignoreCase) }
                }
        }

    override fun hasLocalFunction(predicate: (KoFunctionDeclaration) -> Boolean): Boolean = localFunctions.any(predicate)

    override fun hasAllLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Boolean = localFunctions.all(predicate)
}
