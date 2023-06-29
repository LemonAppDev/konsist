package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.api.container.koscope.KoScope

/**
 * An interface for asserting a KoArchitecture with its associated dependencies and configurations.
 */
interface KoArchitectureAssertion {
    /**
     * Asserts the architecture with the specified [dependencies] defined as a function literal with receiver [Dependency].
     *
     * @param dependencies The function literal with receiver [Dependency] that allows configuring the dependencies
     *                     of the architecture.
     */
    fun KoScope.assertArchitecture(dependencies: Dependency.() -> Unit): Unit

    /**
     * Asserts the architecture with the specified [dependencies].
     *
     * @param dependencies The [Dependency] instance representing the configured dependencies of the architecture.
     */
    fun KoScope.assertArchitecture(dependencies: Dependency): Unit

    /**
     * Creates and returns a [Dependency] instance representing the configured dependencies of the architecture,
     * defined as a function literal with receiver [Dependency].
     *
     * @param dependencies The function literal with receiver [Dependency] that allows configuring the dependencies
     *                     of the architecture.
     * @return A [Dependency] instance representing the configured dependencies of the architecture.
     */
    fun architecture(dependencies: Dependency.() -> Unit): Dependency
}
