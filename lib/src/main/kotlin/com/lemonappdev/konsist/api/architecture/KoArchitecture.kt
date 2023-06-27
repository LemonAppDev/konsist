package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.architecture.Layer

/**
 * Represents a layered architecture.
 */
interface KoArchitecture {
    /**
     * Adds dependencies to the architecture.
     *
     * @param dependencies A lambda function that represents the dependencies to be added.
     * @return The updated `KoArchitecture` instance.
     */
    fun addDependencies(dependencies: KoArchitecture.() -> Unit): KoArchitecture

    /**
     * Adds dependencies between the current layer and the specified layers.
     *
     * @receiver The [Layer] that depends on other layers.
     * @param layer The layer that the current layer depends on.
     * @param layers The layers that the current layer depends on.
     */
    fun Layer.dependsOn(layer: Layer, vararg layers: Layer): Unit

    /**
     * Specifies that the current layer does not depend on any other layer.
     *
     * @receiver The [Layer] that does not depend on any other layer.
     */
    fun Layer.notDependOnAnyLayer(): Unit
}
