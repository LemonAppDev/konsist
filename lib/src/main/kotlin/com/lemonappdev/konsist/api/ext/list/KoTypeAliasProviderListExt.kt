package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoTypeAliasProvider

/**
 * List containing elements with any type alias.
 *
 * @return A list containing elements with any type alias.
 */
fun <T : KoTypeAliasProvider> List<T>.withTypeAliases(): List<T> = filter { it.hasTypeAliases() }

/**
 * List containing elements with all specified type aliases.
 *
 * @param typeAlias The type alias to include.
 * @param typeAliases The type aliases to include.
 * @return A list containing elements with all the specified type alias(es).
 */
fun <T : KoTypeAliasProvider> List<T>.withAllTypeAliases(typeAlias: String, vararg typeAliases: String): List<T> = filter {
    it.hasTypeAliases(typeAlias, *typeAliases)
}

/**
 * List containing elements with some type aliases.
 *
 * @param typeAlias The type alias to include.
 * @param typeAliases The type aliases to include.
 * @return A list containing elements with at least one of the specified type alias(es).
 */
fun <T : KoTypeAliasProvider> List<T>.withSomeTypeAliases(typeAlias: String, vararg typeAliases: String): List<T> = filter {
    it.hasTypeAliases(typeAlias) || typeAliases.any { typeAlias -> it.hasTypeAliases(typeAlias) }
}

/**
 * List containing elements with no type aliases.
 *
 * @return A list containing elements with no type aliases.
 */
fun <T : KoTypeAliasProvider> List<T>.withoutTypeAliases(): List<T> = filterNot { it.hasTypeAliases() }

/**
 * List containing elements without all the specified type aliases.
 *
 * @param typeAlias The type alias to exclude.
 * @param typeAliases The type aliases to exclude.
 * @return A list containing elements without all the specified type alias(es).
 */
fun <T : KoTypeAliasProvider> List<T>.withoutAllTypeAliases(typeAlias: String, vararg typeAliases: String): List<T> = filterNot {
    it.hasTypeAliases(typeAlias, *typeAliases)
}

/**
 * List containing elements without some type aliases.
 *
 * @param typeAlias The type alias to exclude.
 * @param typeAliases The type aliases to exclude.
 * @return A list containing elements without at least one of the specified type alias(es).
 */
fun <T : KoTypeAliasProvider> List<T>.withoutSomeTypeAliases(typeAlias: String, vararg typeAliases: String): List<T> = filter {
    !it.hasTypeAliases(typeAlias) && if (typeAliases.isNotEmpty()) {
        typeAliases.any { typeAlias -> !it.hasTypeAliases(typeAlias) }
    } else {
        true
    }
}
