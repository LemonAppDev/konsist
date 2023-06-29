package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.ArchitectureScope
import com.lemonappdev.konsist.api.architecture.KoArchitecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator
import com.lemonappdev.konsist.api.container.koscope.KoScope

class KoArchitectureCreatorImpl: KoArchitectureCreator {
    override fun KoScope.architecture(dependencies: KoArchitecture.() -> Unit): ArchitectureScope {
        val architecture = KoArchitectureImpl()
        dependencies(architecture)
        return ArchitectureScopeImpl(architecture, this)
    }

    override fun KoScope.architecture(koArchitecture: KoArchitecture): ArchitectureScope = ArchitectureScopeImpl(koArchitecture, this)
}
