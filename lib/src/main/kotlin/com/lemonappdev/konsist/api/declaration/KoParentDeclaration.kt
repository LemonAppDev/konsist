package com.lemonappdev.konsist.api.declaration

/**
 * Represents a Kotlin parent declaration.
 */
interface KoParentDeclaration : KoNamedDeclaration {
    /**
     * Name of the delegate.
     */
    val delegateName: String?

    /**
     * Whatever declaration has a delegate with given name.
     */
    fun hasDelegate(delegateName: String? = null): Boolean
}
