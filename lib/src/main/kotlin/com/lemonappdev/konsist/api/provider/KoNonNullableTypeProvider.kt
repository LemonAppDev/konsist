package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * An interface representing a Kotlin declaration that provides access to its non-nullable type information.
 */
interface KoNonNullableTypeProvider : KoBaseProvider {
    /**
     * Type of the declaration.
     */
    val type: KoTypeDeclaration

    /**
     * Determines whether the declaration has the specified type.
     *
     * @param predicate The predicate function used to determine if a declaration type satisfies a condition.
     * @return `true` if the declaration has the specified type, `false` otherwise.
     */
    fun hasType(predicate: (KoTypeDeclaration) -> Boolean): Boolean

    /**
     * Determines whether the declaration has a type of the specified Kotlin class.
     *
     * Type arguments are not checked, e.g. `List<String>` matches `List::class`.
     *
     * @param kClass The Kotlin class representing the type to check for.
     * @return `true` if the declaration has a type matching the specified KClass, `false` otherwise.
     */
    fun hasTypeOf(kClass: KClass<*>): Boolean

    /**
     * Determines whether the declaration has a type of the specified Kotlin type, including its type arguments.
     * For example, `List<String>` matches `typeOf<List<String>>()`, but not `typeOf<List<Int>>()`.
     * A star projection matches any type argument, e.g. `List<String>` matches `typeOf<List<*>>()`.
     * A nullable [kType] matches only a nullable type, e.g. `String` does not match `typeOf<String?>()`.
     * A non-nullable [kType] matches both, e.g. `String?` matches `typeOf<String>()`.
     * Nullability of type arguments must match exactly.
     *
     * @param kType The Kotlin type representing the type to check for.
     * @return `true` if the declaration has a type matching the specified KType, `false` otherwise.
     */
    fun hasTypeOf(kType: KType): Boolean
}
