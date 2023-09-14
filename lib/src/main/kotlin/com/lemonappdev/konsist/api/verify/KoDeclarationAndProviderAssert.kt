package com.lemonappdev.konsist.api.verify

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.core.verify.assert

/**
 * Asserts that element match the specified predicate.
 *
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered valid; otherwise, it's considered invalid.
 */
fun <E : KoBaseProvider> E.assert(additionalMessage: String? = null, function: (E) -> Boolean?): Unit {
    listOf(this).assert(additionalMessage, function, positiveCheck = true)
}

/**
 * Asserts that element not match the specified predicate.
 *
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered invalid; otherwise, it's considered valid.
 */
fun <E : KoBaseProvider> E.assertNot(additionalMessage: String? = null, function: (E) -> Boolean?): Unit {
    listOf(this).assert(additionalMessage, function, positiveCheck = false)
}

/**
 * Asserts that all elements in the list match the specified predicate.
 *
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered valid; otherwise, it's considered invalid.
 */
fun <E : KoBaseProvider> List<E>.assert(additionalMessage: String? = null, function: (E) -> Boolean?): Unit {
    assert(additionalMessage, function, positiveCheck = true)
}

/**
 * Asserts that no elements in the list match the specified predicate.
 *
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered invalid; otherwise, it's considered valid.
 */
fun <E : KoBaseProvider> List<E>.assertNot(additionalMessage: String? = null, function: (E) -> Boolean?): Unit {
    assert(additionalMessage, function, positiveCheck = false)
}

/**
 * Asserts that all elements in the sequence match the specified predicate.
 *
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered valid; otherwise, it's considered invalid.
 */
fun <E : KoBaseProvider> Sequence<E>.assert(additionalMessage: String? = null, function: (E) -> Boolean?): Unit {
    this.toList().assert(additionalMessage, function, true)
}

/**
 * Asserts that no elements in the sequence match the specified predicate.
 *
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered invalid; otherwise, it's considered valid.
 */
fun <E : KoBaseProvider> Sequence<E>.assertNot(additionalMessage: String? = null, function: (E) -> Boolean?): Unit {
    this.toList().assert(additionalMessage, function, false)
}
