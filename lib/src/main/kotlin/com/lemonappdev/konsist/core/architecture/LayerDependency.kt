package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.Layer

internal data class LayerDependency(
    val layer1: Layer,
    val dependencyType: LayerDependencyType,
    val layer2: Layer? = null,
)
