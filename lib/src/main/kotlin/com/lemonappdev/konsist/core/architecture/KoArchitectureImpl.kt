package com.lemonappdev.konsist.core.architecture

class KoArchitectureImpl(vararg layers: Layer) : KoArchitecture {
    // remove
    override val dependencies = mutableMapOf<Layer, Set<Layer>>()

    private val allLayers = layers.toMutableList() // jakis check? Czy jest valid?
        .onEach { layer -> dependencies[layer] = setOf() }

    fun addDependencies(dependencies: KoArchitecture.() -> Unit): KoArchitectureImpl = this

    override fun Layer.dependsOn(layer: Layer, vararg layers: Layer) {
        require(allLayers.contains(layer) && layers.all { allLayers.contains(it) }) { "Some layer doesn't exist." }
        dependencies[this] = (dependencies[this]?.plus(layer)?.plus(layers)?.minus(this) ?: setOf()).toSet()
    }

    override fun Layer.dependsOnAllLayers() {
        dependencies[this] = (dependencies[this]?.plus(allLayers)?.minus(this) ?: setOf()).toSet()
    }

    override fun Layer.notDependOnAnyLayer() {
        dependencies[this] = emptySet()
    }

    override fun Layer.dependsOnAllLayersExpect(layer: Layer, vararg layers: Layer) {
        require(allLayers.contains(layer) && layers.all { allLayers.contains(it) }) { "Some layer doesn't exist." }

        dependencies[this] = (allLayers - this - layer - layers.toSet()).toSet()
    }
}
