package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

/**
 * Represents a layer within an architecture.
 *
 * @param name The name of the layer.
 * @param definedBy The package or module that defines the layer. It should end with '..'.
 *
 * @throws KoPreconditionFailedException if the [definedBy] package does not end with '..'.
 */
data class Layer(internal val name: String, internal val definedBy: String) {
    init {
        if (!definedBy.endsWith("..")) {
            throw KoPreconditionFailedException("Layer $name must be defined by package ending with '..'. Now: $definedBy .")
        }

        val similarLayer = layers.firstOrNull { it.name == name || it.definedBy == definedBy }

        if(similarLayer != null) {
            val value = if (name == similarLayer.name) "name: $name" else "definedBy: $definedBy "
                throw KoPreconditionFailedException("Already exists layer with the same $value.")
        } else {
        layers += this
        }
    }

    companion object {
        private val layers = mutableListOf<Layer>()
    }
}
