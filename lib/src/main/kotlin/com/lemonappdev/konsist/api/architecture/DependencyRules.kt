package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.architecture.LayerImpl

/**
 * Represents the dependency configuration for a layer within an architecture.
 *
 * This interface provides methods to define dependencies between layers in an architecture.
 */
interface DependencyRules {
    /**
     * Adds dependencies between the current layer and the specified layers.
     *
     * @receiver The [LayerImpl] that depends on other layers.
     * @param layer The layer that the current layer depends on.
     * @param layers The layers that the current layer depends on.
     */
    fun LayerImpl.dependsOn(layer: LayerImpl, vararg layers: LayerImpl): Unit

    /**
     * Specifies that the current layer does not depend on any other layer.
     *
     * @receiver The [LayerImpl] that does not depend on any other layer.
     */
    fun LayerImpl.dependsOnNothing(): Unit
}
