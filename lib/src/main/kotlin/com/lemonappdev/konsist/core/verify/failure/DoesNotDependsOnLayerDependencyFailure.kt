package com.lemonappdev.konsist.core.verify.failure

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration

internal data class DoesNotDependsOnLayerDependencyFailure(
    val layer1: Layer,
    val failedFiles: List<KoFileDeclaration>,
    val doesNotDependOnLayer: Layer,
)
