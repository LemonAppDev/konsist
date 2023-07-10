package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration

interface KoConstructorsProvider {
    /**
     * The all primary and secondary constructors of the declaration.
     */
    val allConstructors: List<KoConstructorDeclaration>
}
