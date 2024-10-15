package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to its receiver type information.
 */
interface KoReceiverTypeProvider : KoBaseProvider {
    /**
     * Receiver type of the declaration.
     *
     * @see KoExtensionTypeProvider.isExtension
     */
    val receiverType: KoTypeDeclaration?

    /**
     * Determines whatever declaration has a specified receiver type.
     *
     * @param predicate The predicate function used to determine if a declaration receiver type satisfies a condition.
     * @return `true` if the declaration has the specified receiver type (or any receiver type if [predicate] is `null`), `false` otherwise.
     */
    fun hasReceiverType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Determines whatever declaration has a receiver type of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the receiver type to check for.
     * @return `true` if the declaration has a receiver type matching the specified KClass, `false` otherwise.
     */
    fun hasReceiverTypeOf(kClass: KClass<*>): Boolean
}
