package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.architecture.Layer

/**
 * Architecture creator.
 *
 * Creates a [KoArchitecture] instance from the given set of layers.
 */
interface KoArchitectureCreator {
    /**
     * Creates an instance of `KoArchitectureImpl` with the specified layers.
     *
     * @param layer The layer to be included in the architecture.
     * @param layers Additional layers to be included in the architecture.
     * @return An instance of `KoArchitectureImpl` with the specified layers.
     */
    fun architecture(layer: Layer, vararg layers: Layer): KoArchitecture
}
