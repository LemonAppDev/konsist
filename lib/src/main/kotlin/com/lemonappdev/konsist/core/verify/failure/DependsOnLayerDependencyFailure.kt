package com.lemonappdev.konsist.core.verify.failure

import com.lemonappdev.konsist.api.architecture.Layer

internal data class DependsOnLayerDependencyFailure(
    val layer1: Layer,
    val dependsOnLayer: Layer,
)
