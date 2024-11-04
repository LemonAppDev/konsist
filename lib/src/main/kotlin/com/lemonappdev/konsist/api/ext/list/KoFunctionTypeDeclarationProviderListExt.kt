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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("parameters"))
val <T : KoFunctionTypeDeclarationProvider> List<T>.parameterTypes: List<KoParameterDeclaration>
    get() = parameters

/**
 * Gets the list of parameters for the function declarations in the list.
 */
val <T : KoFunctionTypeDeclarationProvider> List<T>.parameters: List<KoParameterDeclaration>
    get() =
        mapNotNull { it.parameters }
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
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("withParameter(predicate)"))
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withParameterType(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    withParameter(predicate)

/**
 * Filters the list by excluding functions that match the parameter type predicate.
 *
 * @param predicate A function that defines the condition to be met by the parameter type.
 * @return A list of function declarations that do not match the predicate for the parameter type.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("withoutParameter(predicate)"))
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutParameterType(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    withoutParameter(predicate)

/**
 * Filters the list by requiring that all parameter types match the given predicate.
 *
 * @param predicate A function that defines the condition to be met by all parameter types.
 * @return A list of function declarations where all parameter types match the given predicate.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("withAllParameters(predicate)"))
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withAllParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    withAllParameters(predicate)

/**
 * Filters the list by excluding functions where all parameter types match the given predicate.
 *
 * @param predicate A function that defines the condition to be met by all parameter types.
 * @return A list of function declarations where not all parameter types match the given predicate.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("withoutAllParameters(predicate)"))
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutAllParameterTypes(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    withoutAllParameters(predicate)

/**
 * Filters the list by parameter types as a collection using the given predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of parameter types.
 * @return A list of function declarations that match the predicate for the list of parameter types.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("withParameters(predicate)"))
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withParameterTypes(predicate: (List<KoParameterDeclaration>) -> Boolean): List<T> =
    withParameters(predicate)

/**
 * Filters the list by excluding functions that match the parameter types predicate as a collection.
 *
 * @param predicate A function that defines the condition to be met by the list of parameter types.
 * @return A list of function declarations that do not match the predicate for the list of parameter types.
 */
@Deprecated("Will be removed in version 0.19.0", ReplaceWith("withoutParameters(predicate)"))
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutParameterTypes(predicate: (List<KoParameterDeclaration>) -> Boolean): List<T> =
    withoutParameters(predicate)

/**
 * Filters the list by parameter using the given predicate.
 *
 * @param predicate A function that defines the condition to be met by the parameter.
 * @return A list of function declarations that match the predicate for at least one parameter.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withParameter(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filter { it.hasParameter(predicate) }

/**
 * Filters the list by excluding functions that match the parameter predicate.
 *
 * @param predicate A function that defines the condition to be met by the parameter.
 * @return A list of function declarations that do not match the predicate for the parameter.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutParameter(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filterNot { it.hasParameter(predicate) }

/**
 * Filters the list by requiring that all parameters match the given predicate.
 *
 * @param predicate A function that defines the condition to be met by all parameters.
 * @return A list of function declarations where all parameters match the given predicate.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withAllParameters(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filter { it.hasAllParameters(predicate) }

/**
 * Filters the list by excluding functions where all parameters match the given predicate.
 *
 * @param predicate A function that defines the condition to be met by all parameters.
 * @return A list of function declarations where not all parameters match the given predicate.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutAllParameters(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllParameters(predicate) }

/**
 * Filters the list by parameters as a collection using the given predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of parameters.
 * @return A list of function declarations that match the predicate for the list of parameters.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withParameters(predicate: (List<KoParameterDeclaration>) -> Boolean): List<T> =
    filter { it.parameters?.let { parameters -> predicate(parameters) } == true }

/**
 * Filters the list by excluding functions that match the parameters predicate as a collection.
 *
 * @param predicate A function that defines the condition to be met by the list of parameters.
 * @return A list of function declarations that do not match the predicate for the list of parameters.
 */
fun <T : KoFunctionTypeDeclarationProvider> List<T>.withoutParameters(predicate: (List<KoParameterDeclaration>) -> Boolean): List<T> =
    filterNot { it.parameters?.let { parameters -> predicate(parameters) } == true }
