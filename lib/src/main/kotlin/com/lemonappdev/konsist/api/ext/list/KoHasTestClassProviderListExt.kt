package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoHasTestClassProvider

/**
 * List containing elements with a test.
 *
 * @param testFileNameSuffix the suffix of the test file name. By default, "Test".
 * @param moduleName         the name of the module to check (optional).
 * @param sourceSetName      the name of the source set to check (optional).
 * @return A list containing elements with a test.
 */
fun <T : KoHasTestClassProvider> List<T>.withTestClass(
    testFileNameSuffix: String = "Test",
    moduleName: String? = null,
    sourceSetName: String? = null,
): List<T> = filter { it.hasTestClass(testFileNameSuffix, moduleName, sourceSetName) }

/**
 * List containing elements without a test.
 *
 * @param testFileNameSuffix the suffix of the test file name. By default, "Test".
 * @param moduleName         the name of the module to check (optional).
 * @param sourceSetName      the name of the source set to check (optional).
 * @return A list containing elements without a test.
 */
fun <T : KoHasTestClassProvider> List<T>.withoutTestClass(
    testFileNameSuffix: String = "Test",
    moduleName: String? = null,
    sourceSetName: String? = null,
): List<T> = filterNot { it.hasTestClass(testFileNameSuffix, moduleName, sourceSetName) }
