package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.ArchitectureScope
import com.lemonappdev.konsist.api.architecture.KoArchitecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator
import com.lemonappdev.konsist.api.container.koscope.KoScope
import com.lemonappdev.konsist.core.verify.assert

class KoArchitectureCreatorImpl: KoArchitectureCreator {
    override fun KoScope.assertArchitecture(dependencies: KoArchitecture.() -> Unit) {
        val architecture = KoArchitectureImpl()
        dependencies(architecture)
        val architectureScope = ArchitectureScopeImpl(architecture, this)
        architectureScope.assert()
    }

    override fun KoScope.assertArchitecture(koArchitecture: KoArchitecture) {
        ArchitectureScopeImpl(koArchitecture, this).assert()
    }
}
