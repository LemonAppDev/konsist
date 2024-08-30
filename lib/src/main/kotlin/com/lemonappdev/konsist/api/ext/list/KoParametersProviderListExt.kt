package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoParametersProvider

/**
 * List containing parameter declarations.
 */
val <T : KoParametersProvider> List<T>.parameters: List<KoParameterDeclaration>
    get() = flatMap { it.parameters }

/**
 * List containing declarations with any parameter.
 *
 * @return A list containing declarations with any parameter.
 */
fun <T : KoParametersProvider> List<T>.withParameters(): List<T> = filter { it.hasParameters() }

/**
 * List containing declarations with no parameters.
 *
 * @return A list containing declarations with no parameters.
 */
fun <T : KoParametersProvider> List<T>.withoutParameters(): List<T> = filterNot { it.hasParameters() }

/**
 * List containing declarations that have at least one parameter with the specified name(s).
 *
 * @param name The name of the parameter to include.
 * @param names The names of additional parameters to include.
 * @return A list containing declarations with at least one of the specified parameter(s).
 */
fun <T : KoParametersProvider> List<T>.withParameterNamed(
    name: String,
    vararg names: String,
): List<T> = withParameterNamed(listOf(name, *names))

/**
 * List containing declarations that have at least one parameter with the specified name(s).
 *
 * @param names The names of additional parameters to include.
 * @return A list containing declarations with at least one of the specified parameter(s).
 */
fun <T : KoParametersProvider> List<T>.withParameterNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasParameters()
            else -> it.hasParameterWithName(names)
        }
    }

/**
 * List containing declarations without any of specified parameters.
 *
 * @param name The name of the parameter to exclude.
 * @param names The names of additional parameters to exclude.
 * @return A list containing declarations without any of specified parameters.
 */
fun <T : KoParametersProvider> List<T>.withoutParameterNamed(
    name: String,
    vararg names: String,
): List<T> = withoutParameterNamed(listOf(name, *names))

/**
 * List containing declarations without any of specified parameters.
 *
 * @param names The names of additional parameters to exclude.
 * @return A list containing declarations without any of specified parameters.
 */
fun <T : KoParametersProvider> List<T>.withoutParameterNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasParameters()
            else -> it.hasParameterWithName(names)
        }
    }

/**
 * List containing declarations that have all specified parameters.
 *
 * @param name The name of the parameter to include.
 * @param names The name(s) of the parameter(s) to include.
 * @return A list containing declarations with all specified parameter(s).
 */
fun <T : KoParametersProvider> List<T>.withAllParametersNamed(
    name: String,
    vararg names: String,
): List<T> = withAllParametersNamed(listOf(name, *names))

/**
 * List containing declarations that have all specified parameters.
 *
 * @param names The name(s) of the parameter(s) to include.
 * @return A list containing declarations with all specified parameter(s).
 */
fun <T : KoParametersProvider> List<T>.withAllParametersNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasParameters()
            else -> it.hasParametersWithAllNames(names)
        }
    }

/**
 * List containing declarations without all specified parameters.
 *
 * @param name The name of the parameter to exclude.
 * @param names The name(s) of the parameter(s) to exclude.
 * @return A list containing declarations without all specified parameter(s).
 */
fun <T : KoParametersProvider> List<T>.withoutAllParametersNamed(
    name: String,
    vararg names: String,
): List<T> = withoutAllParametersNamed(listOf(name, *names))

/**
 * List containing declarations without all specified parameters.
 *
 * @param names The name(s) of the parameter(s) to exclude.
 * @return A list containing declarations without all specified parameter(s).
 */
fun <T : KoParametersProvider> List<T>.withoutAllParametersNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasParameters()
            else -> it.hasParametersWithAllNames(names)
        }
    }

/**
 * List containing declarations that have at least one parameter satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a parameter declaration.
 * @return A list containing declarations with at least one parameter satisfying the predicate.
 */
fun <T : KoParametersProvider> List<T>.withParameter(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filter {
        it.hasParameter(predicate)
    }

/**
 * List containing declarations that not have parameter satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a parameter declaration.
 * @return A list containing declarations without parameter satisfying the provided predicate.
 */
fun <T : KoParametersProvider> List<T>.withoutParameter(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filterNot {
        it.hasParameter(predicate)
    }

/**
 * List containing declarations that have all parameters satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all parameter declarations.
 * @return A filtered list containing declarations with all parameters satisfying the predicate.
 */
fun <T : KoParametersProvider> List<T>.withAllParameters(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filter {
        it.hasAllParameters(predicate)
    }

/**
 * List containing declarations that have at least one parameter not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all parameter declarations.
 * @return A list containing declarations that have at least one parameter not satisfying the provided predicate.
 */
fun <T : KoParametersProvider> List<T>.withoutAllParameters(predicate: (KoParameterDeclaration) -> Boolean): List<T> =
    filterNot {
        it.hasAllParameters(predicate)
    }

/**
 * List containing declarations with parameter declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of parameter declarations.
 * @return A list containing declarations with parameter declarations satisfying the predicate.
 */
fun <T : KoParametersProvider> List<T>.withParameters(predicate: (List<KoParameterDeclaration>) -> Boolean): List<T> =
    filter {
        predicate(it.parameters)
    }

/**
 * List containing declarations without parameter declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of parameter declarations.
 * @return A list containing declarations without parameter declarations satisfying the predicate.
 */
fun <T : KoParametersProvider> List<T>.withoutParameters(predicate: (List<KoParameterDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.parameters) }

/**
 * A list containing declarations with specified parameters.
 *
 * @param name The name of the parameters to include.
 * @param names The names of the parameters to include.
 * @return A list containing declarations with all the specified parameters.
 */
@Deprecated(
    """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `withParameterNamed`, otherwise with `withAllParametersNamed`.
            """,
    ReplaceWith("withParameterNamed/withAllParametersNamed"),
)
fun <T : KoParametersProvider> List<T>.withAllParameters(
    name: String,
    vararg names: String,
): List<T> =
    filter {
        it.hasParameterNamed(name) && names.all { name -> it.hasParameterNamed(name) }
    }

/**
 * List containing declarations with some parameters.
 *
 * @param name The name of the parameters to include.
 * @param names The names of the parameters to include.
 * @return A list containing declarations with at least one of the specified parameters.
 */
@Deprecated("Will be removed in v0.16.0.", ReplaceWith("withParameterNamed(*names"))
fun <T : KoParametersProvider> List<T>.withSomeParameters(
    name: String,
    vararg names: String,
): List<T> =
    filter {
        it.hasParameterNamed(name) || names.any { name -> it.hasParameterNamed(name) }
    }

/**
 * List containing declarations without all specified parameters.
 *
 * @param name The name of the parameter to exclude.
 * @param names The names of the parameters to exclude.
 * @return A list containing declarations without all the specified parameters.
 */
@Deprecated(
    """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `withoutParameterNamed`, otherwise with `withoutAllParametersNamed`.
            """,
    ReplaceWith("withoutParameterNamed/withoutAllParametersNamed"),
)
fun <T : KoParametersProvider> List<T>.withoutAllParameters(
    name: String,
    vararg names: String,
): List<T> =
    filter {
        !it.hasParameterNamed(name) && names.none { name -> it.hasParameterNamed(name) }
    }

/**
 * List containing declarations without some parameters.
 *
 * @param name The name of the parameters to exclude.
 * @param names The names of the parameters to exclude.
 * @return A list containing declarations without at least one of the specified parameters.
 */
@Deprecated("Will be removed in v0.16.0.", ReplaceWith("withoutParameterNamed(*names"))
fun <T : KoParametersProvider> List<T>.withoutSomeParameters(
    name: String,
    vararg names: String,
): List<T> =
    filter {
        val missesAtLeastOneParameter =
            if (names.isNotEmpty()) {
                names.any { name -> !it.hasParameterNamed(name) }
            } else {
                true
            }

        !it.hasParameterNamed(name) && missesAtLeastOneParameter
    }
