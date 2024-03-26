package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeAliasProvider

/**
 * List containing type alias declarations.
 */
val <T : KoTypeAliasProvider> List<T>.typeAliases: List<KoTypeAliasDeclaration>
    get() = flatMap { it.typeAliases }

/**
 * List containing declarations with any type alias.
 *
 * @return A list containing declarations with any type alias.
 */
fun <T : KoTypeAliasProvider> List<T>.withTypeAliases(): List<T> = filter { it.hasTypeAliases() }

/**
 * List containing declarations with no type aliases.
 *
 * @return A list containing declarations with no type aliases.
 */
fun <T : KoTypeAliasProvider> List<T>.withoutTypeAliases(): List<T> = filterNot { it.hasTypeAliases() }

/**
 * List containing declarations that have at least one type alias with the specified name(s).
 *
 * @param name The name of the type alias to include.
 * @param names The names of additional type aliases to include.
 * @return A list containing declarations with at least one of the specified type alias(s).
 */
fun <T : KoTypeAliasProvider> List<T>.withTypeAliasNamed(
    name: String,
    vararg names: String,
): List<T> = withTypeAliasNamed(listOf(name, *names))

/**
 * List containing declarations that have at least one type alias with the specified name(s).
 *
 * @param names The names of additional type aliases to include.
 * @return A list containing declarations with at least one of the specified type alias(s).
 */
fun <T : KoTypeAliasProvider> List<T>.withTypeAliasNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasTypeAliases()
            else -> it.hasTypeAliasWithName(names.first(), *names.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations without any of specified type aliases.
 *
 * @param name The name of the type alias to exclude.
 * @param names The names of additional type aliases to exclude.
 * @return A list containing declarations without any of specified type aliases.
 */
fun <T : KoTypeAliasProvider> List<T>.withoutTypeAliasNamed(
    name: String,
    vararg names: String,
): List<T> = withoutTypeAliasNamed(listOf(name, *names))

/**
 * List containing declarations without any of specified type aliases.
 *
 * @param names The names of additional type aliases to exclude.
 * @return A list containing declarations without any of specified type aliases.
 */
fun <T : KoTypeAliasProvider> List<T>.withoutTypeAliasNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasTypeAliases()
            else -> it.hasTypeAliasWithName(names.first(), *names.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations that have all specified type aliases.
 *
 * @param name The name of the type alias to include.
 * @param names The name(s) of the type alias(s) to include.
 * @return A list containing declarations with all specified type alias(s).
 */
fun <T : KoTypeAliasProvider> List<T>.withAllTypeAliasesNamed(
    name: String,
    vararg names: String,
): List<T> = withAllTypeAliasesNamed(listOf(name, *names))

/**
 * List containing declarations that have all specified type aliases.
 *
 * @param names The name(s) of the type alias(s) to include.
 * @return A list containing declarations with all specified type alias(s).
 */
fun <T : KoTypeAliasProvider> List<T>.withAllTypeAliasesNamed(names: Collection<String>): List<T> =
    filter {
        when {
            names.isEmpty() -> it.hasTypeAliases()
            else -> it.hasTypeAliasesWithAllNames(names.first(), *names.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations without all specified type aliases.
 *
 * @param name The name of the type alias to exclude.
 * @param names The name(s) of the type alias(s) to exclude.
 * @return A list containing declarations without all specified type alias(s).
 */
fun <T : KoTypeAliasProvider> List<T>.withoutAllTypeAliasesNamed(
    name: String,
    vararg names: String,
): List<T> = withoutAllTypeAliasesNamed(listOf(name, *names))

/**
 * List containing declarations without all specified type aliases.
 *
 * @param names The name(s) of the type alias(s) to exclude.
 * @return A list containing declarations without all specified type alias(s).
 */
fun <T : KoTypeAliasProvider> List<T>.withoutAllTypeAliasesNamed(names: Collection<String>): List<T> =
    filterNot {
        when {
            names.isEmpty() -> it.hasTypeAliases()
            else -> it.hasTypeAliasesWithAllNames(names.first(), *names.drop(1).toTypedArray())
        }
    }

/**
 * List containing declarations that have at least one type alias satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a type alias declaration.
 * @return A list containing declarations with at least one type alias satisfying the predicate.
 */
fun <T : KoTypeAliasProvider> List<T>.withTypeAlias(predicate: (KoTypeAliasDeclaration) -> Boolean): List<T> =
    filter { it.hasTypeAlias(predicate) }

/**
 * List containing declarations that not have type alias satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a type alias declaration.
 * @return A list containing declarations without type alias satisfying the provided predicate.
 */
fun <T : KoTypeAliasProvider> List<T>.withoutTypeAlias(predicate: (KoTypeAliasDeclaration) -> Boolean): List<T> =
    filterNot { it.hasTypeAlias(predicate) }

/**
 * List containing declarations that have all type aliases satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all type alias declarations.
 * @return A filtered list containing declarations with all type aliases satisfying the predicate.
 */
fun <T : KoTypeAliasProvider> List<T>.withAllTypeAliases(predicate: (KoTypeAliasDeclaration) -> Boolean): List<T> =
    filter { it.hasAllTypeAliases(predicate) }

/**
 * List containing declarations that have at least one type alias not satisfying the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by all type alias declarations.
 * @return A list containing declarations that have at least one type alias not satisfying the provided predicate.
 */
fun <T : KoTypeAliasProvider> List<T>.withoutAllTypeAliases(predicate: (KoTypeAliasDeclaration) -> Boolean): List<T> =
    filterNot { it.hasAllTypeAliases(predicate) }

/**
 * List containing declarations with type alias declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of type alias declarations.
 * @return A list containing declarations with type alias declarations satisfying the predicate.
 */
fun <T : KoTypeAliasProvider> List<T>.withTypeAliases(predicate: (List<KoTypeAliasDeclaration>) -> Boolean): List<T> =
    filter { predicate(it.typeAliases) }

/**
 * List containing declarations without type alias declarations satisfying the predicate.
 *
 * @param predicate A function that defines the condition to be met by the list of type alias declarations.
 * @return A list containing declarations without type alias declarations satisfying the predicate.
 */
fun <T : KoTypeAliasProvider> List<T>.withoutTypeAliases(predicate: (List<KoTypeAliasDeclaration>) -> Boolean): List<T> =
    filterNot { predicate(it.typeAliases) }

/**
 * List containing declarations with all specified type aliases.
 *
 * @param name The type alias name to include.
 * @param names The type alias name(s) to include.
 * @return A list containing declarations with all the specified type alias(es).
 */
@Deprecated(
    """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `withTypeAliasNamed`, otherwise with `withAllTypeAliasesNamed`.
            """,
    ReplaceWith("withTypeAliasNamed/withAllTypeAliasesNamed"),
)
fun <T : KoTypeAliasProvider> List<T>.withAllTypeAliases(
    name: String,
    vararg names: String,
): List<T> =
    filter {
        it.hasTypeAliases(name, *names)
    }

/**
 * List containing declarations with some type aliases.
 *
 * @param name The type alias name to include.
 * @param names The type alias name(s) to include.
 * @return A list containing declarations with at least one of the specified type alias(es).
 */
@Deprecated("Will be removed in v0.16.0.", ReplaceWith("withTypeAliasNamed(*names"))
fun <T : KoTypeAliasProvider> List<T>.withSomeTypeAliases(
    name: String,
    vararg names: String,
): List<T> =
    filter {
        it.hasTypeAliases(name) || names.any { typeAlias -> it.hasTypeAliases(typeAlias) }
    }

/**
 * List containing declarations without all the specified type aliases.
 *
 * @param name The type alias name to exclude.
 * @param names The type alias name(s) to exclude.
 * @return A list containing declarations without all the specified type alias(es).
 */
@Deprecated(
    """
            Will be removed in v0.16.0. 
            If you passed one argument - replace with `withoutTypeAliasNamed`, otherwise with `withoutAllTypeAliasesNamed`.
            """,
    ReplaceWith("withoutTypeAliasNamed/withoutAllTypeAliasesNamed"),
)
fun <T : KoTypeAliasProvider> List<T>.withoutAllTypeAliases(
    name: String,
    vararg names: String,
): List<T> =
    filterNot {
        it.hasTypeAliases(name, *names)
    }

/**
 * List containing declarations without some type aliases.
 *
 * @param name The type alias name to exclude.
 * @param names The type alias name(s) to exclude.
 * @return A list containing declarations without at least one of the specified type alias(es).
 */
@Deprecated("Will be removed in v0.16.0.", ReplaceWith("withoutTypeAliasNamed(*names"))
fun <T : KoTypeAliasProvider> List<T>.withoutSomeTypeAliases(
    name: String,
    vararg names: String,
): List<T> =
    filter {
        val hasAtLeastOneTypeAlias =
            if (names.isNotEmpty()) {
                names.any { typeAlias -> !it.hasTypeAliases(typeAlias) }
            } else {
                true
            }

        !it.hasTypeAliases(name) && hasAtLeastOneTypeAlias
    }
