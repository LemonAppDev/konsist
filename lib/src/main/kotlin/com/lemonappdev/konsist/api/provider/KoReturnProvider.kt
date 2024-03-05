package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import kotlin.reflect.KClass

/**
 * An interface representing a Kotlin declaration that provides access to the return information.
 */
interface KoReturnProvider : KoBaseProvider {
    /**
     * Return type of the declaration.
     */
    val returnType: KoTypeDeclaration?

    /**
     * Indicates whether a declaration has a non-`Unit` return value.
     *
     * - If a declaration explicitly specifies a return type, [hasReturnValue] returns true only if the declared type is not `Unit`.
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
     *       ```
     */
    val hasReturnValue: Boolean

    /**
     * Determines whatever this declaration has a return type.
     */
    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasReturnType()"))
    val hasReturnType: Boolean

    /**
     * Determines whatever declaration has a specified return type.
     *
     * @param predicate The predicate function used to determine if a declaration return type satisfies a condition.
     * @return `true` if the declaration has the specified return type (or any return type if [predicate] is `null`),
     * `false` otherwise.
     */
    fun hasReturnType(predicate: ((KoTypeDeclaration) -> Boolean)? = null): Boolean

    /**
     * Determines whatever declaration has a return type of the specified Kotlin class.
     *
     * @param kClass The Kotlin class representing the return type to check for.
     * @return `true` if the declaration has a return type matching the specified KClass, `false` otherwise.
     */
    fun hasReturnTypeOf(kClass: KClass<*>): Boolean
}
