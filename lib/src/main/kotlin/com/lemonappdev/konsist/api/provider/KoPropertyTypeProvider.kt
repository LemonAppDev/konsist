package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to the type information.
 */
interface KoPropertyTypeProvider : KoBaseProvider {
    /**
     * Declaration type.
     */
    val type: KoTypeDeclaration?

    /**
     * Determines whatever declaration has a type.
     *
     * @param name the type name to check for.
     * @return `true` if the declaration has the specified type, `false` otherwise.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasType { it.name == name }"))
    fun hasType(name: String): Boolean

    /**
     * Determines whatever declaration has a specified type.
     *
     * @param predicate The predicate function used to determine if a declaration type satisfies a condition.
     * @return `true` if the declaration has the specified type (or any type if [predicate] is `null`), `false` otherwise.
     */
    fun hasType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Determines whatever declaration has a type of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the type to check for.
     * @return `true` if the declaration has a type matching the specified KClass, `false` otherwise.
     */
    fun hasTypeOf(kClass: KClass<*>): Boolean
}
