package com.lemonappdev.konsist.core.architecture.validator.rule

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.architecture.LayerDependency
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

internal class UniqueLayerRule : LayerDependenciesRule {
    override fun validate(dependencies: Set<LayerDependency>) {
        val layers = extractLayers(dependencies)
        requireUniqueLayers(layers)
    }

    private fun extractLayers(dependencies: Set<LayerDependency>): List<Layer> =
        dependencies
            .flatMap { listOfNotNull(it.layer1, it.layer2) }
            .distinctBy { System.identityHashCode(it) } // Referential Equality

    private fun requireUniqueLayers(layers: List<Layer>) {
        val nameViolations = mutableListOf<String>()
        val packageViolations = mutableListOf<String>()
        val seenNames = mutableSetOf<String>()
        val seenPackages = mutableSetOf<String>()

        layers.forEach { layer ->
            if (!seenNames.add(layer.name)) {
                nameViolations.add("Layer name must be unique. Duplicated name: '${layer.name}'.")
            }

            if (!seenPackages.add(layer.rootPackage)) {
                packageViolations.add("Layer rootPackage must be unique. Duplicated rootPackage: '${layer.rootPackage}'.")
            }
        }

        val violations = nameViolations + packageViolations

        if (violations.isNotEmpty()) {
            val errorMessage = "Invalid layer configurations:\n${violations.joinToString("\n")}"
            throw KoPreconditionFailedException(errorMessage)
        }
    }
}
