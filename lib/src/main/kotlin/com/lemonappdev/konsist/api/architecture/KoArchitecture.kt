package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.architecture.Layer

interface KoArchitecture {
    fun addDependencies(dependency: KoArchitecture.() -> Unit): KoArchitecture

    fun Layer.dependsOn(layer: Layer, vararg layers: Layer)

    fun Layer.dependsOnAllLayers()

    fun Layer.notDependOnAnyLayer()

    fun Layer.dependsOnAllLayersExpect(layer: Layer, vararg layers: Layer)
}
