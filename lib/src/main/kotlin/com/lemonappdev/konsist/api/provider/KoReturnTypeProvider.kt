package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to the return type information.
 */
interface KoReturnTypeProvider : KoBaseProvider {
    /**
     * Return type of the declaration.
     */
    val returnType: KoTypeDeclaration?

    /**
     * Whether this declaration has a return type.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasReturnType()"))
    val hasReturnType: Boolean

    /**
     * Whether declaration has a specified return type.
     *
     * @param predicate The predicate function used to determine if a declaration return type satisfies a condition.
     * @return `true` if the declaration has the specified return type (or any return type if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasReturnType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a return type of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the return type to check for.
     * @return `true` if the declaration has a return type matching the specified KClass, `false` otherwise.
     */
    fun hasReturnTypeOf(kClass: KClass<*>): Boolean
}
