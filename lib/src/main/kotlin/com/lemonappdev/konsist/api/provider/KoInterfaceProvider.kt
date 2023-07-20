package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration

interface KoInterfaceProvider : KoBaseProvider {
    /**
     * The interfaces present in the scope, file or declaration.
     *
     * @param includeNested specifies whether to include nested interfaces.
     * @return a sequence of [KoInterfaceDeclaration] representing the interfaces in the scope, file or declaration.
     */
    fun interfaces(
        includeNested: Boolean = false,
    ): Sequence<KoInterfaceDeclaration>

    fun containsInterface(
        name: String,
        includeNested: Boolean = false,
    ): Boolean

    fun numInterfaces(includeNested: Boolean = false): Int
}
