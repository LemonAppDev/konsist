@file:Suppress("detekt.TooManyFunctions")

package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.provider.KoTextProvider

/**
 * List containing declarations with text.
 *
 * @param texts The text(s) to include.
 * @return A list containing declarations with the specified texts (or any text if [texts] is empty).
 */
fun <T : KoTextProvider> List<T>.withText(vararg texts: String): List<T> = withText(listOf(*texts))

/**
 * List containing declarations with text.
 *
 * @param texts The text(s) to include.
 * @return A list containing declarations with the specified texts (or any text if [texts] is empty).
 */
fun <T : KoTextProvider> List<T>.withText(texts: Collection<String>): List<T> =
    filter {
        when {
            texts.isEmpty() -> it.text != ""
            else -> texts.any { text -> it.text == text }
        }
    }

/**
 * List containing declarations without text.
 *
 * @param texts The text(s) to exclude.
 * @return A list containing declarations without the specified texts (or none text if [texts] is empty).
 */
fun <T : KoTextProvider> List<T>.withoutText(vararg texts: String): List<T> = withoutText(listOf(*texts))

/**
 * List containing declarations without text.
 *
 * @param texts The text(s) to exclude.
 * @return A list containing declarations without the specified texts (or none text if [texts] is empty).
 */
fun <T : KoTextProvider> List<T>.withoutText(texts: Collection<String>): List<T> =
    filter {
        when {
            texts.isEmpty() -> it.text == ""
            else -> texts.none { text -> it.text == text }
        }
    }

/**
 * List containing declarations that have a text matching the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a declaration text.
 * @return A list containing declarations with the text matching the provided predicate.
 */
fun <T : KoTextProvider> List<T>.withText(predicate: (String) -> Boolean): List<T> = filter { predicate(it.text) }

/**
 * List containing declarations that don't have a text matching the provided predicate.
 *
 * @param predicate A function that defines the condition to be met by a declaration text.
 * @return A list containing declarations without the text matching the provided predicate.
 */
fun <T : KoTextProvider> List<T>.withoutText(predicate: (String) -> Boolean): List<T> = filterNot { predicate(it.text) }

/**
 * List containing declarations with text with any of the specified prefix.
 *
 * @param prefix The prefix to include.
 * @param prefixes The prefixes to include.
 * @return A list containing declarations with texts starting with the specified prefixes.
 */
fun <T : KoTextProvider> List<T>.withTextStartingWith(
    prefix: String,
    vararg prefixes: String,
): List<T> = withTextStartingWith(listOf(prefix, *prefixes))

/**
 * List containing declarations with text with any of the specified prefix.
 *
 * @param prefixes The prefixes to include.
 * @return A list containing declarations with texts starting with the specified prefixes.
 */
fun <T : KoTextProvider> List<T>.withTextStartingWith(prefixes: Collection<String>): List<T> =
    filter {
        when {
            prefixes.isEmpty() -> it.text != ""
            else -> prefixes.any { prefix -> it.hasTextStartingWith(prefix) }
        }
    }

/**
 * List containing declarations without text with any of the specified prefix.
 *
 * @param prefix The prefix to exclude.
 * @param prefixes The prefixes to exclude.
 * @return A list containing declarations without texts starting with the specified prefixes.
 */
fun <T : KoTextProvider> List<T>.withoutTextStartingWith(
    prefix: String,
    vararg prefixes: String,
): List<T> = withoutTextStartingWith(listOf(prefix, *prefixes))

/**
 * List containing declarations without text with any of the specified prefix.
 *
 * @param prefixes The prefixes to exclude.
 * @return A list containing declarations without texts starting with the specified prefixes.
 */
fun <T : KoTextProvider> List<T>.withoutTextStartingWith(prefixes: Collection<String>): List<T> =
    filterNot {
        when {
            prefixes.isEmpty() -> it.text != ""
            else -> prefixes.any { prefix -> it.hasTextStartingWith(prefix) }
        }
    }

/**
 * List containing declarations with text with any of the specified suffix.
 *
 * @param suffix The suffix to include.
 * @param suffixes The suffixes to include.
 * @return A list containing declarations with texts ending with the specified suffixes.
 */
fun <T : KoTextProvider> List<T>.withTextEndingWith(
    suffix: String,
    vararg suffixes: String,
): List<T> = withTextEndingWith(listOf(suffix, *suffixes))

/**
 * List containing declarations with text with any of the specified suffix.
 *
 * @param suffixes The suffixes to include.
 * @return A list containing declarations with texts ending with the specified suffixes.
 */
fun <T : KoTextProvider> List<T>.withTextEndingWith(suffixes: Collection<String>): List<T> =
    filter {
        when {
            suffixes.isEmpty() -> it.text != ""
            else -> suffixes.any { suffix -> it.hasTextEndingWith(suffix) }
        }
    }

/**
 * List containing declarations without text with any of the specified suffix.
 *
 * @param suffix The suffix to exclude.
 * @param suffixes The suffixes to exclude.
 * @return A list containing declarations without texts ending with the specified suffixes.
 */
fun <T : KoTextProvider> List<T>.withoutTextEndingWith(
    suffix: String,
    vararg suffixes: String,
): List<T> = withoutTextEndingWith(listOf(suffix, *suffixes))

/**
 * List containing declarations without text with any of the specified suffix.
 *
 * @param suffixes The suffixes to exclude.
 * @return A list containing declarations without texts ending with the specified suffixes.
 */
fun <T : KoTextProvider> List<T>.withoutTextEndingWith(suffixes: Collection<String>): List<T> =
    filterNot {
        when {
            suffixes.isEmpty() -> it.text != ""
            else -> suffixes.any { suffix -> it.hasTextEndingWith(suffix) }
        }
    }

/**
 * List containing declarations with text containing any of the specified String.
 *
 * @param text The text to include.
 * @param texts The texts to include.
 * @return A list containing declarations with texts containing the specified texts.
 */
fun <T : KoTextProvider> List<T>.withTextContaining(
    text: String,
    vararg texts: String,
): List<T> = withTextContaining(listOf(text, *texts))

/**
 * List containing declarations with text containing any of the specified String.
 *
 * @param texts The texts to include.
 * @return A list containing declarations with texts containing the specified texts.
 */
fun <T : KoTextProvider> List<T>.withTextContaining(texts: Collection<String>): List<T> =
    filter {
        when {
            texts.isEmpty() -> it.text != ""
            else -> texts.any { text -> it.hasTextContaining(text) }
        }
    }

/**
 * List containing declarations without text containing any of the specified String.
 *
 * @param text The text to exclude.
 * @param texts The texts to exclude.
 * @return A list containing declarations without texts containing the specified texts.
 */
fun <T : KoTextProvider> List<T>.withoutTextContaining(
    text: String,
    vararg texts: String,
): List<T> = withoutTextContaining(listOf(text, *texts))

/**
 * List containing declarations without text containing any of the specified String.
 *
 * @param texts The texts to exclude.
 * @return A list containing declarations without texts containing the specified texts.
 */
fun <T : KoTextProvider> List<T>.withoutTextContaining(texts: Collection<String>): List<T> =
    filterNot {
        when {
            texts.isEmpty() -> it.text != ""
            else -> texts.any { text -> it.hasTextContaining(text) }
        }
    }

/**
 * List containing declarations with text matching any of the specified regex.
 *
 * @param regex The regular expression to include.
 * @param regexes The regular expressions to include.
 * @return A list containing declarations with texts matching the specified regular expressions.
 */
fun <T : KoTextProvider> List<T>.withTextMatching(
    regex: Regex,
    vararg regexes: Regex,
): List<T> = withTextMatching(listOf(regex, *regexes))

/**
 * List containing declarations with text matching any of the specified regex.
 *
 * @param regexes The regular expressions to include.
 * @return A list containing declarations with texts matching the specified regular expressions.
 */
fun <T : KoTextProvider> List<T>.withTextMatching(regexes: Collection<Regex>): List<T> =
    filter {
        when {
            regexes.isEmpty() -> it.text != ""
            else -> regexes.any { regex -> it.hasTextMatching(regex) }
        }
    }

/**
 * List containing declarations without text matching any of the specified regex.
 *
 * @param regex The regular expression to exclude.
 * @param regexes The regular expressions to exclude.
 * @return A list containing declarations without texts matching the specified regular expressions.
 */
fun <T : KoTextProvider> List<T>.withoutTextMatching(
    regex: Regex,
    vararg regexes: Regex,
): List<T> = withoutTextMatching(listOf(regex, *regexes))

/**
 * List containing declarations without text matching any of the specified regex.
 *
 * @param regexes The regular expressions to exclude.
 * @return A list containing declarations without texts matching the specified regular expressions.
 */
fun <T : KoTextProvider> List<T>.withoutTextMatching(regexes: Collection<Regex>): List<T> =
    filterNot {
        when {
            regexes.isEmpty() -> it.text != ""
            else -> regexes.any { regex -> it.hasTextMatching(regex) }
        }
    }
