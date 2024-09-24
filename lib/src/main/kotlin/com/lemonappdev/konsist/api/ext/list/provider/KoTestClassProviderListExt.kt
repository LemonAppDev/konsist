package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoTestClassProvider

/**
 * List containing test classes.
 *
 * @param testPropertyName the test property name to check. By default, "sut".
 * @param moduleName         the name of the module to check (optional).
 * @param sourceSetName      the name of the source set to check (optional).
 * @return A list containing all test classes.
 */
fun <T : KoTestClassProvider> List<T>.testClasses(
    testPropertyName: String = "sut",
    moduleName: String? = null,
    sourceSetName: String? = null,
): List<KoBaseDeclaration> = flatMap { it.testClasses(testPropertyName, moduleName, sourceSetName) }

/**
 * List containing test classes matching the predicate.
 *
 * @param moduleName    the name of the module to check (optional).
 * @param sourceSetName the name of the source set to check (optional).
 * @param predicate     A function that defines the condition to be met by a test class.
 * @return A list containing all test classes matching the predicate.
 */
fun <T : KoTestClassProvider> List<T>.testClasses(
    moduleName: String? = null,
    sourceSetName: String? = null,
    predicate: (KoClassDeclaration) -> Boolean,
): List<KoBaseDeclaration> = flatMap { it.testClasses(moduleName, sourceSetName, predicate) }

/**
 * List containing declarations with a test.
 *
 * @param testPropertyName the test property name to check. By default, "sut".
 * @param moduleName         the name of the module to check (optional).
 * @param sourceSetName      the name of the source set to check (optional).
 * @return A list containing declarations with a test.
 */
fun <T : KoTestClassProvider> List<T>.withTestClass(
    testPropertyName: String = "sut",
    moduleName: String? = null,
    sourceSetName: String? = null,
): List<T> = filter { it.hasTestClasses(testPropertyName, moduleName, sourceSetName) }

/**
 * List containing declarations without a test.
 *
 * @param testPropertyName the test property name to check. By default, "sut".
 * @param moduleName         the name of the module to check (optional).
 * @param sourceSetName      the name of the source set to check (optional).
 * @return A list containing declarations without a test.
 */
fun <T : KoTestClassProvider> List<T>.withoutTestClass(
    testPropertyName: String = "sut",
    moduleName: String? = null,
    sourceSetName: String? = null,
): List<T> = filterNot { it.hasTestClasses(testPropertyName, moduleName, sourceSetName) }

/**
 * List containing declarations with a test matching the predicate.
 *
 * @param moduleName    the name of the module to check (optional).
 * @param sourceSetName the name of the source set to check (optional).
 * @param predicate     A function that defines the condition to be met by a test class.
 *  @return A list containing declarations with a test matching the predicate.
 */
fun <T : KoTestClassProvider> List<T>.withTestClass(
    moduleName: String? = null,
    sourceSetName: String? = null,
    predicate: (KoClassDeclaration) -> Boolean,
): List<T> = filter { it.hasTestClass(moduleName, sourceSetName, predicate) }

/**
 * List containing declarations without a test matching the predicate.
 *
 * @param moduleName    the name of the module to check (optional).
 * @param sourceSetName the name of the source set to check (optional).
 * @param predicate     A function that defines the condition to be met by a test class.
 *  @return A list containing declarations without a test matching the predicate.
 */
fun <T : KoTestClassProvider> List<T>.withoutTestClass(
    moduleName: String? = null,
    sourceSetName: String? = null,
    predicate: (KoClassDeclaration) -> Boolean,
): List<T> = filterNot { it.hasTestClass(moduleName, sourceSetName, predicate) }
