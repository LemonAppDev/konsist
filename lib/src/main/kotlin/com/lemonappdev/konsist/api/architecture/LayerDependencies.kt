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
     * Specifies that the current layer does not depend on the given list of layers.
     *
     * @param layers The list of layers that the current layer does not depend on.
     * @receiver The [Layer] that does not depend on other layers.
     */
    fun Layer.doesNotDependOn(layers: Set<Layer>): Unit

    /**
     * Specifies that the current layer does not depend on any other layer.
     *
     * @receiver The [Layer] that does not depend on any other layer.
     *
     * @see include
     */
    fun Layer.dependsOnNothing(): Unit

    /**
     * This function is used to include a Layer in the architecture without specifying any dependencies.
     * It can be used in combination with dependsOnNothing() to specify that a layer does not depend on any other layer.
     *
     * @receiver LayerDependenciesCore The core dependencies container
     * @return The LayerDependenciesCore instance for chaining
     */
    fun Layer.include(): Unit
}
