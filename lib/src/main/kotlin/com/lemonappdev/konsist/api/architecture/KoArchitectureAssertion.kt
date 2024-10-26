package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration

/**
 * An interface for asserting a KoArchitecture with its associated dependencies and configurations.
 */
interface KoArchitectureAssertion {
    /**
     * Asserts the architecture with the specified [dependencies] defined as a function literal with receiver [LayerDependencies].
     *
     * @param dependencies The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     */
    fun KoScope.assertArchitecture(dependencies: LayerDependencies.() -> Unit): Unit

    /**
     * Asserts the architecture with the specified [dependencies] defined as a function literal with receiver [LayerDependencies].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name recommended for `Kotest` tests. By default, test name is derived from JUnit method name,
     *                 however for Kotest framework it must be manually specified to be displayed in error messages
     *                 and enable suppression.
     * @param dependencies The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     */
    fun KoScope.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        dependencies: LayerDependencies.() -> Unit,
    ): Unit

    /**
     * Asserts the architecture with the specified [dependencies].
     *
     * @param dependencies The [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun KoScope.assertArchitecture(dependencies: LayerDependencies): Unit

    /**
     * Asserts the architecture with the specified [dependencies].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name recommended for `Kotest` tests. By default, test name is derived from JUnit method name,
     *                 however for Kotest framework it must be manually specified to be displayed in error messages
     *                 and enable suppression.
     * @param dependencies The [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun KoScope.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        dependencies: LayerDependencies,
    ): Unit

    /**
     * Asserts the architecture with the specified [dependencies] defined as a function literal with receiver [LayerDependencies].
     *
     * @param dependencies The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(dependencies: LayerDependencies.() -> Unit): Unit

    /**
     * Asserts the architecture with the specified [dependencies] defined as a function literal with receiver [LayerDependencies].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name recommended for `Kotest` tests. By default, test name is derived from JUnit method name,
     *                 however for Kotest framework it must be manually specified to be displayed in error messages
     *                 and enable suppression.
     * @param dependencies The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        dependencies: LayerDependencies.() -> Unit,
    ): Unit

    /**
     * Asserts the architecture with the specified [dependencies].
     *
     * @param dependencies The [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(dependencies: LayerDependencies): Unit

    /**
     * Asserts the architecture with the specified [dependencies].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name recommended for `Kotest` tests. By default, test name is derived from JUnit method name,
     *                 however for Kotest framework it must be manually specified to be displayed in error messages
     *                 and enable suppression.
     * @param dependencies The [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        dependencies: LayerDependencies,
    ): Unit

    /**
     * Creates and returns a [LayerDependencies] instance representing the configured dependencies of the architecture,
     * defined as a function literal with receiver [LayerDependencies].
     *
     * @param dependencies The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     * @return A [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun architecture(dependencies: LayerDependencies.() -> Unit): LayerDependencies
}
