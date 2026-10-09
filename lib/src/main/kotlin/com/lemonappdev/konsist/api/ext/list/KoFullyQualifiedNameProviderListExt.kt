package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider

/**
 * List containing declarations with the fully qualified name.
 *
 * @param name The name to include.
 * @param names The names to include.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with the specified fully qualified names.
 */
fun <T : KoFullyQualifiedNameProvider> List<T>.withFullyQualifiedName(
    name: String,
    vararg names: String,
    ignoreCase: Boolean = false,
): List<T> = withFullyQualifiedName(listOf(name, *names), ignoreCase)

/**
 * List containing declarations with the fully qualified name.
 *
 * @param names The names to include.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations with the specified fully qualified names.
 */
fun <T : KoFullyQualifiedNameProvider> List<T>.withFullyQualifiedName(
    names: Collection<String>,
    ignoreCase: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> true
            else -> names.any { fullyQualifiedName -> it.fullyQualifiedName.equals(fullyQualifiedName, ignoreCase) }
        }
    }

/**
 * List containing declarations without fully qualified name.
 *
 * @param name The name to exclude.
 * @param names The names to exclude.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without the specified fully qualified names.
 */
fun <T : KoFullyQualifiedNameProvider> List<T>.withoutFullyQualifiedName(
    name: String,
    vararg names: String,
    ignoreCase: Boolean = false,
): List<T> = withoutFullyQualifiedName(listOf(name, *names), ignoreCase)

/**
 * List containing declarations without fully qualified name.
 *
 * @param names The names to exclude.
 * @param ignoreCase Specifies whether the comparison should ignore case.
 *        If `true`, the prefix comparison will be case-insensitive.
 *        If `false`, the comparison will consider case sensitivity.
 * @return A list containing declarations without the specified fully qualified names.
 */
fun <T : KoFullyQualifiedNameProvider> List<T>.withoutFullyQualifiedName(
    names: Collection<String>,
    ignoreCase: Boolean = false,
): List<T> =
    filter {
        when {
            names.isEmpty() -> false
            else -> names.none { fullyQualifiedName -> it.fullyQualifiedName.equals(fullyQualifiedName, ignoreCase) }
        }
    }
