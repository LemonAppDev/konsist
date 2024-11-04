package com.lemonappdev.konsist.core.architecture.validator.rule

import com.lemonappdev.konsist.core.architecture.LayerDependency
import com.lemonappdev.konsist.core.architecture.LayerDependencyType
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

internal class DependOnLayerThenDependentOnNothingRule : LayerDependenciesRule {
    override fun validate(layerDependencies: Set<LayerDependency>) {
        val layersWithDependencies =
            layerDependencies
                .filter { it.dependencyType == LayerDependencyType.DEPENDS_ON_LAYER }
                .map { it.layer1 }

        val conflictingDependencies =
            layerDependencies
                .filter { it.dependencyType == LayerDependencyType.DEPEND_ON_NOTHING && layersWithDependencies.contains(it.layer1) }

        if (conflictingDependencies.isNotEmpty()) {
            val violations =
                conflictingDependencies
                    .joinToString("\n") { dependency ->
                        "Layer ${dependency.layer1.name} was previously set to depend on other layers, " +
                            "so it cannot be set as dependent on nothing."
                    }

            val errorMessage = "Conflicting dependency configurations:\n$violations"
            throw KoPreconditionFailedException(errorMessage)
        }
    }
}
