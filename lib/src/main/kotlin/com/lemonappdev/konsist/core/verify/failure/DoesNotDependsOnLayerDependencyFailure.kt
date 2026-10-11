package com.lemonappdev.konsist.core.verify.failure

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.architecture.FileDependency

internal data class DoesNotDependsOnLayerDependencyFailure(
    val layer1: Layer,
    val failedFiles: Map<KoFileDeclaration, List<FileDependency>>,
    val doesNotDependOnLayer: Layer,
)
