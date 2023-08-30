package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoHasTestProvider

/**
 * List containing elements with a test.
 *
 * @param testFileNameSuffix the suffix of the test file name. By default, "Test".
 * @param moduleName         the name of the module to check (optional).
 * @param sourceSetName      the name of the source set to check (optional).
 * @return A list containing elements with a test.
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("withTestClass"))
fun <T : KoHasTestProvider> List<T>.withTest(
    testFileNameSuffix: String = "Test",
    moduleName: String? = null,
    sourceSetName: String? = null,
): List<T> = filter { it.hasTest(testFileNameSuffix, moduleName, sourceSetName) }

/**
 * List containing elements without a test.
 *
 * @param testFileNameSuffix the suffix of the test file name. By default, "Test".
 * @param moduleName         the name of the module to check (optional).
 * @param sourceSetName      the name of the source set to check (optional).
 * @return A list containing elements without a test.
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("withoutTestClass"))
fun <T : KoHasTestProvider> List<T>.withoutTest(
    testFileNameSuffix: String = "Test",
    moduleName: String? = null,
    sourceSetName: String? = null,
): List<T> = filterNot { it.hasTest(testFileNameSuffix, moduleName, sourceSetName) }
