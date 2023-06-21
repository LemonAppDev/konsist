package com.lemonappdev.konsist.core.architecture

open class KoArchitectureCreator {
    fun architecture(layer: Layer, vararg layers: Layer): KoArchitectureImpl = KoArchitectureImpl(layer, *layers)
}
