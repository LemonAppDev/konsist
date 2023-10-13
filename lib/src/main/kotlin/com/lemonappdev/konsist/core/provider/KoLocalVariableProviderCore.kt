package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoVariableDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalVariableProvider

internal interface KoLocalVariableProviderCore :
    KoLocalVariableProvider,
    KoLocalDeclarationProviderCore,
    KoBaseProviderCore {
    override val localVariables: List<KoVariableDeclaration>
        get() = localDeclarations.filterIsInstance<KoVariableDeclaration>()

    override val numLocalVariables: Int
        get() = localVariables.size

    override fun countLocalVariables(predicate: (KoVariableDeclaration) -> Boolean): Int =
        localVariables.count { predicate(it) }

    override fun hasLocalVariables(): Boolean = localVariables.isNotEmpty()

    override fun hasLocalVariableWithName(name: String, vararg names: String): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            localVariables.any { localVariable -> it == localVariable.name }
        }
    }

    override fun hasLocalVariablesWithAllNames(name: String, vararg names: String): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            localVariables.any { localVariable -> it == localVariable.name }
        }
    }

    override fun hasLocalVariable(predicate: (KoVariableDeclaration) -> Boolean): Boolean = localVariables.any(predicate)

    override fun hasAllLocalVariables(predicate: (KoVariableDeclaration) -> Boolean): Boolean = localVariables.all(predicate)
}
