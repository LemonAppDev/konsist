package com.lemonappdev.konsist.core.verify.failure

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration

internal data class DependsOnNotAllowedLayerDependencyFailure(
    val layer1: Layer,
    val failedFiles: Map<KoFileDeclaration, List<KoImportDeclaration>>,
    val notAllowedLayer: Layer,
)
