package com.lemonappdev.konsist.core.architecture

class KoArchitectureImpl(vararg layers: Layer) : KoArchitecture {
    // remove
    override val dependencies = mutableMapOf<Layer, Set<Layer>>()

    override val allLayers = layers.toMutableList() // jakis check? Czy jest valid?
        .onEach { layer -> dependencies[layer] = setOf(layer) }

    fun addDependencies(dependency: KoArchitecture.() -> Unit): KoArchitectureImpl {
        dependency()
        return this
    }

    override fun Layer.dependsOn(layer: Layer, vararg layers: Layer) {
        require(allLayers.contains(layer) && layers.all { allLayers.contains(it) }) { "Some layer doesn't exist." }
        require(checkCircularDependency(layer, this) && layers.all { checkCircularDependency(it, this) }) { "Illegal circular dependency" }

        dependencies[this] = (dependencies[this]?.plus(layer)?.plus(layers) ?: setOf(this)).toSet()
    }

    override fun Layer.dependsOnAllLayers() {
        require(allLayers.all { checkCircularDependency(it, this) }) { "Illegal circular dependency" }
        dependencies[this] = allLayers.toSet()
    }

    override fun Layer.notDependOnAnyLayer() {
        dependencies[this] = setOf(this)
    }

    override fun Layer.dependsOnAllLayersExpect(layer: Layer, vararg layers: Layer) {
        require(allLayers.contains(layer) && layers.all { allLayers.contains(it) }) { "Some layer doesn't exist." }
        require(checkCircularDependency(layer, this) && layers.all { checkCircularDependency(it, this) }) { "Illegal circular dependency" }

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
}
