package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

interface KoPropertyProvider : KoDeclarationProvider {
    /**
     * The properties present in the scope, file or declaration.
     *
     * @param includeNested specifies whether to include nested properties.
     * @param includeLocal specifies whether to include local properties.
     * @return a sequence of [KoPropertyDeclaration] representing the properties in the scope, file or declaration.
     */
    fun properties(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoPropertyDeclaration>

    fun containsProperty(
        name: String,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean

    fun numProperties(includeNested: Boolean = false, includeLocal: Boolean = false): Int
}
