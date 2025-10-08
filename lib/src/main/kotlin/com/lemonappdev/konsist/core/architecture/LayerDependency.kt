package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.Layer

internal data class LayerDependency(
    val layer1: Layer,
    val dependencyType: LayerDependencyType,
    val layer2: Layer? = null,
    val strict: Boolean? = null,
) {
    init {
        require(!((dependencyType == LayerDependencyType.DEPENDS_ON_LAYER) && layer2 == null)) {
            "layer2 cannot be null when dependency type is DEPEND_ON_LAYER"
        }
    }
}
