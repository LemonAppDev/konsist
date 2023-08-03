package com.lemonappdev.konsist.api.verify

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.core.verify.assert

/**
 * Asserts that all elements in the list match the specified predicate.
 *
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered valid; otherwise, it's considered invalid.
 */
fun <E : KoBaseProvider> List<E>.assert(function: (E) -> Boolean?): Unit {
    assert(function, positiveCheck = true)
}

/**
 * Asserts that no elements in the list match the specified predicate.
 *
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered invalid; otherwise, it's considered valid.
 */
fun <E : KoBaseProvider> List<E>.assertNot(function: (E) -> Boolean?): Unit {
    assert(function, positiveCheck = false)
}

/**
 * Asserts that all elements in the sequence match the specified predicate.
 *
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered valid; otherwise, it's considered invalid.
 */
fun <E : KoBaseProvider> Sequence<E>.assert(function: (E) -> Boolean?): Unit {
    this.toList().assert(function, true)
}

/**
 * Asserts that no elements in the sequence match the specified predicate.
 *
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered invalid; otherwise, it's considered valid.
 */
fun <E : KoBaseProvider> Sequence<E>.assertNot(function: (E) -> Boolean?): Unit {
    this.toList().assert(function, false)
}
