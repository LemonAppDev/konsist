@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoNameProvider

/**
 * List containing declarations with name.
 *
 * @param names The name(s) to include.
 * @return A list containing declarations with the specified names (or any name if [names] is empty).
 */
fun <T : KoNameProvider> List<T>.withName(vararg names: String): List<T> = withName(listOf(*names))

/**
 * List containing declarations with name.
 *
 * @param names The name(s) to include.
 * @return A list containing declarations with the specified names (or any name if [names] is empty).
 */
fun <T : KoNameProvider> List<T>.withName(names: Collection<String>): List<T> =
    filter {
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
fun <T : KoNameProvider> List<T>.withoutName(vararg names: String): List<T> = withoutName(listOf(*names))

/**
 * List containing declarations without name.
 *
 * @param names The name(s) to exclude.
 * @return A list containing declarations without the specified names (or none name if [names] is empty).
 */
fun <T : KoNameProvider> List<T>.withoutName(names: Collection<String>): List<T> =
    filter {
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
fun <T : KoNameProvider> List<T>.withoutName(predicate: (String) -> Boolean): List<T> = filterNot { predicate(it.name) }

/**
 * List containing declarations with name with any of the specified prefix.
 *
 * @param prefix The prefix to include.
 * @param prefixes The prefixes to include.
 * @return A list containing declarations with names starting with the specified prefixes.
 */
fun <T : KoNameProvider> List<T>.withNameStartingWith(
    prefix: String,
    vararg prefixes: String,
): List<T> = withNameStartingWith(listOf(prefix, *prefixes))

/**
 * List containing declarations with name with any of the specified prefix.
 *
 * @param prefixes The prefixes to include.
 * @return A list containing declarations with names starting with the specified prefixes.
 */
fun <T : KoNameProvider> List<T>.withNameStartingWith(prefixes: Collection<String>): List<T> =
    filter {
        when {
            prefixes.isEmpty() -> it.name != ""
            else -> prefixes.any { prefix -> it.hasNameStartingWith(prefix) }
        }
    }

/**
 * List containing declarations without name with any of the specified prefix.
 *
 * @param prefix The prefix to exclude.
 * @param prefixes The prefixes to exclude.
 * @return A list containing declarations without names starting with the specified prefixes.
 */
fun <T : KoNameProvider> List<T>.withoutNameStartingWith(
    prefix: String,
    vararg prefixes: String,
): List<T> = withoutNameStartingWith(listOf(prefix, *prefixes))

/**
 * List containing declarations without name with any of the specified prefix.
 *
 * @param prefixes The prefixes to exclude.
 * @return A list containing declarations without names starting with the specified prefixes.
 */
fun <T : KoNameProvider> List<T>.withoutNameStartingWith(prefixes: Collection<String>): List<T> =
    filterNot {
        when {
            prefixes.isEmpty() -> it.name != ""
            else -> prefixes.any { prefix -> it.hasNameStartingWith(prefix) }
        }
    }

/**
 * List containing declarations with name with any of the specified suffix.
 *
 * @param suffix The suffix to include.
 * @param suffixes The suffixes to include.
 * @return A list containing declarations with names ending with the specified suffixes.
 */
fun <T : KoNameProvider> List<T>.withNameEndingWith(
    suffix: String,
    vararg suffixes: String,
): List<T> = withNameEndingWith(listOf(suffix, *suffixes))

/**
 * List containing declarations with name with any of the specified suffix.
 *
 * @param suffixes The suffixes to include.
 * @return A list containing declarations with names ending with the specified suffixes.
 */
fun <T : KoNameProvider> List<T>.withNameEndingWith(suffixes: Collection<String>): List<T> =
    filter {
        when {
            suffixes.isEmpty() -> it.name != ""
            else -> suffixes.any { suffix -> it.hasNameEndingWith(suffix) }
        }
    }

/**
 * List containing declarations without name with any of the specified suffix.
 *
 * @param suffix The suffix to exclude.
 * @param suffixes The suffixes to exclude.
 * @return A list containing declarations without names ending with the specified suffixes.
 */
fun <T : KoNameProvider> List<T>.withoutNameEndingWith(
    suffix: String,
    vararg suffixes: String,
): List<T> = withoutNameEndingWith(listOf(suffix, *suffixes))

/**
 * List containing declarations without name with any of the specified suffix.
 *
 * @param suffixes The suffixes to exclude.
 * @return A list containing declarations without names ending with the specified suffixes.
 */
fun <T : KoNameProvider> List<T>.withoutNameEndingWith(suffixes: Collection<String>): List<T> =
    filterNot {
        when {
            suffixes.isEmpty() -> it.name != ""
            else -> suffixes.any { suffix -> it.hasNameEndingWith(suffix) }
        }
    }

/**
 * List containing declarations with name containing any of the specified String.
 *
 * @param text The text to include.
 * @param texts The texts to include.
 * @return A list containing declarations with names containing the specified texts.
 */
fun <T : KoNameProvider> List<T>.withNameContaining(
    text: String,
    vararg texts: String,
): List<T> = withNameContaining(listOf(text, *texts))

/**
 * List containing declarations with name containing any of the specified String.
 *
 * @param texts The texts to include.
 * @return A list containing declarations with names containing the specified texts.
 */
fun <T : KoNameProvider> List<T>.withNameContaining(texts: Collection<String>): List<T> =
    filter {
        when {
            texts.isEmpty() -> it.name != ""
            else -> texts.any { text -> it.hasNameContaining(text) }
        }
    }

/**
 * List containing declarations without name containing any of the specified String.
 *
 * @param text The text to exclude.
 * @param texts The texts to exclude.
 * @return A list containing declarations without names containing the specified texts.
 */
fun <T : KoNameProvider> List<T>.withoutNameContaining(
    text: String,
    vararg texts: String,
): List<T> = withoutNameContaining(listOf(text, *texts))

/**
 * List containing declarations without name containing any of the specified String.
 *
 * @param texts The texts to exclude.
 * @return A list containing declarations without names containing the specified texts.
 */
fun <T : KoNameProvider> List<T>.withoutNameContaining(texts: Collection<String>): List<T> =
    filterNot {
        when {
            texts.isEmpty() -> it.name != ""
            else -> texts.any { text -> it.hasNameContaining(text) }
        }
    }

/**
 * List containing declarations with name matching any of the specified regex.
 *
 * @param regex The regular expression to include.
 * @param regexes The regular expressions to include.
 * @return A list containing declarations with names matching the specified regular expressions.
 */
fun <T : KoNameProvider> List<T>.withNameMatching(
    regex: Regex,
    vararg regexes: Regex,
): List<T> = withNameMatching(listOf(regex, *regexes))

/**
 * List containing declarations with name matching any of the specified regex.
 *
 * @param regexes The regular expressions to include.
 * @return A list containing declarations with names matching the specified regular expressions.
 */
fun <T : KoNameProvider> List<T>.withNameMatching(regexes: Collection<Regex>): List<T> =
    filter {
        when {
            regexes.isEmpty() -> it.name != ""
            else -> regexes.any { regex -> it.hasNameMatching(regex) }
        }
    }

/**
 * List containing declarations without name matching any of the specified regex.
 *
 * @param regex The regular expression to exclude.
 * @param regexes The regular expressions to exclude.
 * @return A list containing declarations without names matching the specified regular expressions.
 */
fun <T : KoNameProvider> List<T>.withoutNameMatching(
    regex: Regex,
    vararg regexes: Regex,
): List<T> = withoutNameMatching(listOf(regex, *regexes))

/**
 * List containing declarations without name matching any of the specified regex.
 *
 * @param regexes The regular expressions to exclude.
 * @return A list containing declarations without names matching the specified regular expressions.
 */
fun <T : KoNameProvider> List<T>.withoutNameMatching(regexes: Collection<Regex>): List<T> =
    filterNot {
        when {
            regexes.isEmpty() -> it.name != ""
            else -> regexes.any { regex -> it.hasNameMatching(regex) }
        }
    }

/**
 * Extension function to check if a collection of KoNameProvider is sorted by name.
 *
 * @param ascending Determines the order of sorting. If true (default), checks for ascending order.
 *                  If false, checks for descending order.
 * @param ignoreCase Determines whether the comparison should be case-insensitive.
 *                   Default is true.
 * @return true if the collection is sorted by name in the specified order, false otherwise.
 */
fun List<KoNameProvider>.isSortedByName(
    ascending: Boolean = true,
    ignoreCase: Boolean = true,
): Boolean {
    if (size <= 1) return true

    val iterator = iterator()
    var previous = iterator.next().name

    while (iterator.hasNext()) {
        val current = iterator.next().name

        val comparisonResult = previous.compareTo(current, ignoreCase = ignoreCase)

        if ((ascending && comparisonResult > 0) || (!ascending && comparisonResult < 0)) {
            return false
        }

        previous = current
    }

    return true
}
