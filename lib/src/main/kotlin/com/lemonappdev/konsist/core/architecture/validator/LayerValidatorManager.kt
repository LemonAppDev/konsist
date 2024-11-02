package com.lemonappdev.konsist.core.architecture.validator

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

internal class LayerValidatorManager {
    operator fun invoke(layers: Set<Layer>) {
        requireUniqueLayers(layers)
    }

    private fun requireUniqueLayers(layers: Set<Layer>) {
        // Using a set to ensure uniqueness based solely on each attribute.
        val uniqueNames = mutableSetOf<String>()
        val uniqueRootPackages = mutableSetOf<String>()

        layers.forEach { layer ->
            if (!uniqueNames.add(layer.name)) {
                throw KoPreconditionFailedException("""Layer name must be unique. Duplicated name: "${layer.name}"""")
            }

            if (!uniqueRootPackages.add(layer.rootPackage)) {
                throw KoPreconditionFailedException("""Layer rootPackage must be unique. Duplicated rootPackage: "${layer.rootPackage}"""")
            }
        }
    }
}
