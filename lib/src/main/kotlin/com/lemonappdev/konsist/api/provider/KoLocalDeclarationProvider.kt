package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about local declarations.
 */
interface KoLocalDeclarationProvider : KoBaseProvider {
    /**
     * The local declarations present in the declaration.
     * @return a sequence of [KoBaseProvider] representing the local declarations in the declaration.
     */
    fun localDeclarations(): Sequence<KoBaseDeclaration>

    /**
     * The number of local declarations.
     */
    val numLocalDeclarations: Int

    /**
     * Checks whether the declaration contains a local declaration with the specified name.
     *
     * @param name The name of the local declaration to check.
     * @return `true` if the declaration contains a local declaration with the specified name, `false` otherwise.
     */
    fun containsLocalDeclarations(name: String): Boolean
}
