package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.KoArchitecture
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

class KoArchitectureImpl(vararg layers: Layer) : KoArchitecture {
    val dependencies = mutableMapOf<Layer, Set<Layer>>()

    val allLayers = layers.toMutableList()
        .onEach { layer -> dependencies[layer] = setOf(layer) }

    override fun addDependencies(dependencies: KoArchitecture.() -> Unit): KoArchitecture {
        dependencies()
        return this
    }

    override fun Layer.dependsOn(layer: Layer, vararg layers: Layer) {
        addAllRequirements(this, layer, *layers)

        dependencies[this] = (dependencies.getOrDefault(this, setOf(this))) + layer + layers
    }

    override fun Layer.notDependOnAnyLayer() {
        checkIfLayerIsAddToArchitecture(this)

        dependencies[this] = setOf(this)
    }

    private fun checkCircularDependencies(layer: Layer, vararg layers: Layer) {
        val allLayers = layers.map {
            checkCircularDependenciesHelper(layer, it, emptyList(), emptyList())
        }

        val notEmpty = allLayers.firstOrNull { it.isNotEmpty() }

        if (notEmpty != null) {
            throw KoPreconditionFailedException(
                "Illegal circular dependencies:\n" +
                    notEmpty.filterNot { it == null }
                        .joinToString(prefix = "$layer -->\n", postfix = "$layer.", separator = "") { "$it -->\n" },
            )
        }
    }

    private fun checkCircularDependenciesHelper(
        nodeLayer: Layer,
        layerToCheck: Layer,
        alreadyChecked: List<Layer>,
        potentialCircular: List<Layer>,
    ): List<Layer?> {
        val layerToCheckDependencies = dependencies.getOrDefault(layerToCheck, emptySet()) - layerToCheck

        if (layerToCheckDependencies.isEmpty()) {
            return potentialCircular
        }

        val layersToCheck = layerToCheckDependencies.filterNot { alreadyChecked.contains(it) }

        val circularLayer = layersToCheck.firstOrNull { it == nodeLayer }

        return if (circularLayer != null) {
            potentialCircular + layerToCheck + null
        } else {
            val lists = layersToCheck.map {
                checkCircularDependenciesHelper(nodeLayer, it, alreadyChecked + layerToCheck, potentialCircular + layerToCheck)
            }

            lists.firstOrNull { it.last() == null } ?: emptyList()
        }
    }

    private fun checkIfLayerIsDependentOnItself(layer: Layer, vararg layers: Layer) {
        if (layers.any { it == layer }) {
            throw KoPreconditionFailedException("Layer: $layer cannot be dependent on itself.")
        }
    }

    private fun checkIfLayerIsAddToArchitecture(layer: Layer, layers: List<Layer>? = null) {
        layers?.let {
            if (layers.any { layer -> !allLayers.contains(layer) }) {
                val notAddedLayers = layers.filterNot { layer -> allLayers.contains(layer)  }
                throw KoPreconditionFailedException("Layers not added to the architecture:\n${notAddedLayers.joinToString(separator = "\n")}.")
            }
        }
        if (!allLayers.contains(layer)) {
            throw KoPreconditionFailedException("Layer not added to the architecture:\n$layer.")
        }
    }

    private fun addAllRequirements(layer: Layer, vararg layers: Layer) {
        checkIfLayerIsDependentOnItself(layer, *layers)
        checkIfLayerIsAddToArchitecture(layer, layers.toList())
        checkCircularDependencies(layer, *layers)
    }
}
