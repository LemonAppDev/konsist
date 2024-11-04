package com.lemonappdev.konsist.core.architecture

internal enum class LayerDependencyType {
    DEPEND_ON_NOTHING,
    DEPENDS_ON_LAYER,
    DOES_NOT_DEPEND_ON_LAYER,
    NONE,
}

