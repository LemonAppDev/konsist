package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoTypeAliasProvider

/**
 * Sequence containing files with any type alias.
 *
 * @return A sequence containing files with any type alias.
 */
fun <T : KoTypeAliasProvider> Sequence<T>.withTypeAliases(): Sequence<T> = filter { it.hasTypeAliases() }

/**
 * Sequence containing files with all specified type aliases.
 *
 * @param typeAlias The type alias to include.
 * @param typeAliases The type aliases to include.
 * @return A sequence containing files with all the specified type alias(es).
 */
fun <T : KoTypeAliasProvider> Sequence<T>.withAllTypeAliases(typeAlias: String, vararg typeAliases: String): Sequence<T> = filter {
    it.hasTypeAliases(typeAlias, *typeAliases)
}

/**
 * Sequence containing files with some type aliases.
 *
 * @param typeAlias The type alias to include.
 * @param typeAliases The type aliases to include.
 * @return A sequence containing files with at least one of the specified type alias(es).
 */
fun <T : KoTypeAliasProvider> Sequence<T>.withSomeTypeAliases(typeAlias: String, vararg typeAliases: String): Sequence<T> = filter {
    it.hasTypeAliases(typeAlias) || typeAliases.any { typeAlias -> it.hasTypeAliases(typeAlias) }
}

/**
 * Sequence containing files with no type aliases.
 *
 * @return A sequence containing files with no type aliases.
 */
fun <T : KoTypeAliasProvider> Sequence<T>.withoutTypeAliases(): Sequence<T> = filterNot { it.hasTypeAliases() }

/**
 * Sequence containing files without all the specified type aliases.
 *
 * @param typeAlias The type alias to exclude.
 * @param typeAliases The type aliases to exclude.
 * @return A sequence containing files without all the specified type alias(es).
 */
fun <T : KoTypeAliasProvider> Sequence<T>.withoutAllTypeAliases(typeAlias: String, vararg typeAliases: String): Sequence<T> = filterNot {
    it.hasTypeAliases(typeAlias, *typeAliases)
}

/**
 * Sequence containing files without some type aliases.
 *
 * @param typeAlias The type alias to exclude.
 * @param typeAliases The type aliases to exclude.
 * @return A sequence containing files without at least one of the specified type alias(es).
 */
fun <T : KoTypeAliasProvider> Sequence<T>.withoutSomeTypeAliases(typeAlias: String, vararg typeAliases: String): Sequence<T> = filter {
    !it.hasTypeAliases(typeAlias) && if (typeAliases.isNotEmpty()) {
        typeAliases.any { typeAlias -> !it.hasTypeAliases(typeAlias) }
    } else {
        true
    }
}
