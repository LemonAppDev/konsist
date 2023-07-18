package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration

/**
 * Sequence containing declarations with any of the specified names.
 *
 * @param name The name to include.
 * @param names The names to include.
 * @return A sequence containing declarations with the specified names.
 */
fun <T : KoBaseDeclaration> Sequence<T>.withName(name: String, vararg names: String): Sequence<T> = filter {
    it.name == name || names.any { name -> it.name == name }
}

/**
 * Sequence containing declarations without any of the specified names.
 *
 * @param name The name to exclude.
 * @param names The names to exclude.
 * @return A sequence containing declarations without the specified names.
 */
fun <T : KoBaseDeclaration> Sequence<T>.withoutName(name: String, vararg names: String): Sequence<T> = filter {
    it.name != name && names.none { name -> it.name == name }
}

/**
 * Sequence containing declarations with name with any of the specified prefix.
 *
 * @param prefix The prefix to include.
 * @param prefixes The prefixes to include.
 * @return A sequence containing declarations with names starting with the specified prefixes.
 */
fun <T : KoBaseDeclaration> Sequence<T>.withNameStartingWith(prefix: String, vararg prefixes: String): Sequence<T> = filter {
    it.hasNameStartingWith(prefix) || prefixes.any { prefix -> it.hasNameStartingWith(prefix) }
}

/**
 * Sequence containing declarations without name with any of the specified prefix.
 *
 * @param prefix The prefix to exclude.
 * @param prefixes The prefixes to exclude.
 * @return A sequence containing declarations without names starting with the specified prefixes.
 */
fun <T : KoBaseDeclaration> Sequence<T>.withoutNameStartingWith(prefix: String, vararg prefixes: String): Sequence<T> = filter {
    !it.hasNameStartingWith(prefix) && prefixes.none { prefix -> it.hasNameStartingWith(prefix) }
}

/**
 * Sequence containing declarations with name with any of the specified suffix.
 *
 * @param suffix The suffix to include.
 * @param suffixes The suffixes to include.
 * @return A sequence containing declarations with names ending with the specified suffixes.
 */
fun <T : KoBaseDeclaration> Sequence<T>.withNameEndingWith(suffix: String, vararg suffixes: String): Sequence<T> = filter {
    it.hasNameEndingWith(suffix) || suffixes.any { suffix -> it.hasNameEndingWith(suffix) }
}

/**
 * Sequence containing declarations without name with any of the specified suffix.
 *
 * @param suffix The suffix to exclude.
 * @param suffixes The suffixes to exclude.
 * @return A sequence containing declarations without names ending with the specified suffixes.
 */
fun <T : KoBaseDeclaration> Sequence<T>.withoutNameEndingWith(suffix: String, vararg suffixes: String): Sequence<T> = filter {
    !it.hasNameEndingWith(suffix) && suffixes.none { suffix -> it.hasNameEndingWith(suffix) }
}

/**
 * Sequence containing declarations with name containing any of the specified String.
 *
 * @param text The text to include.
 * @param texts The texts to include.
 * @return A sequence containing declarations with names containing the specified texts.
 */
fun <T : KoBaseDeclaration> Sequence<T>.withNameContaining(text: String, vararg texts: String): Sequence<T> = filter {
    it.hasNameContaining(text) || texts.any { text -> it.hasNameContaining(text) }
}

/**
 * Sequence containing declarations without name containing any of the specified String.
 *
 * @param text The text to exclude.
 * @param texts The texts to exclude.
 * @return A sequence containing declarations without names containing the specified texts.
 */
fun <T : KoBaseDeclaration> Sequence<T>.withoutNameContaining(text: String, vararg texts: String): Sequence<T> = filter {
    !it.hasNameContaining(text) && texts.none { text -> it.hasNameContaining(text) }
}

/**
 * Sequence containing declarations with name matching any of the specified regex.
 *
 * @param regex The regular expression to include.
 * @param regexes The regular expressions to include.
 * @return A sequence containing declarations with names matching the specified regular expressions.
 */
fun <T : KoBaseDeclaration> Sequence<T>.withNameMatching(regex: Regex, vararg regexes: Regex): Sequence<T> = filter {
    it.hasNameMatching(regex) || regexes.any { regex -> it.hasNameMatching(regex) }
}

/**
 * Sequence containing declarations without name matching any of the specified regex.
 *
 * @param regex The regular expression to exclude.
 * @param regexes The regular expressions to exclude.
 * @return A sequence containing declarations without names matching the specified regular expressions.
 */
fun <T : KoBaseDeclaration> Sequence<T>.withoutNameMatching(regex: Regex, vararg regexes: Regex): Sequence<T> = filter {
    !it.hasNameMatching(regex) && regexes.none { regex -> it.hasNameMatching(regex) }
}
