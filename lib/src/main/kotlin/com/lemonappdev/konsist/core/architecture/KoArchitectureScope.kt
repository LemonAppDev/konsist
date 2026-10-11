package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.LayerDependencies
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration

/**
 * Architecture scope: layer dependencies and the scope they are checked against.
 *
 * @param layerDependencies dependency configuration for a particular Layer
 * @param koScope Scope of declaration
 * @param allowFilesOutsideLayers Whether files that reside in no declared layer are ignored
 */
internal data class KoArchitectureScope(
    val layerDependencies: LayerDependencies,
    val koScope: KoScope,
    val allowFilesOutsideLayers: Boolean,
)

/**
 * Architecture files: layer dependencies and the files they are checked against.
 *
 * @param layerDependencies dependency configuration for a particular Layer
 * @param files Files within the scope
 * @param allowFilesOutsideLayers Whether files that reside in no declared layer are ignored
 */
internal data class KoArchitectureFiles(
    val layerDependencies: LayerDependencies,
    val files: List<KoFileDeclaration>,
    val allowFilesOutsideLayers: Boolean,
)
