package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.DependencyRules
import com.lemonappdev.konsist.api.architecture.KoArchitectureAssertion
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.verify.assert

class KoArchitectureAssertionCore : KoArchitectureAssertion {
    override fun KoScope.assertArchitecture(dependencies: DependencyRules.() -> Unit) {
        val dependencyRules = instanceDependencyRules(dependencies = dependencies)
        val architectureScope = KoArchitectureScope(dependencyRules, this)
        architectureScope.assert()
    }

    override fun KoScope.assertArchitecture(dependencies: DependencyRules) {
        KoArchitectureScope(dependencies, this).assert()
    }

    override fun List<KoFileDeclaration>.assertArchitecture(dependencies: DependencyRules.() -> Unit) {
        val dependencyRules = instanceDependencyRules(dependencies = dependencies)
        KoArchitectureFiles(dependencyRules, this).assert()
    }

    override fun List<KoFileDeclaration>.assertArchitecture(dependencies: DependencyRules) {
        KoArchitectureFiles(dependencies, this).assert()
    }

    override fun architecture(dependencies: DependencyRules.() -> Unit): DependencyRules =
        instanceDependencyRules(dependencies = dependencies)

    /**
     * Obtain the dependency rules from dependencies literal function
     */
    private fun instanceDependencyRules(dependencies: DependencyRules.() -> Unit): DependencyRules {
        val dependencyRules = DependencyRulesCore()
        dependencies(dependencyRules)
        return dependencyRules
    }
}
