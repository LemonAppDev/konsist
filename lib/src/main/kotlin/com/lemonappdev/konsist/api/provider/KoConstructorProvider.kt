package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to all primary and secondary constructors.
 */
interface KoConstructorProvider : KoBaseProvider {
    /**
     * The all primary and secondary constructors of the declaration.
     */
    val constructors: List<KoConstructorDeclaration>

    /**
     * The number of constructors.
     */
    val numConstructors: Int
}
