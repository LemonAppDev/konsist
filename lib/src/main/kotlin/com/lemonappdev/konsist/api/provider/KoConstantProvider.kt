package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoEnumConstDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to type aliases.
 *
 */
interface KoConstantProvider : KoBaseProvider {
    /**
     * List of enum constants.
     */
    val constants: List<KoEnumConstDeclaration>

    /**
     * The number of enum constants.
     */
    val numConstants: Int

    /**
     * Whether the declaration has enum constants.
     *
     * @param names the names of the enum constants to check.
     * @return `true` if the declaration has enum constants with the specified names (or any constant if [names] is empty),
     * `false` otherwise.
     */
    fun hasConstants(vararg names: String): Boolean
}
