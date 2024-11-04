package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.architecture.LayerDependencies
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.core.architecture.validator.LayerValidatorManager
import com.lemonappdev.konsist.core.exception.KoInvalidAssertArchitectureConfigurationException
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

internal class LayerDependenciesCore(
    private val layerValidatorManager: LayerValidatorManager = LayerValidatorManager(),
) : LayerDependencies {
    internal val layerDependencies = mutableSetOf<LayerDependency>()
    internal var layers = mutableSetOf<Layer>()

    internal val dependsOnDependencies: Map<Layer, Set<Layer>>
        get() =
            layerDependencies
                .filter { it.dependencyType == LayerDependencyType.DEPENDS_ON_LAYER }
                .groupBy { it.layer1 }
                .mapValues { (_, dependencies) ->
                    dependencies.mapNotNull { it.layer2 }.toSet()
                }

    internal val doesNotDependsOnDependencies: Map<Layer, Set<Layer>>
        get() =
            layerDependencies
                .filter { it.dependencyType == LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER }
                .groupBy { it.layer1 }
                .mapValues { (_, dependencies) ->
                    dependencies.mapNotNull { it.layer2 }.toSet()
                }

    internal val dependsOnNothingDependencies: Set<Layer>
        get() =
            layerDependencies
                .filter { it.dependencyType == LayerDependencyType.DEPEND_ON_NOTHING }
                .map { it.layer1 }
                .toSet()

    fun checkEmptyLayersDependencies() {
        if (layers.isEmpty()) {
            throw KoPreconditionFailedException("Architecture doesn't contain layers or dependencies.")
        }
    }

    /**
     * Validate that all the layers are not empty
     *
     * @param files within the scope
     *
     * @throws KoPreconditionFailedException Layers do not contain files
     */
    fun checkLayersWithoutFiles(files: List<KoFileDeclaration>) {
        layers
            .firstOrNull { layer ->
                files.withPackage(layer.rootPackage).isEmpty()
            }?.let { invalidLayer ->
                throw KoPreconditionFailedException("Layer ${invalidLayer.name} doesn't contain any files.")
            }
    }

    override fun Layer.dependsOn(
        layer: Layer,
        vararg layers: Layer,
    ) {
        dependsOn(setOf(layer) + layers.toSet())
    }

    override fun Layer.dependsOn(layers: Set<Layer>) {
        require(layers.isNotEmpty()) { "Layers set is empty." }
        requireLayerCannotBeDependentOnItself(this, layers) { "Layer '$name' cannot be dependent on itself." }

        val dependOnNothingDependency = getLayerWithDependOnNothingDependency(this)

        if (dependOnNothingDependency != null) {
            throw KoInvalidAssertArchitectureConfigurationException(
                "Layer '$name' is already configured with no dependencies. " +
                    "It cannot subsequently depend on ${getLayersMessage(layers)}.",
            )
        }

        with(this@LayerDependenciesCore.layers) {
            add(this@dependsOn)
            addAll(layers)
        }

        layers.forEach {
            addLayerDependency(this, LayerDependencyType.DEPENDS_ON_LAYER, it)
        }

        layerValidatorManager.validateLayerDependencies(layerDependencies)
    }

    override fun Collection<Layer>.dependsOn(layer: Layer) {
        forEach { it.dependsOn(layer) }
    }

    override fun Collection<Layer>.dependsOn(layers: Set<Layer>) {
        forEach { it.dependsOn(layers) }
    }

    private fun getLayersMessage(layers: Set<Layer>) =
        if (layers.size == 1) {
            "layer '${layers.first().name}'"
        } else {
            "layers ${layers.joinToString { "'${it.name}'" }}"
        }

    override fun Layer.doesNotDependOn(
        layer: Layer,
        vararg layers: Layer,
    ) {
        doesNotDependOn(setOf(layer) + layers.toSet())
    }

    override fun Layer.doesNotDependOn(layers: Set<Layer>) {
        require(layers.isNotEmpty()) { "Layers set is empty." }
        requireLayerCannotBeDependentOnItself(this, layers) { "Layer '$name' cannot be dependent on itself." }

        val dependOnNothingDependency = getLayerWithDependOnNothingDependency(this)

        if (dependOnNothingDependency != null) {
            throw KoInvalidAssertArchitectureConfigurationException(
                "Layer '$name' is already configured with no dependencies. It cannot subsequently depend on ${getLayersMessage(layers)}.",
            )
        }

        with(this@LayerDependenciesCore.layers) {
            add(this@doesNotDependOn)
            addAll(layers)
        }

        layers.forEach {
            addLayerDependency(this, LayerDependencyType.DOES_NOT_DEPEND_ON_LAYER, it)
        }

        layerValidatorManager.validateLayerDependencies(layerDependencies)
    }

    override fun Collection<Layer>.doesNotDependOn(layer: Layer) {
        forEach { it.doesNotDependOn(layer) }
    }

    override fun Collection<Layer>.doesNotDependOn(layers: Set<Layer>) {
        forEach { it.doesNotDependOn(layers) }
    }

    override fun Layer.dependsOnNothing() {
        val dependOnLayerDependencies = getLayersWithDependOnLayerDependency(this)
        val dependOnLayers =
            dependOnLayerDependencies
                .mapNotNull { it.layer2 }
                .toSet()

        if (dependOnLayers.isNotEmpty()) {
            throw KoInvalidAssertArchitectureConfigurationException(
                "Layer '$name' is already configured to depend on ${getLayersMessage(dependOnLayers)}. " +
                    "It cannot subsequently have no dependencies.",
            )
        }

        addLayerDependency(this, LayerDependencyType.DEPEND_ON_NOTHING, null)
        layers.add(this)

        layerValidatorManager.validateLayerDependencies(layerDependencies)
    }

    override fun Collection<Layer>.dependsOnNothing() {
        forEach { it.dependsOnNothing() }
    }

    override fun Layer.include() {
        layers.add(this)
        layerDependencies.add(LayerDependency(this, LayerDependencyType.NONE, null))
    }

    override fun Collection<Layer>.include() {
        forEach { it.include() }
    }

    private fun getLayerWithDependOnNothingDependency(layer: Layer): LayerDependency? {
        val dependOnLayerDependency =
            layerDependencies.firstOrNull {
                it.layer1 == layer && it.dependencyType == LayerDependencyType.DEPEND_ON_NOTHING
            }
        return dependOnLayerDependency
    }

    private fun getLayersWithDependOnLayerDependency(layer: Layer): Set<LayerDependency> {
        val dependOnLayerDependency =
            layerDependencies.filter {
                it.layer1 == layer && it.dependencyType == LayerDependencyType.DEPENDS_ON_LAYER
            }

        return dependOnLayerDependency.toSet()
    }

    private fun addLayerDependency(
        layer1: Layer,
        layerDependencyType: LayerDependencyType,
        layer2: Layer?,
    ) {
        val result = layerDependencies.add(LayerDependency(layer1, layerDependencyType, layer2))

        if (result.not()) {
            val layerName =
                if (layer2?.name != null) {
                    "'${layer2.name}'"
                } else {
                    "nothing"
                }

            val message =
                "Duplicate layer dependency configuration: Layer '${layer1.name}' is already configured to depend on $layerName."

            throw KoInvalidAssertArchitectureConfigurationException(
                message,
            )
        }
    }

    private fun requireLayerCannotBeDependentOnItself(
        layer: Layer,
        layers: Set<Layer>,
        lazyMessage: () -> String,
    ) {
        require(layers.none { it == layer }, lazyMessage)
    }
}
