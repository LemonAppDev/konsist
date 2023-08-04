package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about classes.
 */
interface KoClassProvider : KoBaseProvider {
    /**
     * The classes present in the declaration.
     *
     * @param includeNested specifies whether to include nested classes.
     * @param includeLocal specifies whether to include local classes.
     * @return a list of [KoClassDeclaration] representing the classes in the declaration.
     */
    fun classes(
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): List<KoClassDeclaration>

    /**
     * Checks whether the declaration contains a class with the specified name.
     *
     * @param name The name of the class to check.
     * @param includeNested Specifies whether to include nested classes in the check (optional, default is `false`).
     * @param includeLocal Specifies whether to include local classes in the check (optional, default is `false`).
     * @return `true` if the declaration contains a class with the specified name, `false` otherwise.
     */
    fun containsClass(
        name: String,
        includeNested: Boolean = false,
        includeLocal: Boolean = false,
    ): Boolean

    /**
     * Gets the number of classes present in the declaration.
     *
     * @param includeNested Specifies whether to include nested classes in the count (optional, default is `false`).
     * @param includeLocal Specifies whether to include local classes in the count (optional, default is `false`).
     * @return The number of classes in the declaration.
     */
    fun numClasses(includeNested: Boolean = false, includeLocal: Boolean = false): Int
}
