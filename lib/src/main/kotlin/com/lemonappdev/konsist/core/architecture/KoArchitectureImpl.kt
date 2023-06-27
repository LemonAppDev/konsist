package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.KoArchitecture
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.sun.tools.javac.comp.Todo

class KoArchitectureImpl(vararg layers: Layer) : KoArchitecture {
    val dependencies = mutableMapOf<Layer, Set<Layer>>()
    private val statuses = mutableMapOf<Layer, Status>()

    val allLayers = layers.toMutableList()
        .onEach { layer ->
            dependencies[layer] = setOf(layer)
            statuses[layer] = Status.NONE
        }

    override fun addDependencies(dependencies: KoArchitecture.() -> Unit): KoArchitecture {
        dependencies()
        return this
    }

    override fun Layer.dependsOn(layer: Layer, vararg layers: Layer) {
        checkIfLayerIsAddToArchitecture(this, listOf(layer) + layers)
        checkIfLayerIsDependentOnItself(this, layer, *layers)
        checkStatusOfLayer(false, this, layer, *layers)
        checkCircularDependencies(this, layer, *layers)

        dependencies[this] = (dependencies.getOrDefault(this, setOf(this))) + layer + layers
        statuses[this] = Status.DEPEND_ON_LAYER
    }

    override fun Layer.notDependOnAnyLayer() {
        checkIfLayerIsAddToArchitecture(this)
        checkStatusOfLayer(true, this)

        dependencies[this] = setOf(this)
        statuses[this] = Status.INDEPENDENT
    }

    private fun checkIfLayerIsAddToArchitecture(layer: Layer, layers: List<Layer>? = null) {
        layers?.let {
            if (layers.any { layer -> !allLayers.contains(layer) }) {
                val notAddedLayers = layers.filterNot { layer -> allLayers.contains(layer) }
                throw KoPreconditionFailedException("Layers not added to the architecture:\n${notAddedLayers.joinToString(separator = "\n")}.")
            }
        }
        if (!allLayers.contains(layer)) {
            throw KoPreconditionFailedException("Layer not added to the architecture:\n$layer.")
        }
    }

    private fun checkIfLayerIsDependentOnItself(layer: Layer, vararg layers: Layer) {
        if (layers.any { it == layer }) {
            throw KoPreconditionFailedException("Layer: $layer cannot be dependent on itself.")
        }
    }

    private fun checkStatusOfLayer(toBeIndependent: Boolean, layer: Layer, vararg layers: Layer): Boolean {
        return when (statuses[layer]) {
            Status.INDEPENDENT -> {
                if (toBeIndependent) {
                    throw KoPreconditionFailedException("Dependency that $layer should be independent was duplicated.")
                } else {
                    throw KoPreconditionFailedException("Layer: $layer was set as independent before.")
                }
            }

            Status.DEPEND_ON_LAYER -> {
                val dependency = dependencies.getOrDefault(layer, emptySet())

                if (toBeIndependent) {
                    // TODO("change error message")
                    val alreadySetLayer = dependency.first { it != layer }
                    throw KoPreconditionFailedException("Layer: $layer had a previously set dependency with $alreadySetLayer.")
                } else if (layers.any { dependency.contains(it) }) {
                    val alreadySetLayer = layers.first { dependency.contains(it) }
                    throw KoPreconditionFailedException("Dependency between $layer and $alreadySetLayer is set more than once.")
                } else {
                    true
                }
            }

            Status.NONE -> {
                true
            }

            else -> throw KoPreconditionFailedException("Unknown status of layer: $layer.")
        }
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
}

private enum class Status {
    INDEPENDENT,
    DEPEND_ON_LAYER,
    NONE,
}
