package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionProvider

/**
 * List containing function declarations.
 *
 * @param includeNested Whether to include nested declarations.
 * @param includeLocal Whether to include local declarations.
 * @return A list containing function declarations.
 */
fun <T : KoFunctionProvider> List<T>.functions(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<KoFunctionDeclaration> = flatMap { it.functions(includeNested, includeLocal) }

/**
 * List containing declarations with any function.
 *
 * @return A list containing declarations with any function.
 */
fun <T : KoFunctionProvider> List<T>.withFunctions(includeNested: Boolean = true, includeLocal: Boolean = true): List<T> =
    filter { it.hasFunctions(includeNested, includeLocal) }

/**
 * List containing declarations with no functions.
 *
 * @return A list containing declarations with no functions.
 */
fun <T : KoFunctionProvider> List<T>.withoutFunctions(includeNested: Boolean = true, includeLocal: Boolean = true): List<T> =
    filterNot { it.hasFunctions(includeNested, includeLocal) }

/**
 * List containing declarations that have at least one function with the specified name(s).
 *
 * @param name The name of the function to include.
 * @param names The names of additional functions to include.
 * @return A list containing declarations with at least one of the specified function(s).
 */
fun <T : KoFunctionProvider> List<T>.withFunctionNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filter {
    it.hasFunctionWithName(name, *names, includeNested = includeNested, includeLocal = includeLocal)
}

/**
 * List containing declarations without any of specified functions.
 *
 * @param name The name of the function to exclude.
 * @param names The names of additional functions to exclude.
 * @return A list containing declarations without any of specified functions.
 */
fun <T : KoFunctionProvider> List<T>.withoutFunctionNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filterNot {
    it.hasFunctionWithName(name, *names, includeNested = includeNested, includeLocal = includeLocal)
}

/**
 * List containing declarations that have all specified functions.
 *
 * @param name The name of the function to include.
 * @param names The name(s) of the function(s) to include.
 * @return A list containing declarations with all specified function(s).
 */
fun <T : KoFunctionProvider> List<T>.withAllFunctionsNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> = filter {
    it.hasFunctionsWithAllNames(name, *names, includeNested = includeNested, includeLocal = includeLocal)
}

/**
 * List containing declarations without all specified functions.
 *
 * @param name The name of the function to exclude.
 * @param names The name(s) of the function(s) to exclude.
 * @return A list containing declarations without all specified function(s).
 */
fun <T : KoFunctionProvider> List<T>.withoutAllFunctionsNamed(
    name: String,
    vararg names: String,
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
): List<T> =
    filterNot {
        it.hasFunctionsWithAllNames(name, *names, includeNested = includeNested, includeLocal = includeLocal)
    }

/**
 * List containing declarations that have at least one function satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a function declaration.
 * @return A list containing declarations with at least one function satisfying the predicate.
 */
fun <T : KoFunctionProvider> List<T>.withFunction(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoFunctionDeclaration) -> Boolean,
): List<T> = filter {
    it.hasFunction(includeNested, includeLocal, predicate)
}

/**
 * List containing declarations that not have function satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a function declaration.
 * @return A list containing declarations without function satisfying the provided predicate.
 */
fun <T : KoFunctionProvider> List<T>.withoutFunction(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoFunctionDeclaration) -> Boolean,
): List<T> =
    filterNot { it.hasFunction(includeNested, includeLocal, predicate) }

/**
 * List containing declarations that have all functions satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all function declarations.
 * @return A filtered list containing declarations with all functions satisfying the predicate.
 */
fun <T : KoFunctionProvider> List<T>.withAllFunctions(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoFunctionDeclaration) -> Boolean,
): List<T> =
    filter {
        it.hasAllFunctions(includeNested, includeLocal, predicate)
    }

/**
 * List containing declarations that have at least one function not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all function declarations.
 * @return A list containing declarations that have at least one function not satisfying the provided predicate.
 */
fun <T : KoFunctionProvider> List<T>.withoutAllFunctions(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (KoFunctionDeclaration) -> Boolean,
): List<T> =
    filterNot { it.hasAllFunctions(includeNested, includeLocal, predicate) }

/**
 * List containing declarations with function declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of function declarations.
 * @return A list containing declarations with function declarations satisfying the predicate.
 */
fun <T : KoFunctionProvider> List<T>.withFunctions(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (List<KoFunctionDeclaration>) -> Boolean,
): List<T> =
    filter { predicate(it.functions(includeNested, includeLocal)) }

/**
 * List containing declarations without function declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of function declarations.
 * @return A list containing declarations without function declarations satisfying the predicate.
 */
fun <T : KoFunctionProvider> List<T>.withoutFunctions(
    includeNested: Boolean = true,
    includeLocal: Boolean = true,
    predicate: (List<KoFunctionDeclaration>) -> Boolean,
): List<T> =
    filterNot { predicate(it.functions(includeNested, includeLocal)) }
