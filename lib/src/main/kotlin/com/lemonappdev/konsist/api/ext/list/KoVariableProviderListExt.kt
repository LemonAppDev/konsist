package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoVariableDeclaration
import com.lemonappdev.konsist.api.provider.KoVariableProvider

/**
 * List containing variable declarations.
 */
val <T : KoVariableProvider> List<T>.variables: List<KoVariableDeclaration>
    get() = flatMap { it.variables }

/**
 * List containing declarations with any variable.
 *
 * @return A list containing declarations with any variable.
 */
fun <T : KoVariableProvider> List<T>.withVariables(): List<T> = filter { it.hasVariables() }

/**
 * List containing declarations with no variables.
 *
 * @return A list containing declarations with no variables.
 */
fun <T : KoVariableProvider> List<T>.withoutVariables(): List<T> = filterNot { it.hasVariables() }

/**
 * List containing declarations that have at least one variable with the specified name(s).
 *
 * @param name The name of the variable to include.
 * @param names The names of additional variables to include.
 * @return A list containing declarations with at least one of the specified variables.
 */
fun <T : KoVariableProvider> List<T>.withVariableNamed(
    name: String,
    vararg names: String,
): List<T> =
    filter {
        it.hasVariableWithName(name, *names)
    }

/**
 * List containing declarations that have at least one variable with the specified name(s).
 *
 * @param names The names of additional variables to include.
 * @return A list containing declarations with at least one of the specified variable(s).
 */
fun <T : KoVariableProvider> List<T>.withVariableNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasVariables()
            else -> it.hasVariableWithName(names.first(), *names.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations without any of specified variables.
 *
 * @param name The name of the variable to exclude.
 * @param names The names of additional variables to exclude.
 * @return A list containing declarations without any of specified variables.
 */
fun <T : KoVariableProvider> List<T>.withoutVariableNamed(
    name: String,
    vararg names: String,
): List<T> =
    filterNot {
        it.hasVariableWithName(name, *names)
    }

/**
 * List containing declarations without any of specified variables.
 *
 * @param names The names of additional variables to exclude.
 * @return A list containing declarations without any of specified variables.
 */
fun <T : KoVariableProvider> List<T>.withoutVariableNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasVariables()
            else -> it.hasVariableWithName(names.first(), *names.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations that have all specified variables.
 *
 * @param name The name of the variable to include.
 * @param names The name(s) of the variables to include.
 * @return A list containing declarations with all specified variables.
 */
fun <T : KoVariableProvider> List<T>.withAllVariablesNamed(
    name: String,
    vararg names: String,
): List<T> =
    filter {
        it.hasVariablesWithAllNames(name, *names)
    }

/**
 * List containing declarations that have all specified variables.
 *
 * @param names The name(s) of the variable(s) to include.
 * @return A list containing declarations with all specified variable(s).
 */
fun <T : KoVariableProvider> List<T>.withAllVariablesNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasVariables()
            else -> it.hasVariablesWithAllNames(names.first(), *names.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations without all specified variables.
 *
 * @param name The name of the variable to exclude.
 * @param names The name(s) of the variables to exclude.
 * @return A list containing declarations without all specified variables.
 */
fun <T : KoVariableProvider> List<T>.withoutAllVariablesNamed(
    name: String,
    vararg names: String,
): List<T> = filterNot { it.hasVariablesWithAllNames(name, *names) }

/**
 * List containing declarations without all specified variables.
 *
 * @param names The name(s) of the variable(s) to exclude.
 * @return A list containing declarations without all specified variable(s).
 */
fun <T : KoVariableProvider> List<T>.withoutAllVariablesNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasVariables()
            else -> it.hasVariablesWithAllNames(names.first(), *names.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations that have at least one variable satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a variable declaration.
 * @return A list containing declarations with at least one variable satisfying the predicate.
 */
fun <T : KoVariableProvider> List<T>.withVariable(predicate: (KoVariableDeclaration) -> Boolean): List<T> =
    filter {
        it.hasVariable(predicate)
    }

/**
 * List containing declarations that not have variable satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a variable declaration.
 * @return A list containing declarations without variable satisfying the provided predicate.
 */
fun <T : KoVariableProvider> List<T>.withoutVariable(predicate: (KoVariableDeclaration) -> Boolean): List<T> =
    filterNot { it.hasVariable(predicate) }

/**
 * List containing declarations that have all variables satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all variable declarations.
 * @return A filtered list containing declarations with all variables satisfying the predicate.
 */
fun <T : KoVariableProvider> List<T>.withAllVariables(predicate: (KoVariableDeclaration) -> Boolean): List<T> =
    filter { it.hasAllVariables(predicate) }

/**
 * List containing declarations that have at least one variable not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all variable declarations.
 * @return A list containing declarations that have at least one variable not satisfying the provided predicate.
 */
fun <T : KoVariableProvider> List<T>.withoutAllVariables(predicate: (KoVariableDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllVariables(predicate) }

/**
 * List containing declarations with variable declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of variable declarations.
 * @return A list containing declarations with variable declarations satisfying the predicate.
 */
fun <T : KoVariableProvider> List<T>.withVariables(predicate: (List<KoVariableDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.variables) }

/**
 * List containing declarations without variable declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of variable declarations.
 * @return A list containing declarations without variable declarations satisfying the predicate.
 */
fun <T : KoVariableProvider> List<T>.withoutVariables(predicate: (List<KoVariableDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.variables) }
