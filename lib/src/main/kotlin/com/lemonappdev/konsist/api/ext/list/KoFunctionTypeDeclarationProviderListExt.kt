package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionTypeDeclarationProvider
import kotlin.reflect.KClass

/**
 * Gets the list of return types for the function declarations in the list.
 */
val <T : KoFunctionTypeDeclarationProvider> List<T>.returnTypes: List<KoTypeDeclaration>
    get() = mapNotNull { it.returnType }

/**
 * Gets the list of parameter types for the function declarations in the list.
 */
val <T : KoFunctionTypeDeclarationProvider> List<T>.parameterTypes: List<KoParameterDeclaration>
    get() = mapNotNull { it.parameterTypes }
        .flatten()

/**
 * Filters the list by return type using the given predicate.
 *
 * @param predicate A function that defines the condition to be met by the return type.
 * @return A list of function declarations that match the predicate for the return type.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withReturnType(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filter { it.returnType?.let { type -> predicate(type) } == true }

/**
 * Filters the list by excluding function declarations that match the return type predicate.
 *
 * @param predicate A function that defines the condition to be met by the return type.
 * @return A list of function declarations that do not match the predicate for the return type.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutReturnType(predicate: (KoTypeDeclaration) -> Boolean): List<T> =
    filterNot { it.returnType?.let { type -> predicate(type) } == true }

/**
 * Filters the list to include only the functions that return a type of the specified class(es).
 *
 * @param kClass The first class to match the return type.
 * @param kClasses Additional classes to match the return type.
 * @return A list of function declarations that have a return type matching any of the provided classes.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withReturnTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withReturnTypeOf(listOf(kClass, *kClasses))

/**
 * Filters the list to include only the functions that return a type of the specified classes.
 *
 * @param kClasses A collection of classes to match the return type.
 * @return A list of function declarations that have a return type matching any of the provided classes.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withReturnTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filter {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasReturnTypeOf(kClass) }
        }
    }

/**
 * Filters the list to exclude functions that return a type of the specified class(es).
 *
 * @param kClass The first class to match the return type.
 * @param kClasses Additional classes to match the return type.
 * @return A list of function declarations that do not have a return type matching any of the provided classes.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutReturnTypeOf(
    kClass: KClass<*>,
    vararg kClasses: KClass<*>,
): List<T> = withoutReturnTypeOf(listOf(kClass, *kClasses))

/**
 * Filters the list to exclude functions that return a type of the specified classes.
 *
 * @param kClasses A collection of classes to match the return type.
 * @return A list of function declarations that do not have a return type matching any of the provided classes.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutReturnTypeOf(kClasses: Collection<KClass<*>>): List<T> =
    filterNot {
        when {
            kClasses.isEmpty() -> true
            else -> kClasses.any { kClass -> it.hasReturnTypeOf(kClass) }
        }
    }

/**
 * Filters the list by parameter type using the given predicate.
 *
 * @param predicate A function that defines the condition to be met by the parameter type.
 * @return A list of function declarations that match the predicate for at least one parameter type.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withParameterType(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filter {
        it.hasParameterType(predicate)
    }

/**
 * Filters the list by excluding functions that match the parameter type predicate.
 *
 * @param predicate A function that defines the condition to be met by the parameter type.
 * @return A list of function declarations that do not match the predicate for the parameter type.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutParameterType(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filterNot { it.hasParameterType(predicate) }

/**
 * Filters the list by requiring that all parameter types match the given predicate.
 *
 * @param predicate A function that defines the condition to be met by all parameter types.
 * @return A list of function declarations where all parameter types match the given predicate.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withAllParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filter {
        it.hasAllParameterTypes(predicate)
    }

/**
 * Filters the list by excluding functions where all parameter types match the given predicate.
 *
 * @param predicate A function that defines the condition to be met by all parameter types.
 * @return A list of function declarations where not all parameter types match the given predicate.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutAllParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllParameterTypes(predicate) }

/**
 * Filters the list by parameter types as a collection using the given predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of parameter types.
 * @return A list of function declarations that match the predicate for the list of parameter types.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withParameterTypes(predicate: (List<KoParameterDeclaration>) -> Boolean): List<T> =
    filter { it.parameterTypes?.let { parameterTypes -> predicate(parameterTypes) } == true }

/**
 * Filters the list by excluding functions that match the parameter types predicate as a collection.
 *
 * @param predicate A function that defines the condition to be met by the list of parameter types.
 * @return A list of function declarations that do not match the predicate for the list of parameter types.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutParameterTypes(predicate: (List<KoParameterDeclaration>) -> Boolean): List<T> =
    filterNot { it.parameterTypes?.let { parameterTypes -> predicate(parameterTypes) } == true }
