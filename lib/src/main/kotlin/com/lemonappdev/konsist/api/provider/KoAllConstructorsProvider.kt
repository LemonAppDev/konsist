package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to all primary and secondary constructors.
 */
interface KoAllConstructorsProvider : KoBaseProvider {
    /**
     * The all primary and secondary constructors of the declaration.
     */
    val allConstructors: Sequence<KoConstructorDeclaration>
}
