package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration

interface KoTypeAliasProvider {
    /**
     * Sequence of type aliases.
     */
    val typeAliases: Sequence<KoTypeAliasDeclaration>

    /**
     * Whether the file or scope has type aliases.
     *
     * @param names the names of the type aliases to check.
     * @return `true` if the file or scope has type aliases with the specified names (or any type alias if [names] is empty), `false` otherwise.
     */
    fun hasTypeAliases(vararg names: String): Boolean
}
