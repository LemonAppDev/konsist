package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to all primary and secondary constructors.
 */
interface KoAllConstructorsProvider : KoBaseProvider {
    /**
     * The all primary and secondary constructors of the declaration.
     */
    val allConstructors: Sequence<KoConstructorProvider>
}
