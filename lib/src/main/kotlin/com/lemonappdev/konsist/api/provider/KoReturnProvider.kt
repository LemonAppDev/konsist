package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import kotlin.reflect.KClass
import kotlin.reflect.KType

/**
 * An interface representing a Kotlin declaration that provides access to the return information.
 */
interface KoReturnProvider : KoBaseProvider {
    /**
     * Explicitly declared return type of the declaration.
     *
     * Konsist does not infer types, so this is `null` when the return type is not declared, e.g. for
     * `fun foo() = 1` or `fun foo() { }`.
     */
    val returnType: KoTypeDeclaration?

    /**
     * Indicates whether a declaration has a non-`Unit` return value.
     *
     * - If a declaration explicitly specifies a return type, [hasReturnValue] returns `true` only if the declared type is not `Unit`.
     *
     * - For declarations without an explicit return type:
     *      1) If the declaration has a block body, [hasReturnValue] always returns `false`.
     *
     *          Example:
     *          ```kotlin
     *          fun sampleFunction() { "some text" } // hasReturnValue == false
     *          ```
     *
     *      2) If the declaration has an expression body, [hasReturnValue] always returns `true`.
     *
     *          Examples:
     *          ```kotlin
     *          fun sampleFunction1() = SampleClass()  // hasReturnValue == true
     *
     *          fun sampleFunction2() = println("some text") // hasReturnValue == true, because Konsist is not able to
     *                                                       // determine value of the "println("some text")" expression
     *          ```
     */
    val hasReturnValue: Boolean

    /**
     * Determines whether the declaration has a specified return type.
     *
     * Only an explicitly declared return type counts, an inferred one does not:
     * ```kotlin
     * fun foo(): Int = 1 // hasReturnType() == true
     * fun foo() = 1      // hasReturnType() == false
     * fun foo() { }      // hasReturnType() == false
     * ```
     * To require an explicit return type for expression-bodied functions combine it with `hasExpressionBody`, e.g.
     * `functions().assertFalse { it.hasExpressionBody && !it.hasReturnType() }`.
     *
     * @param predicate The predicate function used to determine if a declaration return type satisfies a condition.
     * @return `true` if the declaration has the specified return type (or any return type if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasReturnType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Determines whether the declaration has a return type of the specified Kotlin class.
     *
     * Type arguments are not checked, e.g. `List<String>` matches `List::class`.
     *
     * @param kClass The Kotlin class representing the return type to check for.
     * @return `true` if the declaration has a return type matching the specified KClass, `false` otherwise.
     */
    fun hasReturnTypeOf(kClass: KClass<*>): Boolean

    /**
     * Determines whether the declaration has a return type of the specified Kotlin type, including its type arguments.
     * For example, `List<String>` matches `typeOf<List<String>>()`, but not `typeOf<List<Int>>()`.
     * A star projection matches any type argument, e.g. `List<String>` matches `typeOf<List<*>>()`.
     * A nullable [kType] matches only a nullable return type, e.g. `String` does not match `typeOf<String?>()`.
     * A non-nullable [kType] matches both, e.g. `String?` matches `typeOf<String>()`.
     * Nullability of type arguments must match exactly.
     *
     * @param kType The Kotlin type representing the return type to check for.
     * @return `true` if the declaration has a return type matching the specified KType, `false` otherwise.
     */
    fun hasReturnTypeOf(kType: KType): Boolean
}
