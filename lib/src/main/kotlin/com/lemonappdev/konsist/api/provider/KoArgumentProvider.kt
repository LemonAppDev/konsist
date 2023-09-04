package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to arguments.
 *
 */
interface KoArgumentProvider : KoBaseProvider {
    /**
     * List of arguments.
     */
    val arguments: List<KoArgumentDeclaration>

    /**
     * The number of arguments.
     */
    val numArguments: Int

    // Todo: how implement this??? Names? Or other property???
//    /**
//     * Whether the declaration has arguments.
//     *
//     * @param names the names of the arguments to check.
//     * @return `true` if the declaration has arguments with the specified names (or any constant if [names] is empty),
//     * `false` otherwise.
//     */
//    fun hasArguments(vararg names: String): Boolean
}
