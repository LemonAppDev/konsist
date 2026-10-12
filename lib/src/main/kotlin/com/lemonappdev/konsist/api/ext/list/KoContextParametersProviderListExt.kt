package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoContextParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoContextParametersProvider

/**
 * List containing context parameter declarations.
 */
val <T : KoContextParametersProvider> List<T>.contextParameters: List<KoContextParameterDeclaration>
    get() = flatMap { it.contextParameters }

/**
 * List containing declarations with any context parameter.
 *
 * @return A list containing declarations with any context parameter.
 */
fun <T : KoContextParametersProvider> List<T>.withContextParameters(): List<T> = filter { it.hasContextParameters() }

/**
 * List containing declarations with no context parameters.
 *
 * @return A list containing declarations with no context parameters.
 */
fun <T : KoContextParametersProvider> List<T>.withoutContextParameters(): List<T> = filterNot { it.hasContextParameters() }

/**
 * List containing declarations that have at least one context parameter with the specified name(s).
 *
 * @param name The name of the context parameter to include.
 * @param names The names of additional context parameters to include.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with at least one of the specified context parameter(s).
 */
fun <T : KoContextParametersProvider> List<T>.withContextParameterNamed(
    name: String,
    vararg names: String,
    ignoreCase: Boolean = false,
): List<T> = withContextParameterNamed(listOf(name, *names), ignoreCase)

/**
 * List containing declarations that have at least one context parameter with the specified name(s).
 *
 * @param names The names of the context parameters to include.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with at least one of the specified context parameter(s).
 */
fun <T : KoContextParametersProvider> List<T>.withContextParameterNamed(
    names: Collection<String>,
    ignoreCase: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasContextParameters()
            else -> it.hasContextParameterWithName(names, ignoreCase = ignoreCase)
        }
    }

/**
 * List containing declarations without any of the specified context parameters.
 *
 * @param name The name of the context parameter to exclude.
 * @param names The names of additional context parameters to exclude.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without any of the specified context parameters.
 */
fun <T : KoContextParametersProvider> List<T>.withoutContextParameterNamed(
    name: String,
    vararg names: String,
    ignoreCase: Boolean = false,
): List<T> = withoutContextParameterNamed(listOf(name, *names), ignoreCase)

/**
 * List containing declarations without any of the specified context parameters.
 *
 * @param names The names of the context parameters to exclude.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without any of the specified context parameters.
 */
fun <T : KoContextParametersProvider> List<T>.withoutContextParameterNamed(
    names: Collection<String>,
    ignoreCase: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasContextParameters()
            else -> it.hasContextParameterWithName(names, ignoreCase = ignoreCase)
        }
    }

/**
 * List containing declarations that have all specified context parameters.
 *
 * @param name The name of the context parameter to include.
 * @param names The names of additional context parameters to include.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with all specified context parameter(s).
 */
fun <T : KoContextParametersProvider> List<T>.withAllContextParametersNamed(
    name: String,
    vararg names: String,
    ignoreCase: Boolean = false,
): List<T> = withAllContextParametersNamed(listOf(name, *names), ignoreCase)

/**
 * List containing declarations that have all specified context parameters.
 *
 * @param names The names of the context parameters to include.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with all specified context parameter(s).
 */
fun <T : KoContextParametersProvider> List<T>.withAllContextParametersNamed(
    names: Collection<String>,
    ignoreCase: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasContextParameters()
            else -> it.hasContextParametersWithAllNames(names, ignoreCase = ignoreCase)
        }
    }

/**
 * List containing declarations without all specified context parameters.
 *
 * @param name The name of the context parameter to exclude.
 * @param names The names of additional context parameters to exclude.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without all specified context parameter(s).
 */
fun <T : KoContextParametersProvider> List<T>.withoutAllContextParametersNamed(
    name: String,
    vararg names: String,
    ignoreCase: Boolean = false,
): List<T> = withoutAllContextParametersNamed(listOf(name, *names), ignoreCase)

/**
 * List containing declarations without all specified context parameters.
 *
 * @param names The names of the context parameters to exclude.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without all specified context parameter(s).
 */
fun <T : KoContextParametersProvider> List<T>.withoutAllContextParametersNamed(
    names: Collection<String>,
    ignoreCase: Boolean = false,
): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasContextParameters()
            else -> it.hasContextParametersWithAllNames(names, ignoreCase = ignoreCase)
        }
    }

/**
 * List containing declarations that have at least one context parameter satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a context parameter declaration.
 * @return A list containing declarations with at least one context parameter satisfying the predicate.
 */
fun <T : KoContextParametersProvider> List<T>.withContextParameter(predicate: (KoContextParameterDeclaration) -> Boolean): List<T> =
    filter {
        it.hasContextParameter(predicate)
    }

/**
 * List containing declarations that do not have any context parameter satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a context parameter declaration.
 * @return A list containing declarations without any context parameter satisfying the provided predicate.
 */
fun <T : KoContextParametersProvider> List<T>.withoutContextParameter(predicate: (KoContextParameterDeclaration) -> Boolean): List<T> =
    filterNot {
        it.hasContextParameter(predicate)
    }

/**
 * List containing declarations that have all context parameters satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all context parameter declarations.
 * @return A list containing declarations with all context parameters satisfying the predicate.
 */
fun <T : KoContextParametersProvider> List<T>.withAllContextParameters(predicate: (KoContextParameterDeclaration) -> Boolean): List<T> =
    filter {
        it.hasAllContextParameters(predicate)
    }

/**
 * List containing declarations that have at least one context parameter not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all context parameter declarations.
 * @return A list containing declarations that have at least one context parameter not satisfying the provided predicate.
 */
fun <T : KoContextParametersProvider> List<T>.withoutAllContextParameters(predicate: (KoContextParameterDeclaration) -> Boolean): List<T> =
    filterNot {
        it.hasAllContextParameters(predicate)
    }

/**
 * List containing declarations with context parameter declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of context parameter declarations.
 * @return A list containing declarations with context parameter declarations satisfying the predicate.
 */
fun <T : KoContextParametersProvider> List<T>.withContextParameters(predicate: (List<KoContextParameterDeclaration>) -> Boolean): List<T> =
    filter {
        predicate(it.contextParameters)
    }

/**
 * List containing declarations without context parameter declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of context parameter declarations.
 * @return A list containing declarations without context parameter declarations satisfying the predicate.
 */
fun <T : KoContextParametersProvider> List<T>.withoutContextParameters(
    predicate: (List<KoContextParameterDeclaration>) -> Boolean,
): List<T> = filterNot { predicate(it.contextParameters) }
