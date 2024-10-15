package com.lemonappdev.konsist.core.architecture

internal enum class LayerDependencyType {
    DEPEND_ON_LAYER,
    DEPENDENT_ON_NOTHING,
    NONE,
    NOT_DEPEND_ON_LAYER,
}
