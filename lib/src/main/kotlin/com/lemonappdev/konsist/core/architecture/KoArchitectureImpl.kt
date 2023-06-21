package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.KoArchitecture

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

    override fun Layer.dependsOnAllLayersExpect(layer: Layer, vararg layers: Layer) {
        addAllRequirements(this, layer, *layers)

        dependencies[this] = (allLayers - layer - layers.toSet()).toSet()
    }

    private fun checkCircularDependencies(layer: Layer, vararg addedLayers: Layer) {
        val circularDependencies: MutableList<Pair<Layer, Layer>> = mutableListOf()
        val value = addedLayers.map {
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
        require(value.none { !it }) {
            "Illegal circular dependencies (${circularDependencies.size}):\n" +
                circularDependencies.joinToString(separator = "\n") { "${it.first} with ${it.second}" }
        }
    }

    private fun checkIfLayerIsDependentOnItself(layer: Layer, vararg addedLayers: Layer) =
        require(addedLayers.none { it == layer }) { "Layer: $layer cannot be dependent on itself." }

    private fun checkIfLayerIsAddToArchitecture(layer: Layer, addedLayers: List<Layer>? = null) {
        addedLayers?.let {
            require(addedLayers.all { allLayers.contains(it) }) { "Layers: $it is not add to the architecture." }
        }
        require(allLayers.contains(layer)) { "Layer: $layer is not add to the architecture." }
    }

    private fun addAllRequirements(layer: Layer, vararg addedLayers: Layer) {
        checkIfLayerIsDependentOnItself(layer, *addedLayers)
        checkIfLayerIsAddToArchitecture(layer, addedLayers.toList())
        checkCircularDependencies(layer, *addedLayers)
    }
}
