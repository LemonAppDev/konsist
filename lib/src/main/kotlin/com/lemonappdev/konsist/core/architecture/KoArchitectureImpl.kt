package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.KoArchitecture
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

class KoArchitectureImpl(vararg layers: Layer) : KoArchitecture {
    val dependencies = mutableMapOf<Layer, Set<Layer>>()

    val allLayers = layers.toMutableList() // jakis check? Czy jest valid?
        .onEach { layer -> dependencies[layer] = setOf(layer) }

    override fun addDependencies(dependencies: KoArchitecture.() -> Unit): KoArchitecture {
        dependencies()
        return this
    }

    override fun Layer.dependsOn(layer: Layer, vararg layers: Layer) {
        addAllRequirements(this, layer, *layers)

        dependencies[this] = (dependencies.getOrDefault(this, setOf(this))) + layer + layers
    }

    override fun Layer.dependsOnAllLayers() {
        checkIfLayerIsAddToArchitecture(this)
        checkCircularDependencies(this, *allLayers.toTypedArray())

        dependencies[this] = allLayers.toSet()
    }

    override fun Layer.notDependOnAnyLayer() {
        checkIfLayerIsAddToArchitecture(this)

        dependencies[this] = setOf(this)
    }

    private fun checkCircularDependencies(layer: Layer, vararg addedLayers: Layer) {
        val circularDependencies: MutableList<Pair<Layer, Layer>> = mutableListOf()
        val allLayers = getLayers(*addedLayers)

        val value = allLayers.map {
            if (it == layer) {
                true
            } else {
                when (val value = dependencies.getOrDefault(it, null)) {
                    null -> true
                    else -> {
                        circularDependencies.add(Pair(layer, it))
                        !value.contains(layer)
                    }
                }
            }
        }

        if (value.any { !it }) {
            throw KoPreconditionFailedException(
                "Illegal circular dependencies (${circularDependencies.size}):\n" +
                    circularDependencies.joinToString(separator = "\n") { "${it.first} with ${it.second}" },
            )
        }
    }

    private fun getLayers(vararg addedLayers: Layer, all: List<Layer> = emptyList()): List<Layer> {
        val layers = mutableListOf<Layer>()
        val map = mutableMapOf<Layer, Set<Layer>>()

        addedLayers.forEach {
            if (!all.contains(it)) {
                layers += it
                map[it] = dependencies.getOrDefault(it, emptySet())
            }
        }

        val newLayers = map.values.flatten().toSet()

        if (newLayers.toList().isNotEmpty()) {
            layers += getLayers(*newLayers.toTypedArray(), all = all + layers)
        }

        return layers
    }

    private fun checkIfLayerIsDependentOnItself(layer: Layer, vararg addedLayers: Layer) {
        if (addedLayers.any { it == layer }) {
            throw KoPreconditionFailedException("Layer: $layer cannot be dependent on itself.")
        }
    }

    private fun checkIfLayerIsAddToArchitecture(layer: Layer, addedLayers: List<Layer>? = null) {
        addedLayers?.let {
            if (addedLayers.any { layer -> !allLayers.contains(layer) }) {
                throw KoPreconditionFailedException("Layers: $it is not add to the architecture.")
            }
        }
        if (!allLayers.contains(layer)) {
            throw KoPreconditionFailedException("Layer: $layer is not add to the architecture.")
        }
    }

    private fun addAllRequirements(layer: Layer, vararg addedLayers: Layer) {
        checkIfLayerIsDependentOnItself(layer, *addedLayers)
        checkIfLayerIsAddToArchitecture(layer, addedLayers.toList())
        checkCircularDependencies(layer, *addedLayers)
    }
}
