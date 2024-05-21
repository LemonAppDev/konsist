package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to the nullable type information.
 */
interface KoNullableTypeProvider : KoBaseProvider {
    /**
     * Declaration type.
     */
    val type: KoTypeDeclaration?

    /**
     * Whether declaration has a specified type.
     *
     * @param predicate The predicate function used to determine if a declaration type satisfies a condition.
     * @return `true` if the declaration has the specified type (or any type if [predicate] is `null`), `false` otherwise.
     */
    fun hasType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Whether declaration has a type of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the type to check for.
     * @return `true` if the declaration has a type matching the specified KClass, `false` otherwise.
     */
    fun hasTypeOf(kClass: KClass<*>): Boolean
}
