package com.lemonappdev.konsist.core.architecture

internal enum class LayerDependencyType {
    DEPENDS_ON_LAYER,
    DEPEND_ON_NOTHING,
    DOES_NOT_DEPEND_ON_LAYER,
    NONE,
}
