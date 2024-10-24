package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.DependencyRules
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

class DependencyRulesCore : DependencyRules {
    internal val positiveDependencies = mutableMapOf<Layer, Set<Layer>>()
    internal val negativeDependencies = mutableMapOf<Layer, Set<Layer>>()
    internal val statuses = mutableMapOf<Layer, Status>()

    internal var allLayers = mutableSetOf<Layer>()

    override fun Layer.dependsOn(
        layer: Layer,
        vararg layers: Layer,
    ) {
        checkLayers(layer, layers)

        with(allLayers) {
            add(this@dependsOn)
            add(layer)
            addAll(layers)
        }

        positiveDependencies[this] = (positiveDependencies.getOrDefault(this, setOf(this))) + layer + layers
        statuses[this] = Status.DEPEND_ON_LAYER

        if (statuses.getOrDefault(layer, Status.NONE) == Status.NONE) {
            statuses[layer] = Status.NONE
        }
        layers.onEach {
            if (statuses.getOrDefault(it, Status.NONE) == Status.NONE) {
                statuses[it] = Status.NONE
            }
        }
    }

    private fun Layer.checkLayers(
        layer: Layer,
        layers: Array<out Layer>
    ) {
        checkIfLayerHasTheSameValuesAsOtherLayer(this, layer, *layers)
        checkIfLayerIsDependentOnItself(this, layer, *layers)
        checkStatusOfLayer(false, this, layer, *layers)
        checkCircularDependencies(this, layer, *layers)
    }

    override fun Layer.doesNotDependOn(
        layer: Layer,
        vararg layers: Layer,
    ) {
        checkLayers(layer, layers)

        with(allLayers) {
            add(this@doesNotDependOn)
            add(layer)
            addAll(layers)
        }

        negativeDependencies[this] = setOf(layer) + layers
        statuses[this] = Status.NOT_DEPEND_ON_LAYER

        if (statuses.getOrDefault(layer, Status.NONE) == Status.NONE) {
            statuses[layer] = Status.NONE
        }
        layers.onEach {
            if (statuses.getOrDefault(it, Status.NONE) == Status.NONE) {
                statuses[it] = Status.NONE
            }
        }
    }

    override fun Layer.dependsOnNothing() {
        checkIfLayerHasTheSameValuesAsOtherLayer(this)
        checkStatusOfLayer(true, this)

        allLayers.add(this)

        positiveDependencies[this] = setOf(this)
        statuses[this] = Status.DEPENDENT_ON_NOTHING
    }

    private fun checkIfLayerIsDependentOnItself(
        layer: Layer,
        vararg layers: Layer,
    ) {
        if (layers.any { it == layer }) {
            throw KoPreconditionFailedException("Layer ${layer.name} cannot be dependent on itself.")
        }
    }

    @Suppress("detekt.ThrowsCount")
    private fun checkStatusOfLayer(
        toBeIndependent: Boolean,
        layer: Layer,
        vararg layers: Layer,
    ) {
        val layerName = layer.name
        if (statuses[layer] == Status.DEPENDENT_ON_NOTHING) {
            if (toBeIndependent) {
                throw KoPreconditionFailedException("Duplicated the dependency that $layerName layer should be depend on nothing.")
            } else {
                throw KoPreconditionFailedException(
                    "Layer $layerName was previously set as depend on nothing, " +
                        "so it cannot depend on ${layers.first().name} layer.",
                )
            }
        } else if (statuses[layer] == Status.DEPEND_ON_LAYER) {
            val dependency = positiveDependencies.getOrDefault(layer, emptySet())

            if (toBeIndependent) {
                val alreadySetLayer = dependency.first { it != layer }
                throw KoPreconditionFailedException(
                    "Layer $layerName had a dependency previously set with ${alreadySetLayer.name} layer, " +
                        "so it cannot be depend on nothing.",
                )
            } else if (layers.any { dependency.contains(it) }) {
                val alreadySetLayer = layers.first { dependency.contains(it) }
                throw KoPreconditionFailedException("Duplicated the dependency between $layerName and ${alreadySetLayer.name} layers.")
            }
        }
    }

    private fun checkCircularDependencies(
        layer: Layer,
        vararg layers: Layer,
    ) {
        val allLayers =
            layers
                .map { checkCircularDependenciesHelper(layer, it, emptyList(), emptyList()) }
                .distinct()
                .toMutableList()

        val notEmpty = allLayers.firstOrNull { it.size > 2 }

        if (notEmpty != null) {
            val layerName = layer.name
            throw KoPreconditionFailedException(
                "Illegal circular dependencies:\n" +
                    notEmpty
                        .filterNot { it == null }
                        .joinToString(
                            prefix = "Layer $layerName -->\n",
                            postfix = "Layer $layerName.",
                            separator = "",
                        ) { "Layer ${it?.name} -->\n" },
            )
        }
    }

    private fun checkCircularDependenciesHelper(
        nodeLayer: Layer,
        layerToCheck: Layer,
        alreadyChecked: List<Layer>,
        potentialCircular: List<Layer>,
    ): List<Layer?> {
        val layerToCheckDependencies = positiveDependencies.getOrDefault(layerToCheck, emptySet()) - layerToCheck

        if (layerToCheckDependencies.isEmpty()) {
            return potentialCircular
        }

        val layersToCheck = layerToCheckDependencies.filterNot { alreadyChecked.contains(it) }

        val circularLayer = layersToCheck.firstOrNull { it == nodeLayer }

        return if (circularLayer != null) {
            potentialCircular + layerToCheck + null
        } else {
            val lists =
                layersToCheck.map {
                    checkCircularDependenciesHelper(
                        nodeLayer,
                        it,
                        alreadyChecked + layerToCheck,
                        potentialCircular + layerToCheck,
                    )
                }

            lists
                .firstOrNull { it.isNotEmpty() && it.last() == null }
                .orEmpty()
        }
    }

    private fun checkIfLayerHasTheSameValuesAsOtherLayer(vararg layers: Layer) {
        val list: MutableList<Layer> =
            allLayers
                .distinct()
                .toMutableList()

        layers.forEach {
            val similarLayer =
                list.firstOrNull { layerAlreadyDefined ->
                    it != layerAlreadyDefined && (layerAlreadyDefined.name == it.name || layerAlreadyDefined.definedBy == it.definedBy)
                }

            if (similarLayer != null) {
                val value = if (similarLayer.name == it.name) "name: ${it.name}" else "definedBy: ${it.definedBy} "
                throw KoPreconditionFailedException("Layers have the same name $value.")
            } else {
                list += it
            }
        }
    }
}

internal enum class Status {
    DEPEND_ON_LAYER,
    DEPENDENT_ON_NOTHING,
    NONE,
    NOT_DEPEND_ON_LAYER,
}
