package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about local properties.
 */
interface KoLocalPropertyProvider : KoBaseProvider {
    /**
     * The local properties present in the declaration.
     * @return a sequence of [KoPropertyDeclaration] representing the local properties in the declaration.
     */
    fun localProperties(): Sequence<KoPropertyDeclaration>

    /**
     * Checks whether the declaration contains a local property with the specified name.
     *
     * @param name The name of the local property to check.
     * @return `true` if the declaration contains a local property with the specified name, `false` otherwise.
     */
    fun containsLocalProperty(name: String): Boolean
}
