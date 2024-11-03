package com.lemonappdev.konsist.core.architecture

internal enum class LayerDependencyType {
    DEPEND_ON_LAYER,
    DEPEND_ON_NOTHING,
    NONE,
    DOES_NOT_DEPEND_ON_LAYER,
}
