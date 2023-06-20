package com.lemonappdev.konsist.core.architecture

class KoArchitectureImpl(vararg layers: Layer): KoArchitecture {
    val allLayers = layers.toMutableList() // jakis check? Czy jest valid?
    // musimy zrobic zeby sie odswiezala
    val dependencies = mutableMapOf<Layer, List<Layer>>()

    /*
    fun addLayers(layer: Layer, vararg layers: Layer): KoArchitecture {
        // tutaj ten wyjątek ładniej rozbić i w error message dodać which layer
        require(
            projectKotlinFiles.any {
                it.resideInPath(layer.isDefinedBy) && layers.all { layer -> it.resideInPath(layer.isDefinedBy) }
            }
        ) { "This layer doesn't exist."}

        allLayers.also {
            it.add(layer)
            layers.forEach { layer -> it.add(layer) }
        }
        return this
    }
     */

    fun addDependencies(dependencies: KoArchitecture.() -> Unit): KoArchitectureImpl {
        this.dependencies()
        return this
    }

    override fun Layer.dependsOn(layer: Layer, vararg layers: Layer) {
        require(allLayers.contains(layer) && layers.all { allLayers.contains(it) }) { "Some layer doesn't exist." }
        addDependentLayers(layer, *layers)
    }

    override fun Layer.dependsOnAllLayers() {
        addDependentLayers(allLayers)
    }

    override fun Layer.notDependOnAnyLayer() {
        clearDependentLayers()
    }

    override fun Layer.dependsOnAllLayersExpect(layer: Layer, vararg layers: Layer) {
        require(allLayers.contains(layer) && layers.all { allLayers.contains(it) }) { "Some layer doesn't exist." }

        addDependentLayers(allLayers)
        removeDependentLayers(layer, *layers)
    }
}
