package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about a test classes.
 */
interface KoTestClassProvider : KoBaseProvider {
    /**
     * The test classes.
     *
     * @param testPropertyName the test property name to check. By default, "sut".
     * @param moduleName         the name of the module to check (optional).
     * @param sourceSetName      the name of the source set to check (optional).
     * @return a list of [KoClassDeclaration] which are test classes.
     */
    fun testClasses(
        testPropertyName: String = "sut",
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): List<KoClassDeclaration>

    /**
     * The test classes matching the predicate.
     *
     * @param moduleName    the name of the module to check (optional).
     * @param sourceSetName the name of the source set to check (optional).
     * @param predicate     A function that defines the condition to be met by a test class.
     * @return a list of [KoClassDeclaration] which are test classes.
     */
    fun testClasses(
        moduleName: String? = null,
        sourceSetName: String? = null,
        predicate: (KoClassDeclaration) -> Boolean,
    ): List<KoClassDeclaration>

    /**
     * Returns the number of test classes.
     *
     * @param testPropertyName the test property name to check. By default, "sut".
     * @param moduleName         the name of the module to check (optional).
     * @param sourceSetName      the name of the source set to check (optional).
     * @return The number of test classes.
     */
    fun numTestClasses(
        testPropertyName: String = "sut",
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): Int

    /**
     * Returns the number of test classes matching the predicate.
     *
     * @param moduleName    the name of the module to check (optional).
     * @param sourceSetName the name of the source set to check (optional).
     * @param predicate     A function that defines the condition to be met by a test class.
     * @return The number of test classes.
     */
    fun countTestClasses(
        moduleName: String? = null,
        sourceSetName: String? = null,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Int

    /**
     * Determines whatever declaration has a Test class.
     *
     * @param testPropertyName the test property name to check. By default, "sut".
     * @param moduleName         the name of the module to check (optional).
     * @param sourceSetName      the name of the source set to check (optional).
     * @return `true` if the declaration has a test class, `false` otherwise.
     */
    fun hasTestClasses(
        testPropertyName: String = "sut",
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): Boolean

    /**
     * Determines whatever declaration has a test class that satisfies the provided predicate.
     *
     * @param moduleName    the name of the module to check (optional).
     * @param sourceSetName the name of the source set to check (optional).
     * @param predicate     A function that defines the condition to be met by a test class.
     * @return `true` if there is a matching test class, `false` otherwise.
     */
    fun hasTestClass(
        moduleName: String? = null,
        sourceSetName: String? = null,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean
}
