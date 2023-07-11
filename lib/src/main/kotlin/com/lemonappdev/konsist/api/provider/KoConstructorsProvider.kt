package com.lemonappdev.konsist.api.provider

interface KoConstructorsProvider {
    /**
     * The all primary and secondary constructors of the declaration.
     */
    val allConstructors: List<KoConstructorDeclarationProvider>
}
