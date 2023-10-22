package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin declaration that provides access to the delegate information.
 */
interface KoDelegateProvider : KoBaseProvider {
    /**
     * Name of the delegate.
     */
    val delegateName: String?

    /**
     * Determines whatever declaration has a delegate with given name.
     *
     * @param delegateName the name of the delegate (optional).
     * @return `true` if the declaration has a delegate matching the specified name (or any delegate if [delegateName] is `null`),
     * `false` otherwise.
     */
    fun hasDelegate(delegateName: String? = null): Boolean
}
