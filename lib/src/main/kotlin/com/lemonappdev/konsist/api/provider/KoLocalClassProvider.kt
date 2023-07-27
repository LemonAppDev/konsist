package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about local classes.
 */
interface KoLocalClassProvider : KoBaseProvider {
    /**
     * The local classes present in the declaration.
     * @return a sequence of [KoClassDeclaration] representing the local classes in the declaration.
     */
    fun localClasses(): Sequence<KoClassDeclaration>

    /**
     * Checks whether the declaration contains a local class with the specified name.
     *
     * @param name The name of the local class to check.
     * @return `true` if the declaration contains a local class with the specified name, `false` otherwise.
     */
    fun containsLocalClass(name: String): Boolean
}
