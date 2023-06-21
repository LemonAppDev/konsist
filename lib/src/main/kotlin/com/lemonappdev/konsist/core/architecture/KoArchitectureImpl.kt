package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.KoArchitecture

class KoArchitectureImpl(vararg layers: Layer) : KoArchitecture {
    // remove
    val dependencies = mutableMapOf<Layer, Set<Layer>?>()

    val allLayers = layers.toMutableList() // jakis check? Czy jest valid?
        .onEach { layer -> dependencies[layer] = null }

    override fun addDependencies(dependency: KoArchitecture.() -> Unit): KoArchitecture {
        dependency()
        return this
    }

    override fun Layer.dependsOn(layer: Layer, vararg layers: Layer) {
        addRequirements(this, layer, *layers)

        dependencies[this] = (dependencies[this]?.plus(layer)?.plus(layers) ?: setOf(this).plus(layer).plus(layers)).toSet()
    }

    override fun Layer.dependsOnAllLayers() {
        require(allLayers.contains(this)) { "Layer: $this is not add to the architecture." }
        require(allLayers.all { checkCircularDependency(it, this) }) { "Illegal circular dependency" }
        dependencies[this] = allLayers.toSet()
    }

    override fun Layer.notDependOnAnyLayer() {
        require(allLayers.contains(this)) { "Layer: $this is not add to the architecture." }
        dependencies[this] = setOf(this)
    }

    override fun Layer.dependsOnAllLayersExpect(layer: Layer, vararg layers: Layer) {
        addRequirements(this, layer, *layers)

        dependencies[this] = (allLayers - layer - layers.toSet()).toSet()
    }

    private fun checkCircularDependency(layer1: Layer, layer2: Layer): Boolean {
        return if (layer1 == layer2) {
            true
        } else {
            when (val value = dependencies.getOrDefault(layer1, null)) {
                null -> true
                else -> !value.contains(layer2)
            }
        }
    }

    private fun addRequirements(layer: Layer, vararg addedLayers: Layer) {
        require(allLayers.contains(layer)) { "Layer: $layer is not add to the architecture." }
        require(addedLayers.all { allLayers.contains(it) }) { "Some layer doesn't exist." }
        require(addedLayers.all { checkCircularDependency(it, layer) }) { "Illegal circular dependency" }
    }
}
