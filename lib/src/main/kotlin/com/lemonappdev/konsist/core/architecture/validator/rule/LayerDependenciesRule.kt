package com.lemonappdev.konsist.core.architecture.validator.rule

import com.lemonappdev.konsist.core.architecture.LayerDependency

internal interface LayerDependenciesRule {
    fun validate(layerDependencies: Set<LayerDependency>)
}
