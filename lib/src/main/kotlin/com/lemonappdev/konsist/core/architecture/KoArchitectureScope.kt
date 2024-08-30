package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.DependencyRules
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration

/**
 * Architecture Scope for
 * @param dependencyRules dependency configuration for a particular Layer
 * @param koScope Scope of declaration
 */
internal data class KoArchitectureScope(
    val dependencyRules: DependencyRules,
    val koScope: KoScope,
)

/**
 * Architecture files for:
 *
 * @param dependencyRules dependency configuration for a particular Layer
 * @param files Files within the scope
 */
internal data class KoArchitectureFiles(
    val dependencyRules: DependencyRules,
    val files: List<KoFileDeclaration>,
)
