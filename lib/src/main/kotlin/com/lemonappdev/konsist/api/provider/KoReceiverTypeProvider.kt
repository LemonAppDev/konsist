package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * An interface representing a Kotlin declaration that provides access to its receiver type information.
 */
interface KoReceiverTypeProvider : KoBaseProvider {
    /**
     * Receiver type of the declaration.
     *
     * @see KoIsExtensionProvider.isExtension
     */
    val receiverType: KoTypeDeclaration?

    /**
     * Determines whether the declaration has a specified receiver type.
     *
     * @param predicate The predicate function used to determine if a declaration receiver type satisfies a condition.
     * @return `true` if the declaration has the specified receiver type (or any receiver type if [predicate] is `null`), `false` otherwise.
     */
    fun hasReceiverType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Determines whether the declaration has a receiver type of the specified Kotlin class.
     *
     * Type arguments are not checked, e.g. `List<String>` matches `List::class`.
     *
     * @param kClass The Kotlin class representing the receiver type to check for.
     * @return `true` if the declaration has a receiver type matching the specified KClass, `false` otherwise.
     */
    fun hasReceiverTypeOf(kClass: KClass<*>): Boolean

    /**
     * Determines whether the declaration has a receiver type of the specified Kotlin type, including its type arguments.
     * For example, `List<String>` matches `typeOf<List<String>>()`, but not `typeOf<List<Int>>()`.
     * A star projection matches any type argument, e.g. `List<String>` matches `typeOf<List<*>>()`.
     * A nullable [kType] matches only a nullable receiver type, e.g. `String` does not match `typeOf<String?>()`.
     * A non-nullable [kType] matches both, e.g. `String?` matches `typeOf<String>()`.
     * Nullability of type arguments must match exactly.
     *
     * @param kType The Kotlin type representing the receiver type to check for.
     * @return `true` if the declaration has a receiver type matching the specified KType, `false` otherwise.
     */
    fun hasReceiverTypeOf(kType: KType): Boolean
}
