package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.architecture.LayerDependencies
import com.lemonappdev.konsist.core.architecture.validator.LayerValidatorManager
import com.lemonappdev.konsist.core.exception.KoInvalidAssertArchitectureConfigurationException

internal class LayerDependenciesCore(
    private val layerValidatorManager: LayerValidatorManager = LayerValidatorManager(),
) : LayerDependencies {
    internal val layerDependencies = mutableSetOf<LayerDependency>()
    internal var layers = mutableSetOf<Layer>()

    override fun Layer.dependsOn(
        layer: Layer,
        vararg layers: Layer,
    ) {
        dependsOn(setOf(layer) + layers.toSet())
    }

    override fun Layer.dependsOn(layers: Set<Layer>) {
        require(layers.isNotEmpty()) { "Layers set is empty." }
        requireLayerCannotBeDependentOnItself(this, layers) { "Layer '$name' cannot be dependent on itself." }

        val dependOnNothingDependency = getLayerWithDependency(this, LayerDependencyType.DEPEND_ON_NOTHING)

        if (dependOnNothingDependency != null) {
            throw KoInvalidAssertArchitectureConfigurationException(
                "Layer ''$name'' is already configured with no dependencies. " +
                    "It cannot subsequently depend on ${getLayersMessage(layers)}.",
            )
        }

        with(this@LayerDependenciesCore.layers) {
            add(this@dependsOn)
            addAll(layers)
        }

        layers.forEach {
            addLayerDependency(this, LayerDependencyType.DEPEND_ON_LAYER, it)
        }

        layerValidatorManager.validateLayerDependencies(layerDependencies)
    }

    private fun getLayersMessage(layers: Set<Layer>) =
        if (layers.size == 1) {
            "layer '${layers.first().name}'"
        } else {
            "layers ${layers.joinToString { "'${it.name}'" }}"
        }

    override fun Layer.doesNotDependOn(
        layer: Layer,
        vararg layers: Layer,
    ) {
        doesNotDependOn(setOf(layer) + layers.toSet())
    }

    override fun Layer.doesNotDependOn(layers: Set<Layer>) {
        require(layers.isNotEmpty()) { "Layers set is empty." }
        requireLayerCannotBeDependentOnItself(this, layers) { "Layer '$name' cannot be dependent on itself." }

        val dependOnNothingDependency = getLayerWithDependency(this, LayerDependencyType.DEPEND_ON_NOTHING)

        if (dependOnNothingDependency != null) {
            throw KoInvalidAssertArchitectureConfigurationException(
                "Layer '$name' is already configured with no dependencies. It cannot subsequently depend on ${getLayersMessage(layers)}.",
            )
        }

        with(this@LayerDependenciesCore.layers) {
            add(this@doesNotDependOn)
            addAll(layers)
        }

        layers.forEach {
            addLayerDependency(this, LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER, it)
        }

        layerValidatorManager.validateLayerDependencies(layerDependencies)
    }

    override fun Layer.dependsOnNothing() {
        val dependOnLayerDependencies = getLayersWithDependency(this, LayerDependencyType.DEPEND_ON_LAYER)
        val dependOnLayers =
            dependOnLayerDependencies
                .mapNotNull { it.layer2 }
                .toSet()

        if (dependOnLayers.isNotEmpty()) {
            throw KoInvalidAssertArchitectureConfigurationException(
                "Layer '$name' is already configured to depend on '${getLayersMessage(dependOnLayers)}'. " +
                    "It cannot subsequently have no dependencies.",
            )
        }

        addLayerDependency(this, LayerDependencyType.DEPEND_ON_NOTHING, null)
        layers.add(this)

        layerValidatorManager.validateLayerDependencies(layerDependencies)
    }

    private fun getLayerWithDependency(
        layer: Layer,
        layerDependencyType: LayerDependencyType,
    ): LayerDependency? {
        val dependOnLayerDependency =
            layerDependencies.firstOrNull {
                it.layer1 == layer && it.dependencyType == layerDependencyType
            }
        return dependOnLayerDependency
    }

    private fun getLayersWithDependency(
        layer: Layer,
        layerDependencyType: LayerDependencyType,
    ): Set<LayerDependency> {
        val dependOnLayerDependency =
            layerDependencies.filter {
                it.layer1 == layer && it.dependencyType == layerDependencyType
            }

        return dependOnLayerDependency.toSet()
    }

    private fun addLayerDependency(
        layer1: Layer,
        layerDependencyType: LayerDependencyType,
        layer2: Layer?,
    ) {
        val result = layerDependencies.add(LayerDependency(layer1, layerDependencyType, layer2))

        if (result.not()) {
            throw KoInvalidAssertArchitectureConfigurationException(
                "Duplicate layer dependency configuration: Layer '${layer1.name}' is already configured to depend on '${layer2?.name}'.",
            )
        }
    }

    private fun requireLayerCannotBeDependentOnItself(
        layer: Layer,
        layers: Set<Layer>,
        lazyMessage: () -> String,
    ) {
        require(layers.none { it == layer }, lazyMessage)
    }
}
