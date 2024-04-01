package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalFunctionProvider

/**
 * List containing local function declarations.
 */
val <T : KoLocalFunctionProvider> List<T>.localFunctions: List<KoFunctionDeclaration>
    get() = flatMap { it.localFunctions }

/**
 * List containing declarations with any local function.
 *
 * @return A list containing declarations with any local function.
 */
fun <T : KoLocalFunctionProvider> List<T>.withLocalFunctions(): List<T> = filter { it.hasLocalFunctions() }

/**
 * List containing declarations with no local functions.
 *
 * @return A list containing declarations with no local functions.
 */
fun <T : KoLocalFunctionProvider> List<T>.withoutLocalFunctions(): List<T> = filterNot { it.hasLocalFunctions() }

/**
 * List containing declarations that have at least one local function with the specified name(s).
 *
 * @param name The name of the local function to include.
 * @param names The names of additional local functions to include.
 * @return A list containing declarations with at least one of the specified local function(s).
 */
fun <T : KoLocalFunctionProvider> List<T>.withLocalFunctionNamed(
    name: String,
    vararg names: String,
): List<T> = withLocalFunctionNamed(listOf(name, *names))

/**
 * List containing declarations that have at least one local function with the specified name(s).
 *
 * @param names The names of additional local functions to include.
 * @return A list containing declarations with at least one of the specified local function(s).
 */
fun <T : KoLocalFunctionProvider> List<T>.withLocalFunctionNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasLocalFunctions()
            else -> it.hasLocalFunctionWithName(names)
        }
    }

/**
 * List containing declarations without any of specified local functions.
 *
 * @param name The name of the local function to exclude.
 * @param names The names of additional local functions to exclude.
 * @return A list containing declarations without any of specified local functions.
 */
fun <T : KoLocalFunctionProvider> List<T>.withoutLocalFunctionNamed(
    name: String,
    vararg names: String,
): List<T> = withoutLocalFunctionNamed(listOf(name, *names))

/**
 * List containing declarations without any of specified local functions.
 *
 * @param names The names of additional local functions to exclude.
 * @return A list containing declarations without any of specified local functions.
 */
fun <T : KoLocalFunctionProvider> List<T>.withoutLocalFunctionNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasLocalFunctions()
            else -> it.hasLocalFunctionWithName(names)
        }
    }

/**
 * List containing declarations that have all specified local functions.
 *
 * @param name The name of the local function to include.
 * @param names The name(s) of the local function(s) to include.
 * @return A list containing declarations with all specified local function(s).
 */
fun <T : KoLocalFunctionProvider> List<T>.withAllLocalFunctionsNamed(
    name: String,
    vararg names: String,
): List<T> = withAllLocalFunctionsNamed(listOf(name, *names))

/**
 * List containing declarations that have all specified local functions.
 *
 * @param names The name(s) of the local function(s) to include.
 * @return A list containing declarations with all specified local function(s).
 */
fun <T : KoLocalFunctionProvider> List<T>.withAllLocalFunctionsNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasLocalFunctions()
            else -> it.hasLocalFunctionsWithAllNames(names)
        }
    }

/**
 * List containing declarations without all specified local functions.
 *
 * @param name The name of the local function to exclude.
 * @param names The name(s) of the local function(s) to exclude.
 * @return A list containing declarations without all specified local function(s).
 */
fun <T : KoLocalFunctionProvider> List<T>.withoutAllLocalFunctionsNamed(
    name: String,
    vararg names: String,
): List<T> = withoutAllLocalFunctionsNamed(listOf(name, *names))

/**
 * List containing declarations without all specified local functions.
 *
 * @param names The name(s) of the local function(s) to exclude.
 * @return A list containing declarations without all specified local function(s).
 */
fun <T : KoLocalFunctionProvider> List<T>.withoutAllLocalFunctionsNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasLocalFunctions()
            else -> it.hasLocalFunctionsWithAllNames(names)
        }
    }

/**
 * List containing declarations that have at least one local function satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a local function declaration.
 * @return A list containing declarations with at least one local function satisfying the predicate.
 */
fun <T : KoLocalFunctionProvider> List<T>.withLocalFunction(predicate: (KoFunctionDeclaration) -> Boolean): List<T> =
    filter { it.hasLocalFunction(predicate) }

/**
 * List containing declarations that not have local function satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a local function declaration.
 * @return A list containing declarations without local function satisfying the provided predicate.
 */
fun <T : KoLocalFunctionProvider> List<T>.withoutLocalFunction(predicate: (KoFunctionDeclaration) -> Boolean): List<T> =
    filterNot { it.hasLocalFunction(predicate) }

/**
 * List containing declarations that have all local functions satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all local function declarations.
 * @return A filtered list containing declarations with all local functions satisfying the predicate.
 */
fun <T : KoLocalFunctionProvider> List<T>.withAllLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): List<T> =
    filter { it.hasAllLocalFunctions(predicate) }

/**
 * List containing declarations that have at least one local function not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all local function declarations.
 * @return A list containing declarations that have at least one local function not satisfying the provided predicate.
 */
fun <T : KoLocalFunctionProvider> List<T>.withoutAllLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllLocalFunctions(predicate) }

/**
 * List containing declarations with local function declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of local function declarations.
 * @return A list containing declarations with local function declarations satisfying the predicate.
 */
fun <T : KoLocalFunctionProvider> List<T>.withLocalFunctions(predicate: (List<KoFunctionDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.localFunctions) }

/**
 * List containing declarations without local function declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of local function declarations.
 * @return A list containing declarations without local function declarations satisfying the predicate.
 */
fun <T : KoLocalFunctionProvider> List<T>.withoutLocalFunctions(predicate: (List<KoFunctionDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.localFunctions) }
