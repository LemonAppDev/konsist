package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.ArchitectureScope
import com.lemonappdev.konsist.api.architecture.KoArchitecture
import com.lemonappdev.konsist.api.container.koscope.KoScope

data class ArchitectureScopeImpl(internal val koArchitecture: KoArchitecture, internal val koScope: KoScope) : ArchitectureScope
