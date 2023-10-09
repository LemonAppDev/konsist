package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about local classes.
 */
interface KoLocalClassProvider : KoBaseProvider {
    /**
     * The local classes present in the declaration.
     */
    val localClasses: List<KoClassDeclaration>

    /**
     * The number of local classes.
     */
    val numLocalClasses: Int

    /**
     * Gets the number of local classes that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if a local class satisfies a condition.
     * @return The number of local classes in the declaration.
     */
    fun countLocalClasses(predicate: (KoClassDeclaration) -> Boolean): Int

    /**
     * Checks whether the declaration contains a local class with the specified name.
     *
     * @param predicate The predicate function to determine if a local class satisfies a condition.
     * @return `true` if the declaration contains a local class with the specified predicate, `false` otherwise.
     */
    fun containsLocalClass(predicate: (KoClassDeclaration) -> Boolean): Boolean
}
