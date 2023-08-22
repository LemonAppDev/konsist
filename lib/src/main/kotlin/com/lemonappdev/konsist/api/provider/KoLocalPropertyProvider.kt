package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about local properties.
 */
interface KoLocalPropertyProvider : KoBaseProvider {
    /**
     * The local properties present in the declaration.
     */
    val localProperties: List<KoPropertyDeclaration>

    /**
     * The number of local properties.
     */
    val numLocalProperties: Int

    /**
     * Gets the number of local properties that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a local property satisfies a condition.
     * @return The number of local properties in the declaration.
     */
    fun countLocalProperties(predicate: (KoPropertyDeclaration) -> Boolean): Int

    /**
     * Checks whether the declaration contains a local property with the specified name.
     *
     * @param predicate The predicate function to determine if a local property satisfies a condition.
     * @return `true` if the declaration contains a local property with the specified name, `false` otherwise.
     */
    fun containsLocalProperty(predicate: (KoPropertyDeclaration) -> Boolean): Boolean
}
