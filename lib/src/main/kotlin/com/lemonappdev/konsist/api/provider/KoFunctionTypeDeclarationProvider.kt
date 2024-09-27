package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import kotlin.reflect.KClass

/**
 * Provides access to function type declarations, including details about parameters and return types.
 */
interface KoFunctionTypeDeclarationProvider : KoBaseProvider {
    /**
     * Represents the parameters of the function type.
     */
    val parameterTypes: List<KoParameterDeclaration>

    /**
     * The number of parameters for the function type.
     */
    val numParameterTypes: Int

    /**
     * Represents the return type of the function type.
     */
    val returnType: KoTypeDeclaration

    /**
     * Counts the number of parameter types that match the given predicate.
     *
     * @param predicate A function that evaluates each parameter declaration.
     * @return The number of parameters that match the predicate.
     */
    fun countParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): Int

    /**
     * Checks if any parameter type matches the given predicate.
     *
     * @param predicate A function that evaluates each parameter declaration.
     * @return `true` if any parameter type matches the predicate, otherwise `false`.
     */
    fun hasParameterType(predicate: (KoParameterDeclaration) -> Boolean): Boolean

    /**
     * Checks if all parameter types match the given predicate.
     *
     * @param predicate A function that evaluates each parameter declaration.
     * @return `true` if all parameter types match the predicate, otherwise `false`.
     */
    fun hasAllParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): Boolean

    /**
     * Checks if the return type matches the given predicate.
     *
     * @param predicate A function that evaluates the return type declaration.
     * @return `true` if the return type matches the predicate, otherwise `false`.
     */
    fun hasReturnType(predicate: (KoTypeDeclaration) -> Boolean): Boolean

    /**
     * Checks if the return type matches the specified class.
     *
     * @param kClass The class to check the return type against.
     * @return `true` if the return type matches the specified class, otherwise `false`.
     */
    fun hasReturnTypeOf(kClass: KClass<*>): Boolean
}