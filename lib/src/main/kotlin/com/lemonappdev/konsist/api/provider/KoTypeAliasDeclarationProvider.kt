package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration

interface KoTypeAliasDeclarationProvider {
    /**
     * The type aliases of the file.
     */
    val typeAliases: List<KoTypeAliasDeclaration>

    /**
     * Whether the file has type aliases.
     *
     * @param names the names of the type aliases to check.
     * @return `true` if the file has type aliases with the specified names (or any type alias if [names] is empty), `false` otherwise.
     */
    fun hasTypeAliases(vararg names: String): Boolean
}
