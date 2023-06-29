package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException

data class LayerImpl(internal val name: String, internal val definedBy: String) : Layer {
    init {
        if (!definedBy.endsWith("..")) {
            throw KoPreconditionFailedException("Layer $name must be defined by package ending with '..'. Now: $definedBy .")
        }
    }
}
