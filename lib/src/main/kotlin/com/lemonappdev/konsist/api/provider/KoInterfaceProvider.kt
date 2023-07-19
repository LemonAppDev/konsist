package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration

interface KoInterfaceProvider: KoDeclarationProvider {
    fun interfaces(
        includeNested: Boolean = false,
    ): Sequence<KoInterfaceDeclaration>

    fun containsInterface(
        name: String,
        includeNested: Boolean = false,
    ): Boolean

    fun numInterfaces(includeNested: Boolean = false): Int
}
