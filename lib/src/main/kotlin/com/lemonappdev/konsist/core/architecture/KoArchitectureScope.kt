package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.DependencyRules
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration

internal data class KoArchitectureScope(val dependencyRules: DependencyRules, val koScope: KoScope)

internal data class KoArchitectureFiles(val dependencyRules: DependencyRules, val files: List<KoFileDeclaration>)
