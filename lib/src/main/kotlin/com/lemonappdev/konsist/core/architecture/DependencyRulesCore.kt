package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.DependencyRules
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

class DependencyRulesCore : DependencyRules {
    internal val positiveDependencies = mutableMapOf<Layer, Set<Layer>>()
    internal val negativeDependencies = mutableMapOf<Layer, Set<Layer>>()
    internal val layerDependencyTypes = mutableMapOf<Layer, LayerDependencyType>()

    internal val uniqueLayers = mutableSetOf<Layer>()

    override fun Layer.dependsOn(
        layer: Layer,
        vararg layers: Layer,
    ) {
        requireUniqueLayers(this, layer, *layers)
        requireNotDependentOnItself(this, layer, *layers)
        requireLayerStatusConsistency(false, this, layer, *layers)
        requireNoCilcularDependencies(this, layer, *layers)

        uniqueLayers.apply {
            add(layer)
            add(this@dependsOn)
            addAll(layers)
        }

        positiveDependencies[this] = (positiveDependencies.getOrDefault(this, setOf(this))) + layer + layers
        layerDependencyTypes[this] = LayerDependencyType.DEPEND_ON_LAYER

        val allLayers = listOf(layer) + layers + this

        allLayers.forEach { dependency ->
            layerDependencyTypes.putIfAbsent(dependency, LayerDependencyType.NONE)
        }
    }

    override fun Layer.doesNotDependOn(
        layer: Layer,
        vararg layers: Layer,
    ) {
        requireUniqueLayers(this, layer, *layers)
        requireNotDependentOnItself(this, layer, *layers)
        requireLayerStatusConsistency(false, this, layer, *layers)
        requireNoCilcularDependencies(this, layer, *layers)

        uniqueLayers.apply {
            add(layer)
            add(this@doesNotDependOn)
            addAll(layers)
        }

        negativeDependencies[this] = setOf(layer) + layers
        layerDependencyTypes[this] = LayerDependencyType.NOT_DEPEND_ON_LAYER

        if (layerDependencyTypes.getOrDefault(layer, LayerDependencyType.NONE) == LayerDependencyType.NONE) {
            layerDependencyTypes[layer] = LayerDependencyType.NONE
        }

        layers.onEach {
            if (layerDependencyTypes.getOrDefault(it, LayerDependencyType.NONE) == LayerDependencyType.NONE) {
                layerDependencyTypes[it] = LayerDependencyType.NONE
            }
        }
    }

    override fun Layer.dependsOnNothing() {
        requireUniqueLayers(this)
        requireLayerStatusConsistency(true, this)

        uniqueLayers.add(this)
        positiveDependencies[this] = setOf(this)
        layerDependencyTypes[this] = LayerDependencyType.DEPENDENT_ON_NOTHING
    }

    private fun requireNotDependentOnItself(
        layer: Layer,
        vararg layers: Layer,
    ) {
        if (layers.any { it == layer }) {
            throw KoPreconditionFailedException("Layer ${layer.name} cannot be dependent on itself.")
        }
    }

    private fun requireLayerStatusConsistency(
        shouldBeIndependent: Boolean,
        layer: Layer,
        vararg dependentLayers: Layer,
    ) {
        val layerName = layer.name
        when (layerDependencyTypes[layer]) {
            LayerDependencyType.DEPENDENT_ON_NOTHING -> handleIndependentLayerConflict(shouldBeIndependent, layerName, dependentLayers)
            LayerDependencyType.DEPEND_ON_LAYER -> handleDependentLayerConflict(shouldBeIndependent, layer, layerName, dependentLayers)
            else -> {} // No action needed for other statuses
        }
    }

    private fun handleIndependentLayerConflict(shouldBeIndependent: Boolean, layerName: String, dependentLayers: Array<out Layer>) {
        if (shouldBeIndependent) {
            throw KoPreconditionFailedException("Redundant requirement: $layerName layer is already set to be independent.")
        } else {
            throw KoPreconditionFailedException(
                "Conflict: $layerName was previously set as independent, " +
                        "so it cannot depend on ${dependentLayers.first().name} layer."
            )
        }
    }

    private fun handleDependentLayerConflict(shouldBeIndependent: Boolean, layer: Layer, layerName: String, dependentLayers: Array<out Layer>) {
        val existingDependencies = positiveDependencies.getOrDefault(layer, emptySet())

        if (shouldBeIndependent) {
            val existingDependency = existingDependencies.first { it != layer }
            throw KoPreconditionFailedException(
                "Conflict: $layerName already depends on ${existingDependency.name} layer, " +
                        "so it cannot be set as independent."
            )
        } else {
            val conflictingLayer = dependentLayers.firstOrNull { existingDependencies.contains(it) }
            if (conflictingLayer != null) {
                throw KoPreconditionFailedException("Redundant dependency between $layerName and ${conflictingLayer.name} layers.")
            }
        }
    }

    private fun requireNoCilcularDependencies(
        layer: Layer,
        vararg layers: Layer,
    ) {
        val allLayers = layers
                .map { checkCircularDependenciesHelper(layer, it, emptyList(), emptyList()) }
                .toSet()

        val notEmpty = allLayers.firstOrNull { it.size > 2 }

        if (notEmpty != null) {
            val layerName = layer.name
            throw KoPreconditionFailedException(
                "Illegal circular dependencies:\n" +
                    notEmpty
                        .filterNot { it == null }
                        .joinToString(
                            prefix = "Layer $layerName -->\n",
                            postfix = "Layer $layerName.",
                            separator = "",
                        ) { "Layer ${it?.name} -->\n" },
            )
        }
    }

    private fun checkCircularDependenciesHelper(
        nodeLayer: Layer,
        layerToCheck: Layer,
        alreadyChecked: List<Layer>,
        potentialCircular: List<Layer>,
    ): List<Layer?> {
        val layerToCheckDependencies = positiveDependencies.getOrDefault(layerToCheck, emptySet()) - layerToCheck

        if (layerToCheckDependencies.isEmpty()) {
            return potentialCircular
        }

        val layersToCheck = layerToCheckDependencies.filterNot { alreadyChecked.contains(it) }

        val circularLayer = layersToCheck.firstOrNull { it == nodeLayer }

        return if (circularLayer != null) {
            potentialCircular + layerToCheck + null
        } else {
            val lists =
                layersToCheck.map {
                    checkCircularDependenciesHelper(
                        nodeLayer,
                        it,
                        alreadyChecked + layerToCheck,
                        potentialCircular + layerToCheck,
                    )
                }

            lists
                .firstOrNull { it.isNotEmpty() && it.last() == null }
                .orEmpty()
        }
    }

    private fun requireUniqueLayers(vararg layers: Layer) {
        layers.forEach {
            val similarLayer =
                uniqueLayers.firstOrNull { layerAlreadyDefined ->
                    it != layerAlreadyDefined && (layerAlreadyDefined.name == it.name || layerAlreadyDefined.definedBy == it.definedBy)
                }

            if (similarLayer != null) {
                val value = if (similarLayer.name == it.name) "name: ${it.name}" else "definedBy: ${it.definedBy} "
                throw KoPreconditionFailedException("Layers have the same name $value.")
            }
        }
    }
}

