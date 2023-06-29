package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.DependencyRules
import com.lemonappdev.konsist.api.architecture.KoArchitectureAssertion
import com.lemonappdev.konsist.api.container.koscope.KoScope
import com.lemonappdev.konsist.core.verify.assert

class KoArchitectureAssertionImpl : KoArchitectureAssertion {
    override fun KoScope.assertArchitecture(dependencies: DependencyRules.() -> Unit) {
        val architecture = DependencyRulesImpl()
        dependencies(architecture)
        val architectureScope = KoArchitectureScope(architecture, this)
        architectureScope.assert()
    }

    override fun KoScope.assertArchitecture(dependencies: DependencyRules) {
        KoArchitectureScope(dependencies, this).assert()
    }

    override fun architecture(dependencies: DependencyRules.() -> Unit): DependencyRules {
        val koArchitecture = DependencyRulesImpl()
        dependencies(koArchitecture)
        return koArchitecture
    }
}
