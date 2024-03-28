package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoVariableDeclaration
import com.lemonappdev.konsist.api.provider.KoVariableProvider

internal interface KoVariableProviderCore :
    KoVariableProvider,
    KoLocalDeclarationProviderCore,
    KoBaseProviderCore {
    override val variables: List<KoVariableDeclaration>
        get() = localDeclarations.filterIsInstance<KoVariableDeclaration>()

    override val numVariables: Int
        get() = variables.size

    override fun countVariables(predicate: (KoVariableDeclaration) -> Boolean): Int = variables.count { predicate(it) }

    override fun hasVariables(): Boolean = variables.isNotEmpty()

    override fun hasVariableWithName(
        name: String,
        vararg names: String,
    ): Boolean = hasVariableWithName(listOf(name, *names))

    override fun hasVariableWithName(names: Collection<String>): Boolean = when {
        names.isEmpty() -> hasVariables()
        else -> names.any {
            variables.any { variable -> it == variable.name }
        }
    }

    override fun hasVariablesWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean = hasVariablesWithAllNames(listOf(name, *names))

    override fun hasVariablesWithAllNames(names: Collection<String>): Boolean = when {
        names.isEmpty() -> hasVariables()
        else -> names.all {
            variables.any { variable -> it == variable.name }
        }
    }

    override fun hasVariable(predicate: (KoVariableDeclaration) -> Boolean): Boolean = variables.any(predicate)

    override fun hasAllVariables(predicate: (KoVariableDeclaration) -> Boolean): Boolean = variables.all(predicate)
}
