package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.LayerDependencies
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration

/**
 * Architecture scope: layer dependencies and the scope they are checked against.
 *
 * @param layerDependencies dependency configuration for a particular Layer
 * @param koScope Scope of declaration
 * @param requireAllFilesInLayers Whether every file must reside in a declared layer
 */
internal data class KoArchitectureScope(
    val layerDependencies: LayerDependencies,
    val koScope: KoScope,
    val requireAllFilesInLayers: Boolean,
)

/**
 * Architecture files: layer dependencies and the files they are checked against.
 *
 * @param layerDependencies dependency configuration for a particular Layer
 * @param files Files within the scope
 * @param requireAllFilesInLayers Whether every file must reside in a declared layer
 */
internal data class KoArchitectureFiles(
    val layerDependencies: LayerDependencies,
    val files: List<KoFileDeclaration>,
    val requireAllFilesInLayers: Boolean,
)
