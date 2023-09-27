package com.lemonappdev.konsist.api.verify

import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.core.verify.assert

/**
 * Asserts that element match the specified predicate.
 *
 * @param strict A flag indicating whether strict checking should be enabled.
 *               If set to `true`, an assertion error will be thrown if assert is called on null,
 *               which can be helpful for debugging.
 *               If set to `false`, the method will pass successfully when called on null.
 *               By default, false.
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param suppressName An optional suppress name. By default, suppress name is derived from JUnit method name,
 *                however for koTest framework it hase to be manually specified to allow suppression.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered valid; otherwise, it's considered invalid.
 */
fun <E : KoBaseProvider> E?.assertTrue(
    strict: Boolean = false,
    additionalMessage: String? = null,
    suppressName: String? = null,
    function: (E) -> Boolean?,
): Unit {
    listOf(this).assert(strict, additionalMessage, suppressName, function, positiveCheck = true)
}

/**
 * Asserts that element not match the specified predicate.
 *
 * @param strict A flag indicating whether strict checking should be enabled.
 *               If set to `true`, an assertion error will be thrown if assert is called on null,
 *               which can be helpful for debugging.
 *               If set to `false`, the method will pass successfully when called on null.
 *               By default, false.
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param suppressName An optional suppress name. By default, suppress name is derived from JUnit method name,
 *                however for koTest framework it hase to be manually specified to allow suppression.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered invalid; otherwise, it's considered valid.
 */
fun <E : KoBaseProvider> E?.assertFalse(
    strict: Boolean = false,
    additionalMessage: String? = null,
    suppressName: String? = null,
    function: (E) -> Boolean?,
): Unit {
    listOf(this).assert(strict, additionalMessage, suppressName, function, positiveCheck = false)
}

/**
 * Asserts that all elements in the list match the specified predicate.
 *
 * @param strict A flag indicating whether strict checking should be enabled.
 *               If set to `true`, an assertion error will be thrown if the list is empty or contains only null values,
 *               which can be helpful for debugging.
 *               If set to `false`, the method will pass successfully when called on an empty list.
 *               By default, false.
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param suppressName An optional suppress name. By default, suppress name is derived from JUnit method name,
 *                however for koTest framework it hase to be manually specified to allow suppression.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered valid; otherwise, it's considered invalid.
 */
fun <E : KoBaseProvider> List<E?>.assertTrue(
    strict: Boolean = false,
    additionalMessage: String? = null,
    suppressName: String? = null,
    function: (E) -> Boolean?,
): Unit {
    assert(strict, additionalMessage, suppressName, function, positiveCheck = true)
}

/**
 * Asserts that no elements in the list match the specified predicate.
 *
 * @param strict A flag indicating whether strict checking should be enabled.
 *               If set to `true`, an assertion error will be thrown if the list is empty or contains only null values,
 *               which can be helpful for debugging.
 *               If set to `false`, the method will pass successfully when called on an empty list.
 *               By default, false.
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param suppressName An optional suppress name. By default, suppress name is derived from JUnit method name,
 *                however for koTest framework it hase to be manually specified to allow suppression.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered invalid; otherwise, it's considered valid.
 */
fun <E : KoBaseProvider> List<E?>.assertFalse(
    strict: Boolean = false,
    additionalMessage: String? = null,
    suppressName: String? = null,
    function: (E) -> Boolean?,
): Unit {
    assert(strict, additionalMessage, suppressName, function, positiveCheck = false)
}

/**
 * Asserts that all elements in the sequence match the specified predicate.
 *
 * @param strict A flag indicating whether strict checking should be enabled.
 *               If set to `true`, an assertion error will be thrown if the sequence is empty or contains only null values,
 *               which can be helpful for debugging.
 *               If set to `false`, the method will pass successfully when called on an empty sequence.
 *               By default, false.
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param suppressName An optional suppress name. By default, suppress name is derived from JUnit method name,
 *                however for koTest framework it hase to be manually specified to allow suppression.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered valid; otherwise, it's considered invalid.
 */
fun <E : KoBaseProvider> Sequence<E?>.assertTrue(
    strict: Boolean = false,
    additionalMessage: String? = null,
    suppressName: String? = null,
    function: (E) -> Boolean?,
): Unit {
    this.toList().assert(strict, additionalMessage, suppressName, function, true)
}

/**
 * Asserts that no elements in the sequence match the specified predicate.
 *
 * @param strict A flag indicating whether strict checking should be enabled.
 *               If set to `true`, an assertion error will be thrown if the sequence is empty or contains only null values,
 *               which can be helpful for debugging.
 *               If set to `false`, the method will pass successfully when called on an empty sequence.
 *               By default, false.
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param suppressName An optional suppress name. By default, suppress name is derived from JUnit method name,
 *                however for koTest framework it hase to be manually specified to allow suppression.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered invalid; otherwise, it's considered valid.
 */
fun <E : KoBaseProvider> Sequence<E?>.assertFalse(
    strict: Boolean = false,
    additionalMessage: String? = null,
    suppressName: String? = null,
    function: (E) -> Boolean?,
): Unit {
    this.toList().assert(strict, additionalMessage, suppressName, function, false)
}

/**
 * Asserts that the element has `null` value.
 *
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                          This message will be included in the assertion error if the assertion fails.
 */
fun <E : KoBaseProvider> E?.assertNull(
    additionalMessage: String? = null,
): Unit {
    listOf(this).assert(true, additionalMessage, isEmptyOrNull = true, onSingleElement = true)
}

/**
 * Asserts that the element has not `null` value.
 *
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                          This message will be included in the assertion error if the assertion fails.
 */
fun <E : KoBaseProvider> E?.assertNotNull(
    additionalMessage: String? = null,
): Unit {
    listOf(this).assert(true, additionalMessage, isEmptyOrNull = false, onSingleElement = true)
}

/**
 * Asserts that the list is empty.
 *
 * @param strict A flag indicating whether strict checking should be enabled.
 *               If set to `true`, the assertion will pass if the list is empty or contains only null values.
 *               If set to `false`, null values are treated as regular elements and the assertion will pass if the list
 *               is completely empty.
 *               By default, false.
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                         This message will be included in the assertion error if the assertion fails.
 */
fun <E : KoBaseProvider> List<E?>.assertEmpty(
    strict: Boolean = false,
    additionalMessage: String? = null,
): Unit {
    assert(strict, additionalMessage, isEmptyOrNull = true, onSingleElement = false)
}

/**
 * Asserts that the list is not empty.
 *
 * @param strict A flag indicating whether strict checking should be enabled.
 *               If set to `true`, the assertion will fail if the list is empty or contains only null values.
 *               If set to `false`, null values are treated as regular elements and the assertion will fail if the list
 *               is completely empty.
 *               By default, false.
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                         This message will be included in the assertion error if the assertion fails.
 */
fun <E : KoBaseProvider> List<E?>.assertNotEmpty(
    strict: Boolean = false,
    additionalMessage: String? = null,
): Unit {
    assert(strict, additionalMessage, isEmptyOrNull = false, onSingleElement = false)
}

/**
 * Asserts that the sequence is empty.
 *
 * @param strict A flag indicating whether strict checking should be enabled.
 *               If set to `true`, the assertion will pass if the sequence is empty or contains only null values.
 *               If set to `false`, null values are treated as regular elements and the assertion will pass if the sequence
 *               is completely empty.
 *               By default, false.
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                         This message will be included in the assertion error if the assertion fails.
 */
fun <E : KoBaseProvider> Sequence<E?>.assertEmpty(
    strict: Boolean = false,
    additionalMessage: String? = null,
): Unit {
    this.toList().assert(strict, additionalMessage, isEmptyOrNull = true, onSingleElement = false)
}

/**
 * Asserts that the sequence is not empty.
 *
 * @param strict A flag indicating whether strict checking should be enabled.
 *               If set to `true`, the assertion will fail if the sequence is empty or contains only null values.
 *               If set to `false`, null values are treated as regular elements and the assertion will fail if the sequence
 *               is completely empty.
 *               By default, false.
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                         This message will be included in the assertion error if the assertion fails.
 */
fun <E : KoBaseProvider> Sequence<E?>.assertNotEmpty(
    strict: Boolean = false,
    additionalMessage: String? = null,
): Unit {
    this.toList().assert(strict, additionalMessage, isEmptyOrNull = false, onSingleElement = false)
}

/**
 * Asserts that element match the specified predicate.
 *
 * @param additionalMessage An optional message to provide additional context when the assertion fails.
 *                This message will be included in the assertion error if the assertion fails.
 * @param function The predicate function that takes an element of type [E] and returns a [Boolean] value.
 *                If the function returns `true`, the element is considered valid; otherwise, it's considered invalid.
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("assertTrue"))
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
@Deprecated("Will be removed in v1.0.0", ReplaceWith("assertFalse"))
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
@Deprecated("Will be removed in v1.0.0", ReplaceWith("assertTrue"))
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
@Deprecated("Will be removed in v1.0.0", ReplaceWith("assertFalse"))
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
@Deprecated("Will be removed in v1.0.0", ReplaceWith("assertTrue"))
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
@Deprecated("Will be removed in v1.0.0", ReplaceWith("assertFalse"))
fun <E : KoBaseProvider> Sequence<E>.assertNot(additionalMessage: String? = null, function: (E) -> Boolean?): Unit {
    this.toList().assert(additionalMessage, function, false)
}
