package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoHasTestClassProvider

/**
 * List containing declarations with a test.
 *
 * @param testPropertyName the test property name to check. By default, "sut".
 * @param moduleName         the name of the module to check (optional).
 * @param sourceSetName      the name of the source set to check (optional).
 * @return A list containing declarations with a test.
 */
fun <T : KoHasTestClassProvider> List<T>.withTestClass(
    testPropertyName: String = "sut",
    moduleName: String? = null,
    sourceSetName: String? = null,
): List<T> = filter { it.hasTestClass(testPropertyName, moduleName, sourceSetName) }

/**
 * List containing declarations without a test.
 *
 * @param testPropertyName the test property name to check. By default, "sut".
 * @param moduleName         the name of the module to check (optional).
 * @param sourceSetName      the name of the source set to check (optional).
 * @return A list containing declarations without a test.
 */
fun <T : KoHasTestClassProvider> List<T>.withoutTestClass(
    testPropertyName: String = "sut",
    moduleName: String? = null,
    sourceSetName: String? = null,
): List<T> = filterNot { it.hasTestClass(testPropertyName, moduleName, sourceSetName) }
