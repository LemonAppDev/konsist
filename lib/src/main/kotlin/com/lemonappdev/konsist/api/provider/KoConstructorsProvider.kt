package com.lemonappdev.konsist.api.provider

interface KoConstructorsProvider : KoProvider {
    /**
     * The all primary and secondary constructors of the declaration.
     */
    val allConstructors: List<KoConstructorProvider>
}
