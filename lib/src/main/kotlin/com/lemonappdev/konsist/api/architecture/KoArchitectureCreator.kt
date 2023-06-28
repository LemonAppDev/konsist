package com.lemonappdev.konsist.api.architecture

/**
 * Architecture creator.
 *
 * Creates a [KoArchitecture] instance from the given set of layers.
 */
interface KoArchitectureCreator {
    /**
     * Creates an instance of `KoArchitectureImpl` with the specified layers.
     *
     * @return An instance of [KoArchitecture] with the specified layers.
     */
    fun architecture(): KoArchitecture
}
