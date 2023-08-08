package com.lemonappdev.konsist.api.architecture

import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.util.LocationUtil

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
        if (!definedBy.endsWith(LocationUtil.WILD_CARD_SYNTAX)) {
            throw KoPreconditionFailedException("Layer $name must be defined by package ending with '..'. Now: $definedBy .")
        }
    }
}
