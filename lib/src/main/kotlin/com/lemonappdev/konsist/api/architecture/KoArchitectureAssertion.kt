package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.api.container.koscope.KoScope

/**
 * An interface for asserting a KoArchitecture with its associated dependencies and configurations.
 */
interface KoArchitectureAssertion {
    /**
     * Asserts the architecture with the specified [dependencies] defined as a function literal with receiver [DependencyRules].
     *
     * @param dependencies The function literal with receiver [DependencyRules] that allows configuring the dependencies
     *                     of the architecture.
     */
    fun KoScope.assertArchitecture(dependencies: DependencyRules.() -> Unit): Unit

    /**
     * Asserts the architecture with the specified [dependencies].
     *
     * @param dependencies The [DependencyRules] instance representing the configured dependencies of the architecture.
     */
    fun KoScope.assertArchitecture(dependencies: DependencyRules): Unit

    /**
     * Creates and returns a [DependencyRules] instance representing the configured dependencies of the architecture,
     * defined as a function literal with receiver [DependencyRules].
     *
     * @param dependencies The function literal with receiver [DependencyRules] that allows configuring the dependencies
     *                     of the architecture.
     * @return A [DependencyRules] instance representing the configured dependencies of the architecture.
     */
    fun architecture(dependencies: DependencyRules.() -> Unit): DependencyRules
}
