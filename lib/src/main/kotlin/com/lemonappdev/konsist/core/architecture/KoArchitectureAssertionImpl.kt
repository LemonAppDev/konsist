package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.Dependency
import com.lemonappdev.konsist.api.architecture.KoArchitectureAssertion
import com.lemonappdev.konsist.api.container.koscope.KoScope
import com.lemonappdev.konsist.core.verify.assert

class KoArchitectureAssertionImpl: KoArchitectureAssertion {
    override fun KoScope.assertArchitecture(dependencies: Dependency.() -> Unit) {
        val architecture = DependencyImpl()
        dependencies(architecture)
        val architectureScope = KoArchitectureScope(architecture, this)
        architectureScope.assert()
    }

    override fun KoScope.assertArchitecture(dependencies: Dependency) {
        KoArchitectureScope(dependencies, this).assert()
    }

    override fun architecture(dependencies: Dependency.() -> Unit): Dependency {
        val koArchitecture = DependencyImpl()
        dependencies(koArchitecture)
        return koArchitecture
    }
}
