package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.Dependency
import com.lemonappdev.konsist.api.container.koscope.KoScope

internal data class KoArchitectureScope(internal val koArchitecture: Dependency, internal val koScope: KoScope)
