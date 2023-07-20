package com.lemonappdev.konsist.api.provider

interface KoConstructorsProvider : KoBaseProvider {
    /**
     * The all primary and secondary constructors of the declaration.
     */
    val allConstructors: List<KoConstructorProvider>
}
