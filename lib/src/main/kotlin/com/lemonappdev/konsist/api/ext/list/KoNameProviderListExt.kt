package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoNameProvider

/**
 * List containing elements with any of the specified names.
 *
 * @param name The name to include.
 * @param names The names to include.
 * @return A list containing elements with the specified names.
 */
fun <T : KoNameProvider> List<T>.withName(name: String, vararg names: String): List<T> = filter {
    it.name == name || names.any { name -> it.name == name }
}

/**
 * List containing elements without any of the specified names.
 *
 * @param name The name to exclude.
 * @param names The names to exclude.
 * @return A list containing elements without the specified names.
 */
fun <T : KoNameProvider> List<T>.withoutName(name: String, vararg names: String): List<T> = filter {
    it.name != name && names.none { name -> it.name == name }
}

/**
 * List containing elements with name with any of the specified prefix.
 *
 * @param prefix The prefix to include.
 * @param prefixes The prefixes to include.
 * @return A list containing elements with names starting with the specified prefixes.
 */
fun <T : KoNameProvider> List<T>.withNameStartingWith(prefix: String, vararg prefixes: String): List<T> = filter {
    it.hasNameStartingWith(prefix) || prefixes.any { prefix -> it.hasNameStartingWith(prefix) }
}

/**
 * List containing elements without name with any of the specified prefix.
 *
 * @param prefix The prefix to exclude.
 * @param prefixes The prefixes to exclude.
 * @return A list containing elements without names starting with the specified prefixes.
 */
fun <T : KoNameProvider> List<T>.withoutNameStartingWith(prefix: String, vararg prefixes: String): List<T> = filter {
    !it.hasNameStartingWith(prefix) && prefixes.none { prefix -> it.hasNameStartingWith(prefix) }
}

/**
 * List containing elements with name with any of the specified suffix.
 *
 * @param suffix The suffix to include.
 * @param suffixes The suffixes to include.
 * @return A list containing elements with names ending with the specified suffixes.
 */
fun <T : KoNameProvider> List<T>.withNameEndingWith(suffix: String, vararg suffixes: String): List<T> = filter {
    it.hasNameEndingWith(suffix) || suffixes.any { suffix -> it.hasNameEndingWith(suffix) }
}

/**
 * List containing elements without name with any of the specified suffix.
 *
 * @param suffix The suffix to exclude.
 * @param suffixes The suffixes to exclude.
 * @return A list containing elements without names ending with the specified suffixes.
 */
fun <T : KoNameProvider> List<T>.withoutNameEndingWith(suffix: String, vararg suffixes: String): List<T> = filter {
    !it.hasNameEndingWith(suffix) && suffixes.none { suffix -> it.hasNameEndingWith(suffix) }
}

/**
 * List containing elements with name containing any of the specified String.
 *
 * @param text The text to include.
 * @param texts The texts to include.
 * @return A list containing elements with names containing the specified texts.
 */
fun <T : KoNameProvider> List<T>.withNameContaining(text: String, vararg texts: String): List<T> = filter {
    it.hasNameContaining(text) || texts.any { text -> it.hasNameContaining(text) }
}

/**
 * List containing elements without name containing any of the specified String.
 *
 * @param text The text to exclude.
 * @param texts The texts to exclude.
 * @return A list containing elements without names containing the specified texts.
 */
fun <T : KoNameProvider> List<T>.withoutNameContaining(text: String, vararg texts: String): List<T> = filter {
    !it.hasNameContaining(text) && texts.none { text -> it.hasNameContaining(text) }
}

/**
 * List containing elements with name matching any of the specified regex.
 *
 * @param regex The regular expression to include.
 * @param regexes The regular expressions to include.
 * @return A list containing elements with names matching the specified regular expressions.
 */
fun <T : KoNameProvider> List<T>.withNameMatching(regex: Regex, vararg regexes: Regex): List<T> = filter {
    it.hasNameMatching(regex) || regexes.any { regex -> it.hasNameMatching(regex) }
}

/**
 * List containing elements without name matching any of the specified regex.
 *
 * @param regex The regular expression to exclude.
 * @param regexes The regular expressions to exclude.
 * @return A list containing elements without names matching the specified regular expressions.
 */
fun <T : KoNameProvider> List<T>.withoutNameMatching(regex: Regex, vararg regexes: Regex): List<T> = filter {
    !it.hasNameMatching(regex) && regexes.none { regex -> it.hasNameMatching(regex) }
}
