package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration

/**
 * An interface representing a Kotlin declaration that provides access to its receiver type information.
 */
interface KoReceiverTypeProvider : KoBaseProvider {
    /**
     * Receiver type of the declaration.
     */
    val receiverType: KoTypeDeclaration?

    /**
     * Whether this declaration has receiver type.
     *
     * @param name the receiver type to check.
     * @return `true` if the declaration has receiver type with the specified name (or any receiver type if [name] is null),
     * `false` otherwise.
     */
    fun hasReceiverType(name: String? = null): Boolean
}
