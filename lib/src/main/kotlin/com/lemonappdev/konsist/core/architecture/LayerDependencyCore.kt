package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.DependencyRules
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoInvalidAssertArchitectureConfigurationException

class LayerDependencyCore : DependencyRules {
    internal val layerDependencies = mutableListOf<LayerDependency>()
    internal var allLayers = mutableSetOf<Layer>()

    override fun Layer.dependsOn(layer: Layer, vararg layers: Layer) {
        dependsOn(setOf(layer) + layers.toSet())
    }

    override fun Layer.dependsOn(layers: Set<Layer>) {
        require(layers.isNotEmpty()) { "Layers set is empty." }
        requireLayerCannotBeDependentOnItself(this, layers) { "Layer '${name}' cannot be dependent on itself." }

        val dependOnNothingDependency = getLayerWithDependency(this, LayerDependencyType.DEPENDENTS_ON_NOTHING)

        if(dependOnNothingDependency != null) {
            throw KoInvalidAssertArchitectureConfigurationException(
                "Layer ''${name}'' is already configured with no dependencies. " +
                        "It cannot subsequently depend on ${getLayersMessage(layers)}."
            )
        }

        with(allLayers) {
            add(this@dependsOn)
            addAll(layers)
        }

        layers.forEach {
            addLayerDependency(this, LayerDependencyType.DEPEND_ON_LAYER, it)
        }
    }

    private fun getLayersMessage(layers: Set<Layer>) = if (layers.size == 1) {
        "layer '${layers.first().name}'"
    } else {
        "layers ${layers.joinToString { "'${it.name}'" }}"
    }

    override fun Layer.doesNotDependOn(layer: Layer, vararg layers: Layer) {
        doesNotDependOn(setOf(layer) + layers.toSet())
    }

    override fun Layer.doesNotDependOn(layers: Set<Layer>) {
        require(layers.isNotEmpty()) { "Layers set is empty." }
        requireLayerCannotBeDependentOnItself(this, layers) { "Layer '${name}' cannot be dependent on itself." }

        val dependOnNothingDependency = getLayerWithDependency(this, LayerDependencyType.DEPENDENTS_ON_NOTHING)

        if (dependOnNothingDependency != null) {
            throw KoInvalidAssertArchitectureConfigurationException(
                "Layer '${name}' is already configured with no dependencies. It cannot subsequently depend on ${getLayersMessage(layers)}."
            )
        }

        with(allLayers) {
            add(this@doesNotDependOn)
            addAll(layers)
        }

        layers.forEach {
            addLayerDependency(this, LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER, it)
        }
    }

    override fun Layer.dependsOnNothing() {
        val dependOnLayerDependencies = getAllLayersWithDependency(this, LayerDependencyType.DEPEND_ON_LAYER)
        val dependOnLayers = dependOnLayerDependencies
            .mapNotNull { it.layer2 }
            .toSet()

        if (dependOnLayers.isNotEmpty()) {
            throw KoInvalidAssertArchitectureConfigurationException(
                "Layer '${name}' is already configured to depend on '${getLayersMessage(dependOnLayers)}'. " +
                        "It cannot subsequently have no dependencies."
            )
        }

        addLayerDependency(this, LayerDependencyType.DEPENDENTS_ON_NOTHING, null)
        allLayers.add(this)

//        requireUniqueLayers(setOf(this))
//        requireValidLayerStatus(true, this)
//
//        allLayers.add(this)
//
//        positiveDependencies[this] = setOf(this)
//        layerDependencyTypes[this] = LayerDependencyType.DEPENDENT_ON_NOTHING
    }

    private fun getLayerWithDependency(layer: Layer, layerDependencyType: LayerDependencyType): LayerDependency? {
        val dependOnLayerDependency = layerDependencies.firstOrNull {
            it.layer1 == layer && it.dependencyType == layerDependencyType
        }
        return dependOnLayerDependency
    }

    private fun getAllLayersWithDependency(layer: Layer, layerDependencyType: LayerDependencyType): Set<LayerDependency> {
        val dependOnLayerDependency = layerDependencies.filter {
            it.layer1 == layer && it.dependencyType == layerDependencyType
        }

        return dependOnLayerDependency.toSet()
    }

    private fun addLayerDependency(
        layer1: Layer,
        layerDependencyType: LayerDependencyType,
        layer2: Layer?
    ) {
        layerDependencies.add(LayerDependency(layer1, layerDependencyType, layer2))
    }

    private fun requireLayerCannotBeDependentOnItself(
        layer: Layer,
        layers: Set<Layer>,
        lazyMessage: () -> String
    ) {
        require(layers.none { it == layer }, lazyMessage)
    }

    private fun Layer.requireValidLayerDependencies(layers: Set<Layer>) {
//        requireUniqueLayers(layers)
//        requireNoCircularDependencies(this, layers)
//        requireValidLayerStatus(false, this, layers)
    }

    @Suppress("detekt.ThrowsCount")
    private fun requireValidLayerStatus(
        toBeIndependent: Boolean,
        layer: Layer,
        layers: Set<Layer> = emptySet(),
    ) {
//        val layerName = layer.name
//        if (layerDependencyTypes[layer] == LayerDependencyType.DEPENDENT_ON_NOTHING) {
//            if (toBeIndependent) {
//                throw KoPreconditionFailedException("Duplicated the dependency that $layerName layer should be depend on nothing.")
//            } else {
//                throw KoPreconditionFailedException(
//                    "Layer $layerName was previously set as depend on nothing, " +
//                        "so it cannot depend on ${layers.first().name} layer.",
//                )
//            }
//        } else if (layerDependencyTypes[layer] == LayerDependencyType.DEPEND_ON_LAYER) {
//            val dependency = positiveDependencies.getOrDefault(layer, emptySet())
//
//            if (toBeIndependent) {
//                val alreadySetLayer = dependency.first { it != layer }
//                throw KoPreconditionFailedException(
//                    "Layer $layerName had a dependency previously set with ${alreadySetLayer.name} layer, " +
//                        "so it cannot be depend on nothing.",
//                )
//            } else if (layers.any { dependency.contains(it) }) {
//                val alreadySetLayer = layers.first { dependency.contains(it) }
//                throw KoPreconditionFailedException("Duplicated the dependency between $layerName and ${alreadySetLayer.name} layers.")
//            }
//        }
    }

    private fun requireNoCircularDependencies(
        layer: Layer,
        layers: Set<Layer>,
    ) {
//        val allLayers =
//            layers
//                .map { checkCircularDependenciesHelper(layer, it, emptyList(), emptyList()) }
//                .distinct()
//                .toMutableList()
//
//        val notEmpty = allLayers.firstOrNull { it.size > 2 }
//
//        if (notEmpty != null) {
//            val layerName = layer.name
//            throw KoPreconditionFailedException(
//                "Illegal circular dependencies:\n" +
//                    notEmpty
//                        .filterNot { it == null }
//                        .joinToString(
//                            prefix = "Layer $layerName -->\n",
//                            postfix = "Layer $layerName.",
//                            separator = "",
//                        ) { "Layer ${it?.name} -->\n" },
//            )
//        }
    }

    private fun checkCircularDependenciesHelper(
        nodeLayer: Layer,
        layerToCheck: Layer,
        alreadyChecked: List<Layer>,
        potentialCircular: List<Layer>,
    ) {
//        val layerToCheckDependencies = positiveDependencies.getOrDefault(layerToCheck, emptySet()) - layerToCheck
//
//        if (layerToCheckDependencies.isEmpty()) {
//            return potentialCircular
//        }
//
//        val layersToCheck = layerToCheckDependencies.filterNot { alreadyChecked.contains(it) }
//
//        val circularLayer = layersToCheck.firstOrNull { it == nodeLayer }
//
//        return if (circularLayer != null) {
//            potentialCircular + layerToCheck + null
//        } else {
//            val lists =
//                layersToCheck.map {
//                    checkCircularDependenciesHelper(
//                        nodeLayer,
//                        it,
//                        alreadyChecked + layerToCheck,
//                        potentialCircular + layerToCheck,
//                    )
//                }
//
//            lists
//                .firstOrNull { it.isNotEmpty() && it.last() == null }
//                .orEmpty()
//        }
    }

    private fun requireUniqueLayers(layers: Set<Layer>) {
//        // Using a set to ensure uniqueness based solely on each attribute.
//        val uniqueNames = mutableSetOf<String>()
//        val uniqueRootPackages = mutableSetOf<String>()
//
//        layers.forEach { layer ->
//            if (!uniqueNames.add(layer.name)) {
//                throw KoPreconditionFailedException("""Layer name must be unique. Duplicated name: "${layer.name}"""")
//            }
//
//            if (!uniqueRootPackages.add(layer.rootPackage)) {
//                throw KoPreconditionFailedException("""Layer rootPackage must be unique. Duplicated rootPackage: "${layer.rootPackage}"""")
//            }
//
//            allLayers.add(layer)
//        }
    }
}

