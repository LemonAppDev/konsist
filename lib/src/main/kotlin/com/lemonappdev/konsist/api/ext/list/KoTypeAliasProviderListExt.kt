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
 * List containing declarations with all specified type aliases.
 *
 * @param name The type alias name to include.
 * @param names The type alias name(s) to include.
 * @return A list containing declarations with all the specified type alias(es).
 */
fun <T : KoTypeAliasProvider> List<T>.withAllTypeAliases(name: String, vararg names: String): List<T> = filter {
    it.hasTypeAliases(name, *names)
}

/**
 * List containing declarations with some type aliases.
 *
 * @param name The type alias name to include.
 * @param names The type alias name(s) to include.
 * @return A list containing declarations with at least one of the specified type alias(es).
 */
fun <T : KoTypeAliasProvider> List<T>.withSomeTypeAliases(name: String, vararg names: String): List<T> = filter {
    it.hasTypeAliases(name) || names.any { typeAlias -> it.hasTypeAliases(typeAlias) }
}

/**
 * List containing declarations with no type aliases.
 *
 * @return A list containing declarations with no type aliases.
 */
fun <T : KoTypeAliasProvider> List<T>.withoutTypeAliases(): List<T> = filterNot { it.hasTypeAliases() }

/**
 * List containing declarations without all the specified type aliases.
 *
 * @param name The type alias name to exclude.
 * @param names The type alias name(s) to exclude.
 * @return A list containing declarations without all the specified type alias(es).
 */
fun <T : KoTypeAliasProvider> List<T>.withoutAllTypeAliases(name: String, vararg names: String): List<T> = filterNot {
    it.hasTypeAliases(name, *names)
}

/**
 * List containing declarations without some type aliases.
 *
 * @param name The type alias name to exclude.
 * @param names The type alias name(s) to exclude.
 * @return A list containing declarations without at least one of the specified type alias(es).
 */
fun <T : KoTypeAliasProvider> List<T>.withoutSomeTypeAliases(name: String, vararg names: String): List<T> = filter {
    !it.hasTypeAliases(name) && if (names.isNotEmpty()) {
        names.any { typeAlias -> !it.hasTypeAliases(typeAlias) }
    } else {
        true
    }
}
