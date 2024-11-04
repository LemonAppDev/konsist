package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.LayerDependencies
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration

/**
 * Architecture Scope for
 * @param layerDependencies dependency configuration for a particular Layer
 * @param koScope Scope of declaration
 */
internal data class KoArchitectureScope(
    val layerDependencies: LayerDependencies,
    val koScope: KoScope,
)

/**
 * Architecture files for:
 *
 * @param layerDependencies dependency configuration for a particular Layer
 * @param files Files within the scope
 */
internal data class KoArchitectureFiles(
    val layerDependencies: LayerDependencies,
    val files: List<KoFileDeclaration>,
)
