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

    /**
     * Whether the declaration has arguments.
     *
     * @return `true` if the declaration has any argument, `false` otherwise.
     */
    fun hasArguments(): Boolean
}
