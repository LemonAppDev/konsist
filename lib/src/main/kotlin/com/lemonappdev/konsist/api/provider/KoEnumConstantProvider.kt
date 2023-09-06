package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to enum constants.
 *
 */
interface KoEnumConstantProvider : KoBaseProvider {
    /**
     * List of enum constants.
     */
    val enumConstants: List<KoEnumConstantDeclaration>

    /**
     * The number of enum constants.
     */
    val numEnumConstants: Int

    /**
     * Gets the number of enum constants that satisfies the specified predicate present in the declaration.
     *
     * @param predicate The predicate function to determine if an enum constant satisfies a condition.
     * @return The number of enum constants in the declaration.
     */
    fun countEnumConstants(predicate: (KoEnumConstantDeclaration) -> Boolean): Int

    /**
     * Whether the declaration has enum constants.
     *
     * @param names the names of the enum constants to check.
     * @return `true` if the declaration has enum constants with the specified names (or any constant if [names] is empty),
     * `false` otherwise.
     */
    fun hasEnumConstants(vararg names: String): Boolean
}
