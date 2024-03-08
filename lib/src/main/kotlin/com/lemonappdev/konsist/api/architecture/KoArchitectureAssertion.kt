package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration

/**
 * An interface for asserting a KoArchitecture with its associated dependencies and configurations.
 */
interface KoArchitectureAssertion {
    /**
     * Asserts the architecture with the specified [dependencies] defined as a function literal with receiver [DependencyRules].
     *
     * @param dependencies The function literal with receiver [DependencyRules] that allows configuring the dependencies
     * of the architecture.
     */
    fun KoScope.assertArchitecture(dependencies: DependencyRules.() -> Unit): Unit

    /**
     * Asserts the architecture with the specified [dependencies] defined as a function literal with receiver [DependencyRules].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name recommended for `Kotest` tests. By default, test name is derived from JUnit method name,
     *                 however for Kotest framework it must be manually specified to be displayed in error messages
     *                 and enable suppression.
     * @param dependencies The function literal with receiver [DependencyRules] that allows configuring the dependencies
     * of the architecture.
     */
    fun KoScope.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        dependencies: DependencyRules.() -> Unit,
    ): Unit

    /**
     * Asserts the architecture with the specified [dependencies].
     *
     * @param dependencies The [DependencyRules] instance representing the configured dependencies of the architecture.
     */
    fun KoScope.assertArchitecture(dependencies: DependencyRules): Unit

    /**
     * Asserts the architecture with the specified [dependencies].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name recommended for `Kotest` tests. By default, test name is derived from JUnit method name,
     *                 however for Kotest framework it must be manually specified to be displayed in error messages
     *                 and enable suppression.
     * @param dependencies The [DependencyRules] instance representing the configured dependencies of the architecture.
     */
    fun KoScope.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        dependencies: DependencyRules,
    ): Unit

    /**
     * Asserts the architecture with the specified [dependencies] defined as a function literal with receiver [DependencyRules].
     *
     * @param dependencies The function literal with receiver [DependencyRules] that allows configuring the dependencies
     * of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(dependencies: DependencyRules.() -> Unit): Unit

    /**
     * Asserts the architecture with the specified [dependencies] defined as a function literal with receiver [DependencyRules].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name recommended for `Kotest` tests. By default, test name is derived from JUnit method name,
     *                 however for Kotest framework it must be manually specified to be displayed in error messages
     *                 and enable suppression.
     * @param dependencies The function literal with receiver [DependencyRules] that allows configuring the dependencies
     * of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        dependencies: DependencyRules.() -> Unit,
    ): Unit

    /**
     * Asserts the architecture with the specified [dependencies].
     *
     * @param dependencies The [DependencyRules] instance representing the configured dependencies of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(dependencies: DependencyRules): Unit

    /**
     * Asserts the architecture with the specified [dependencies].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name recommended for `Kotest` tests. By default, test name is derived from JUnit method name,
     *                 however for Kotest framework it must be manually specified to be displayed in error messages
     *                 and enable suppression.
     * @param dependencies The [DependencyRules] instance representing the configured dependencies of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        dependencies: DependencyRules,
    ): Unit

    /**
     * Creates and returns a [DependencyRules] instance representing the configured dependencies of the architecture,
     * defined as a function literal with receiver [DependencyRules].
     *
     * @param dependencies The function literal with receiver [DependencyRules] that allows configuring the dependencies
     * of the architecture.
     * @return A [DependencyRules] instance representing the configured dependencies of the architecture.
     */
    fun architecture(dependencies: DependencyRules.() -> Unit): DependencyRules
}
