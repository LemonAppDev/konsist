package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration

interface KoObjectProvider: KoDeclarationProvider {
    fun objects(
        includeNested: Boolean = false,
    ): Sequence<KoObjectDeclaration>


    fun containsObject(
        name: String,
        includeNested: Boolean = false,
    ): Boolean

    fun numObjects(includeNested: Boolean = false): Int
}
