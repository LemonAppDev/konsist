package com.lemonappdev.konsist.core.architecture

interface KoArchitecture {
    // remove
    val dependencies: Map<Layer, Set<Layer>>

    fun Layer.dependsOn(layer: Layer, vararg layers: Layer)

    fun Layer.dependsOnAllLayers()

    fun Layer.notDependOnAnyLayer()

    fun Layer.dependsOnAllLayersExpect(layer: Layer, vararg layers: Layer)
}
