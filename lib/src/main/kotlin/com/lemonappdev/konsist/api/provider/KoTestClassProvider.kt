package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration

/**
 * An interface representing a Kotlin declaration that provides information about its test classes.
 */
interface KoTestClassProvider : KoBaseTestClassProvider {
    /**
     * The test classes.
     *
     * @param testPropertyName the test property name to check. By default, "sut".
     * @param moduleName       the name of the module to check (optional).
     * @param sourceSetName    the name of the source set to check (optional).
     * @return a list of [KoClassDeclaration] which are test classes.
     */
    fun testClasses(
        testPropertyName: String = "sut",
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): List<KoClassDeclaration>

    /**
     * Returns the number of test classes.
     *
     * @param testPropertyName the test property name to check. By default, "sut".
     * @param moduleName       the name of the module to check (optional).
     * @param sourceSetName    the name of the source set to check (optional).
     * @return The number of test classes.
     */
    fun numTestClasses(
        testPropertyName: String = "sut",
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): Int

    /**
     * Determines whether the declaration has a test class.
     *
     * @param testPropertyName the test property name to check. By default, "sut".
     * @param moduleName       the name of the module to check (optional).
     * @param sourceSetName    the name of the source set to check (optional).
     * @return `true` if the declaration has a test class, `false` otherwise.
     */
    fun hasTestClasses(
        testPropertyName: String = "sut",
        moduleName: String? = null,
        sourceSetName: String? = null,
    ): Boolean
}
