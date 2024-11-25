package com.lemonappdev.konsist.api.architecture

/**
 * Represents the dependency configuration for a layer within an architecture.
 *
 * This interface provides methods to define dependencies between layers in an architecture.
 */
interface LayerDependencies {
    /**
     * Adds dependencies between the current layer and the specified layers.
     *
     * @receiver The [Layer] that depends on other layers.
     * @param layer The layer that the current layer depends on.
     * @param layers The layers that the current layer depends on.
     */
    fun Layer.dependsOn(
        layer: Layer,
        vararg layers: Layer,
    ): Unit

    /**
     * Adds dependencies between the current layer and the specified list of layers.
     *
     * @receiver The [Layer] that depends on other layers.
     * @param layers The list of layers that the current layer depends on.
     */
    fun Layer.dependsOn(layers: Set<Layer>): Unit

    /**
     * Specifies that the current layer does not depend on any given layer.
     *
     * @param layer The layer that the current layer does not depend on.
     * @param layers The layers that the current layer does not depend on.
     * @receiver The [Layer] that does not depend on other layers.
     */
    fun Layer.doesNotDependOn(
        layer: Layer,
        vararg layers: Layer,
    ): Unit

    /**
     * Adds dependencies between each layer in the collection and the specified layer.
     *
     * Example usage:
     * ```
     * val layers = setOf(domainLayer, dataLayer)
     * layers.dependsOn(presentationLayer)
     * ```
     *
     * @receiver The collection of [Layer]s that depends on other layer.
     * @param layer The layer that the collection of layers depends on.
     */
    fun Collection<Layer>.dependsOn(layer: Layer): Unit

    /**
     * Specifies that the current layer does not depend on the given list of layers.
     *
     * @param layers The list of layers that the current layer does not depend on.
     * @receiver The [Layer] that does not depend on other layers.
     */
    fun Layer.doesNotDependOn(layers: Set<Layer>): Unit

    /**
     * Adds dependencies between each layer in the collection and the specified layers.
     *
     * Example usage:
     * ```
     * val sourceLayers = setOf(domainLayer, dataLayer)
     * val targetLayers = setOf(presentationLayer, infrastructureLayer)
     * sourceLayers.dependsOn(targetLayers)
     * ```
     *
     * @receiver The collection of [Layer]s that depends on other layers.
     * @param layers The set of layers that the collection of layers depends on.
     */
    fun Collection<Layer>.dependsOn(layers: Set<Layer>): Unit

    /**
     * Specifies that the current layer does not depend on any other layer.
     *
     * @receiver The [Layer] that does not depend on any other layer.
     *
     * @see include
     */
    fun Layer.dependsOnNothing(): Unit

    /**
     * Specifies that none of the layers in the collection depend on any other layer.
     *
     * Example usage:
     * ```
     * val layers = setOf(domainLayer, dataLayer)
     * layers.dependsOnNothing()
     * ```
     *
     * @receiver The collection of [Layer]s that should not depend on any other layer.
     * @see include
     */
    fun Collection<Layer>.dependsOnNothing(): Unit

    /**
     * Specifies that none of the layers in the collection depend on the specified layer.
     *
     * Example usage:
     * ```
     * val layers = setOf(domainLayer, dataLayer)
     * layers.doesNotDependOn(presentationLayer)
     * ```
     *
     * @receiver The collection of [Layer]s that do not depend on the specified layer.
     * @param layer The layer that none of the collection layers should depend on.
     */
    fun Collection<Layer>.doesNotDependOn(layer: Layer): Unit

    /**
     * Specifies that none of the layers in the collection depend on any of the specified layers.
     *
     * Example usage:
     * ```
     * val sourceLayers = setOf(domainLayer, dataLayer)
     * val targetLayers = setOf(presentationLayer, infrastructureLayer)
     * sourceLayers.doesNotDependOn(targetLayers)
     * ```
     *
     * @receiver The collection of [Layer]s that do not depend on the specified layers.
     * @param layers The set of layers that none of the collection layers should depend on.
     */
    fun Collection<Layer>.doesNotDependOn(layers: Set<Layer>): Unit

    /**
     * This function is used to include a Layer in the architecture without specifying any dependencies.
     * It can be used in combination with dependsOnNothing() to specify that a layer does not depend on any other layer.
     *
     * @receiver LayerDependenciesCore The core dependencies container
     * @return The LayerDependenciesCore instance for chaining
     */
    fun Layer.include(): Unit

    /**
     * Includes all layers from the collection in the architecture without specifying any dependencies.
     * This can be used in combination with dependsOnNothing() to specify that multiple layers do not depend on any other layer.
     *
     * Example usage:
     * ```
     * val layers = setOf(domainLayer, dataLayer)
     * layers.include()
     * ```
     *
     * @receiver The collection of [Layer]s to be included in the architecture.
     */
    fun Collection<Layer>.include(): Unit
}
