package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoNamedDeclaration

/**
 * Sequence containing declarations that have name.
 *
 * @param names The names to include.
 * @return A sequence containing declarations with the specified names.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withName(vararg names: String): Sequence<T> = filter {
    names.any { name -> it.name == name }
}

/**
 * Sequence containing declarations that don't have name.
 *
 * @param names The names to exclude.
 * @return A sequence containing declarations without the specified names.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withoutName(vararg names: String): Sequence<T> = filter {
    names.none { name -> it.name == name }
}

/**
 * Sequence containing declarations that have name with prefix.
 *
 * @param prefixes The prefixes to include.
 * @return A sequence containing declarations with names starting with the specified prefixes.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withNameStartingWith(vararg prefixes: String): Sequence<T> = filter {
    prefixes.any { prefix -> it.hasNameStartingWith(prefix) }
}

/**
 * Sequence containing declarations that don't have name with prefix.
 *
 * @param prefixes The prefixes to exclude.
 * @return A sequence containing declarations without names starting with the specified prefixes.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withoutNameStartingWith(vararg prefixes: String): Sequence<T> = filter {
    prefixes.none { prefix -> it.hasNameStartingWith(prefix) }
}

/**
 * Sequence containing declarations that have name with suffix.
 *
 * @param suffixes The suffixes to include.
 * @return A sequence containing declarations with names ending with the specified suffixes.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withNameEndingWith(vararg suffixes: String): Sequence<T> = filter {
    suffixes.any { suffix -> it.hasNameEndingWith(suffix) }
}

/**
 * Sequence containing declarations that don't have name with suffix.
 *
 * @param suffixes The suffixes to exclude.
 * @return A sequence containing declarations without names ending with the specified suffixes.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withoutNameEndingWith(vararg suffixes: String): Sequence<T> = filter {
    suffixes.none { suffix -> it.hasNameEndingWith(suffix) }
}

/**
 * Sequence containing declarations that have name containing String.
 *
 * @param texts The texts to include.
 * @return A sequence containing declarations with names containing the specified texts.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withNameContaining(vararg texts: String): Sequence<T> = filter {
    texts.any { text -> it.hasNameContaining(text) }
}

/**
 * Sequence containing declarations that don't have name containing String.
 *
 * @param texts The texts to exclude.
 * @return A sequence containing declarations without names containing the specified texts.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withoutNameContaining(vararg texts: String): Sequence<T> = filter {
    texts.none { text -> it.hasNameContaining(text) }
}

/**
 * Sequence containing declarations that have name matching regex.
 *
 * @param regexes The regular expressions to include.
 * @return A sequence containing declarations with names matching the specified regular expressions.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withNameMatching(vararg regexes: Regex): Sequence<T> = filter {
    regexes.any { text -> it.hasNameMatching(text) }
}

/**
 * Sequence containing declarations that don't have name matching regex.
 *
 * @param regexes The regular expressions to exclude.
 * @return A sequence containing declarations without names matching the specified regular expressions.
 */
fun <T : KoNamedDeclaration> Sequence<T>.withoutNameMatching(vararg regexes: Regex): Sequence<T> = filter {
    regexes.none { text -> it.hasNameMatching(text) }
}
