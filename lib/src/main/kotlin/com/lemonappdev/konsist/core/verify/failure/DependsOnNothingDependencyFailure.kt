package com.lemonappdev.konsist.core.verify.failure

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration

data class DependsOnNothingDependencyFailure(
    val layer: Layer,
    val failedFiles: List<KoFileDeclaration>,
)
