package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to type aliases.
 *
 */
interface KoTypeAliasProvider : KoBaseProvider {
    /**
     * List of type aliases.
     */
    val typeAliases: List<KoTypeAliasDeclaration>

    /**
     * The number of type aliases.
     */
    val numTypeAliases: Int

    /**
     * Whether the declaration has type aliases.
     *
     * @param names the names of the type aliases to check.
     * @return `true` if the declaration has type aliases with the specified names (or any type alias if [names] is empty),
     * `false` otherwise.
     */
    fun hasTypeAliases(vararg names: String): Boolean
}
