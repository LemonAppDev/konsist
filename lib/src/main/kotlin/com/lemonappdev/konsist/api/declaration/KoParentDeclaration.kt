package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoParentProvider

/**
 * Represents a Kotlin parent declaration.
 */
interface KoParentDeclaration :
    KoBaseDeclaration,
    KoParentProvider {
    /**
     * Name of the delegate.
     */
    val delegateName: String?

    /**
     * Whatever declaration has a delegate with given name.
     *
     * @param delegateName the name of the delegate (optional).
     * @return `true` if the declaration has a delegate matching the specified name (or any delegate if [delegateName] is `null`),
     * `false` otherwise.
     */
    fun hasDelegate(delegateName: String? = null): Boolean
}
