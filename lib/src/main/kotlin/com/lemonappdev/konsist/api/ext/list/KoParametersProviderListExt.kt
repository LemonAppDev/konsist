package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoImportProvider
import com.lemonappdev.konsist.api.provider.KoParametersProvider

/**
 * List containing parameter declarations.
 */
val <T : KoParametersProvider> List<T>.parameters: List<KoParameterDeclaration>
    get() = flatMap { it.parameters }

/**
 * List containing elements with any parameter.
 *
 * @return A list containing elements with any parameter.
 */
fun <T : KoParametersProvider> List<T>.withParameters(): List<T> = filter { it.parameters.isNotEmpty() }

/**
 * A list containing elements with specified parameters.
 *
 * @param name The name of the parameters to include.
 * @param names The names of the parameters to include.
 * @return A list containing elements with all the specified parameters.
 */
fun <T : KoParametersProvider> List<T>.withAllParameters(name: String, vararg names: String): List<T> = filter {
    it.hasParameterNamed(name) && names.all { name -> it.hasParameterNamed(name) }
}

/**
 * List containing elements with some parameters.
 *
 * @param name The name of the parameters to include.
 * @param names The names of the parameters to include.
 * @return A list containing elements with at least one of the specified parameters.
 */
fun <T : KoParametersProvider> List<T>.withSomeParameters(name: String, vararg names: String): List<T> = filter {
    it.hasParameterNamed(name) || names.any { name -> it.hasParameterNamed(name) }
}

/**
 * List containing elements with no parameters.
 *
 * @return A list containing elements with no parameters.
 */
fun <T : KoParametersProvider> List<T>.withoutParameters(): List<T> = filter { it.parameters.isEmpty() }

/**
 * List containing elements without all specified parameters.
 *
 * @param name The name of the parameter to exclude.
 * @param names The names of the parameters to exclude.
 * @return A list containing elements without all the specified parameters.
 */
fun <T : KoParametersProvider> List<T>.withoutAllParameters(name: String, vararg names: String): List<T> = filter {
    !it.hasParameterNamed(name) && names.none { name -> it.hasParameterNamed(name) }
}

/**
 * List containing elements without some parameters.
 *
 * @param name The name of the parameters to exclude.
 * @param names The names of the parameters to exclude.
 * @return A list containing elements without at least one of the specified parameters.
 */
fun <T : KoParametersProvider> List<T>.withoutSomeParameters(name: String, vararg names: String): List<T> = filter {
    !it.hasParameterNamed(name) && if (names.isNotEmpty()) {
        names.any { name -> !it.hasParameterNamed(name) }
    } else {
        true
    }
}
