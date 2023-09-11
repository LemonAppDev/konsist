package com.lemonappdev.konsist.api.verify

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.core.verify.assert

/**
 * Asserts that all elements in the list match the specified predicate.
 *
 * @param message An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered valid; otherwise, it's considered invalid.
 */
fun <E : KoBaseProvider> List<E>.assert(message: String? = null, function: (E) -> Boolean?): Unit {
    assert(message, function, positiveCheck = true)
}

/**
 * Asserts that no elements in the list match the specified predicate.
 *
 * @param message An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered invalid; otherwise, it's considered valid.
 */
fun <E : KoBaseProvider> List<E>.assertNot(message: String? = null, function: (E) -> Boolean?): Unit {
    assert(message, function, positiveCheck = false)
}

/**
 * Asserts that all elements in the sequence match the specified predicate.
 *
 * @param message An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered valid; otherwise, it's considered invalid.
 */
fun <E : KoBaseProvider> Sequence<E>.assert(message: String? = null, function: (E) -> Boolean?): Unit {
    this.toList().assert(message, function, true)
}

/**
 * Asserts that no elements in the sequence match the specified predicate.
 *
 * @param message An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered invalid; otherwise, it's considered valid.
 */
fun <E : KoBaseProvider> Sequence<E>.assertNot(message: String? = null, function: (E) -> Boolean?): Unit {
    this.toList().assert(message, function, false)
}
