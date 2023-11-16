package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoHasTestClassProvider

/**
 * List containing declarations with a test.
 *
 * @param testFileNameSuffix the suffix of the test file name. By default, "Test".
 * @param moduleName         the name of the module to check (optional).
 * @param sourceSetName      the name of the source set to check (optional).
 * @return A list containing declarations with a test.
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("withTestClass"))
fun <T : KoHasTestClassProvider> List<T>.withTestClass(
    testFileNameSuffix: String = "Test",
    moduleName: String? = null,
    sourceSetName: String? = null,
): List<T> = filter { it.hasTestClass(testFileNameSuffix, moduleName, sourceSetName) }

/**
 * List containing declarations without a test.
 *
 * @param testFileNameSuffix the suffix of the test file name. By default, "Test".
 * @param moduleName         the name of the module to check (optional).
 * @param sourceSetName      the name of the source set to check (optional).
 * @return A list containing declarations without a test.
 */
@Deprecated("Will be removed in v1.0.0", ReplaceWith("withoutTestClass"))
fun <T : KoHasTestClassProvider> List<T>.withoutTestClass(
    testFileNameSuffix: String = "Test",
    moduleName: String? = null,
    sourceSetName: String? = null,
): List<T> = filterNot { it.hasTestClass(testFileNameSuffix, moduleName, sourceSetName) }
