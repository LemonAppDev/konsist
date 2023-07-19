package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration

interface KoClassProvider : KoDeclarationProvider {
    /**
     * The classes present in the scope, file or declaration.
     *
     * @param includeNested specifies whether to include nested classes.
     * @param includeLocal specifies whether to include local classes.
     * @return a sequence of [KoClassDeclaration] representing the classes in the scope, file or declaration.
     */
    fun classes(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Sequence<KoClassDeclaration>

    fun containsClass(
        name: String,
        includeNested: Boolean = false,
    ): Boolean

    fun numClasses(includeNested: Boolean = false): Int
}
