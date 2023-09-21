package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoNameProvider

/**
 * List containing declarations with name.
 *
 * @param names The name(s) to include.
 * @return A list containing declarations with the specified names (or any name if [names] is empty).
 */
fun <T : KoNameProvider> List<T>.withName(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.name != ""
        else -> names.any { name -> it.name == name }
    }
}

/**
 * List containing declarations without name.
 *
 * @param names The name(s) to exclude.
 * @return A list containing declarations without the specified names (or none name if [names] is empty).
 */
fun <T : KoNameProvider> List<T>.withoutName(vararg names: String): List<T> = filter {
    when {
        names.isEmpty() -> it.name == ""
        else -> names.none { name -> it.name == name }
    }
}

/**
 * List containing declarations that have a name matching the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a declaration name.
 * @return A list containing declarations with the name matching the provided predicate.
 */
fun <T : KoNameProvider> List<T>.withName(predicate: (String) -> Boolean): List<T> = filter { predicate(it.name) }

/**
 * List containing declarations that don't have a name matching the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a declaration name.
 * @return A list containing declarations without the name matching the provided predicate.
 */
fun <T : KoNameProvider> List<T>.withoutName(predicate: (String) -> Boolean): List<T> =
    filterNot { predicate(it.name) }

/**
 * List containing declarations with name with any of the specified prefix.
 *
 * @param prefix The prefix to include.
 * @param prefixes The prefixes to include.
 * @return A list containing declarations with names starting with the specified prefixes.
 */
fun <T : KoNameProvider> List<T>.withNameStartingWith(prefix: String, vararg prefixes: String): List<T> = filter {
    it.hasNameStartingWith(prefix) || prefixes.any { prefix -> it.hasNameStartingWith(prefix) }
}

/**
 * List containing declarations without name with any of the specified prefix.
 *
 * @param prefix The prefix to exclude.
 * @param prefixes The prefixes to exclude.
 * @return A list containing declarations without names starting with the specified prefixes.
 */
fun <T : KoNameProvider> List<T>.withoutNameStartingWith(prefix: String, vararg prefixes: String): List<T> = filter {
    !it.hasNameStartingWith(prefix) && prefixes.none { prefix -> it.hasNameStartingWith(prefix) }
}

/**
 * List containing declarations with name with any of the specified suffix.
 *
 * @param suffix The suffix to include.
 * @param suffixes The suffixes to include.
 * @return A list containing declarations with names ending with the specified suffixes.
 */
fun <T : KoNameProvider> List<T>.withNameEndingWith(suffix: String, vararg suffixes: String): List<T> = filter {
    it.hasNameEndingWith(suffix) || suffixes.any { suffix -> it.hasNameEndingWith(suffix) }
}

/**
 * List containing declarations without name with any of the specified suffix.
 *
 * @param suffix The suffix to exclude.
 * @param suffixes The suffixes to exclude.
 * @return A list containing declarations without names ending with the specified suffixes.
 */
fun <T : KoNameProvider> List<T>.withoutNameEndingWith(suffix: String, vararg suffixes: String): List<T> = filter {
    !it.hasNameEndingWith(suffix) && suffixes.none { suffix -> it.hasNameEndingWith(suffix) }
}

/**
 * List containing declarations with name containing any of the specified String.
 *
 * @param text The text to include.
 * @param texts The texts to include.
 * @return A list containing declarations with names containing the specified texts.
 */
fun <T : KoNameProvider> List<T>.withNameContaining(text: String, vararg texts: String): List<T> = filter {
    it.hasNameContaining(text) || texts.any { text -> it.hasNameContaining(text) }
}

/**
 * List containing declarations without name containing any of the specified String.
 *
 * @param text The text to exclude.
 * @param texts The texts to exclude.
 * @return A list containing declarations without names containing the specified texts.
 */
fun <T : KoNameProvider> List<T>.withoutNameContaining(text: String, vararg texts: String): List<T> = filter {
    !it.hasNameContaining(text) && texts.none { text -> it.hasNameContaining(text) }
}

/**
 * List containing declarations with name matching any of the specified regex.
 *
 * @param regex The regular expression to include.
 * @param regexes The regular expressions to include.
 * @return A list containing declarations with names matching the specified regular expressions.
 */
fun <T : KoNameProvider> List<T>.withNameMatching(regex: Regex, vararg regexes: Regex): List<T> = filter {
    it.hasNameMatching(regex) || regexes.any { regex -> it.hasNameMatching(regex) }
}

/**
 * List containing declarations without name matching any of the specified regex.
 *
 * @param regex The regular expression to exclude.
 * @param regexes The regular expressions to exclude.
 * @return A list containing declarations without names matching the specified regular expressions.
 */
fun <T : KoNameProvider> List<T>.withoutNameMatching(regex: Regex, vararg regexes: Regex): List<T> = filter {
    !it.hasNameMatching(regex) && regexes.none { regex -> it.hasNameMatching(regex) }
}
