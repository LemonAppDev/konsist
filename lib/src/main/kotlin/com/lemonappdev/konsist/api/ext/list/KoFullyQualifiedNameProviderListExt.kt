package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider

/**
 * List containing declarations with the fully qualified name.
 *
 * @param name The name to include.
 * @param names The names to include.
 * @return A list containing declarations with the specified fully qualified names.
 */
fun <T : KoFullyQualifiedNameProvider> List<T>.withFullyQualifiedName(
    name: String,
    vararg names: String,
): List<T> = withFullyQualifiedName(listOf(name, *names))

/**
 * List containing declarations with the fully qualified name.
 *
 * @param names The names to include.
 * @return A list containing declarations with the specified fully qualified names.
 */
fun <T : KoFullyQualifiedNameProvider> List<T>.withFullyQualifiedName(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> true
            else -> names.any { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
        }
    }

/**
 * List containing declarations without fully qualified name.
 *
 * @param name The name to exclude.
 * @param names The names to exclude.
 * @return A list containing declarations without the specified fully qualified names.
 */
fun <T : KoFullyQualifiedNameProvider> List<T>.withoutFullyQualifiedName(
    name: String,
    vararg names: String,
): List<T> = withoutFullyQualifiedName(listOf(name, *names))

/**
 * List containing declarations without fully qualified name.
 *
 * @param names The names to exclude.
 * @return A list containing declarations without the specified fully qualified names.
 */
fun <T : KoFullyQualifiedNameProvider> List<T>.withoutFullyQualifiedName(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> false
            else -> names.none { fullyQualifiedName -> it.fullyQualifiedName == fullyQualifiedName }
        }
    }
