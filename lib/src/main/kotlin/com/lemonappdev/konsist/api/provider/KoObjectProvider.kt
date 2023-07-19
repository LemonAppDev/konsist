package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration

interface KoObjectProvider : KoDeclarationProvider {
    /**
     * The objects present in the scope, file or declaration.
     *
     * @param includeNested specifies whether to include nested objects.
     * @return a sequence of [KoObjectDeclaration] representing the objects in the scope, file or declaration.
     */
    fun objects(
        includeNested: Boolean = false,
    ): Sequence<KoObjectDeclaration>

    fun containsObject(
        name: String,
        includeNested: Boolean = false,
    ): Boolean

    fun numObjects(includeNested: Boolean = false): Int
}
