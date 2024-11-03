package com.lemonappdev.konsist.core.architecture.validator.rule

import com.lemonappdev.konsist.core.architecture.LayerDependency
import com.lemonappdev.konsist.core.architecture.LayerDependencyType
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

internal class DependentOnNothingThenDependOnLayerDependenciesRule : LayerDependenciesRule {
    override fun validate(layerDependencies: Set<LayerDependency>) {
        val independentLayers =
            layerDependencies
                .filter { it.dependencyType == LayerDependencyType.DEPEND_ON_NOTHING }
                .map { it.layer1 }

        val conflictingDependencies =
            layerDependencies
                .filter { it.dependencyType == LayerDependencyType.DEPEND_ON_LAYER && independentLayers.contains(it.layer1) }

        if (conflictingDependencies.isNotEmpty()) {
            val violations =
                conflictingDependencies
                    .joinToString("\n") { dependency ->
                        "Layer ${dependency.layer1.name} was previously set as depend on nothing, " +
                            "so it cannot depend on ${dependency.layer2?.name} layer."
                    }

            val errorMessage = "Conflicting dependency configurations:\n$violations"
            throw KoPreconditionFailedException(errorMessage)
        }
    }
}
