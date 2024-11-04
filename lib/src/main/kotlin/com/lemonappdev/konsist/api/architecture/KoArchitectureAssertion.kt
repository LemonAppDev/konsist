package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration

/**
 * An interface for asserting a KoArchitecture with its associated dependencies and configurations.
 */
interface KoArchitectureAssertion {
    /**
     * Asserts the architecture with the specified [layerDependenciesFunc] defined as a function literal with receiver [LayerDependencies].
     *
     * @param layerDependenciesFunc The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     */
    fun KoScope.assertArchitecture(layerDependenciesFunc: LayerDependencies.() -> Unit): Unit

    /**
     * Asserts the architecture with the specified [layerDependenciesFunc] defined as a function literal with receiver [LayerDependencies].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name recommended for `Kotest` tests. By default, test name is derived from JUnit method name,
     *                 however for Kotest framework it must be manually specified to be displayed in error messages
     *                 and enable suppression.
     * @param layerDependenciesFunc The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     */
    fun KoScope.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        layerDependenciesFunc: LayerDependencies.() -> Unit,
    ): Unit

    /**
     * Asserts the architecture with the specified [layerDependenciesFunc].
     *
     * @param layerDependenciesFunc The [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun KoScope.assertArchitecture(layerDependenciesFunc: LayerDependencies): Unit

    /**
     * Asserts the architecture with the specified [layerDependenciesFunc].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name recommended for `Kotest` tests. By default, test name is derived from JUnit method name,
     *                 however for Kotest framework it must be manually specified to be displayed in error messages
     *                 and enable suppression.
     * @param layerDependenciesFunc The [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun KoScope.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        layerDependenciesFunc: LayerDependencies,
    ): Unit

    /**
     * Asserts the architecture with the specified [layerDependenciesFunc] defined as a function literal with receiver [LayerDependencies].
     *
     * @param layerDependenciesFunc The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(layerDependenciesFunc: LayerDependencies.() -> Unit): Unit

    /**
     * Asserts the architecture with the specified [layerDependenciesFunc] defined as a function literal with receiver [LayerDependencies].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name recommended for `Kotest` tests. By default, test name is derived from JUnit method name,
     *                 however for Kotest framework it must be manually specified to be displayed in error messages
     *                 and enable suppression.
     * @param layerDependenciesFunc The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        layerDependenciesFunc: LayerDependencies.() -> Unit,
    ): Unit

    /**
     * Asserts the architecture with the specified [layerDependenciesFunc].
     *
     * @param layerDependenciesFunc The [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(layerDependenciesFunc: LayerDependencies): Unit

    /**
     * Asserts the architecture with the specified [layerDependenciesFunc].
     *
     * @param additionalMessage An optional message to provide additional context when the assertion fails.
     *                This message will be included in the assertion error if the assertion fails.
     * @param testName An optional test name recommended for `Kotest` tests. By default, test name is derived from JUnit method name,
     *                 however for Kotest framework it must be manually specified to be displayed in error messages
     *                 and enable suppression.
     * @param layerDependenciesFunc The [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String? = null,
        testName: String? = null,
        layerDependenciesFunc: LayerDependencies,
    ): Unit

    /**
     * Creates and returns a [LayerDependencies] instance representing the configured dependencies of the architecture,
     * defined as a function literal with receiver [LayerDependencies].
     *
     * @param layerDependenciesFunc The function literal with receiver [LayerDependencies] that allows configuring the dependencies
     * of the architecture.
     * @return A [LayerDependencies] instance representing the configured dependencies of the architecture.
     */
    fun architecture(layerDependenciesFunc: LayerDependencies.() -> Unit): LayerDependencies
}
