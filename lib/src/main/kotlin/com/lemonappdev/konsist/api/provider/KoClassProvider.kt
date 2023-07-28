package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration

/**
 * An interface representing a Kotlin scope, file, or declaration that provides information about classes.
 */
interface KoClassProvider : KoBaseProvider {
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

    /**
     * Checks whether the scope, file or declaration contains a class with the specified name.
     *
     * @param name The name of the class to check.
     * @param includeNested Specifies whether to include nested classes in the check (optional, default is `false`).
     * @return `true` if the scope, file, or declaration contains a class with the specified name, `false` otherwise.
     */
    fun containsClass(
        name: String,
        includeNested: Boolean = false,
    ): Boolean

    /**
     * Gets the number of classes present in the scope, file, or declaration.
     *
     * @param includeNested Specifies whether to include nested classes in the count (optional, default is `false`).
     * @return The number of classes in the scope, file, or declaration.
     */
    fun numClasses(includeNested: Boolean = false): Int
}
