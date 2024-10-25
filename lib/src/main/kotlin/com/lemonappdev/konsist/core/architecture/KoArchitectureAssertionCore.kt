package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.DependencyRules
import com.lemonappdev.konsist.api.architecture.KoArchitectureAssertion
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.verify.assert

class KoArchitectureAssertionCore : KoArchitectureAssertion {
    override fun KoScope.assertArchitecture(dependencies: DependencyRules.() -> Unit) = assertArchitecture(null, null, dependencies)

    override fun KoScope.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        dependencies: DependencyRules.() -> Unit,
    ) {
        val dependencyRules = instanceDependencyRules(dependencies = dependencies)
        val architectureScope = KoArchitectureScope(dependencyRules, this)
        architectureScope.assert(additionalMessage, testName)
    }

    override fun KoScope.assertArchitecture(dependencies: DependencyRules) = assertArchitecture(null, null, dependencies)

    override fun KoScope.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        dependencies: DependencyRules,
    ) {
        KoArchitectureScope(dependencies, this).assert(additionalMessage, testName)
    }

    override fun List<KoFileDeclaration>.assertArchitecture(dependencies: DependencyRules.() -> Unit) =
        assertArchitecture(null, null, dependencies)

    override fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        dependencies: DependencyRules.() -> Unit,
    ) {
        val dependencyRules = instanceDependencyRules(dependencies = dependencies)
        KoArchitectureFiles(dependencyRules, this).assert(additionalMessage, testName)
    }

    override fun List<KoFileDeclaration>.assertArchitecture(dependencies: DependencyRules) = assertArchitecture(null, null, dependencies)

    override fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        dependencies: DependencyRules,
    ) {
        KoArchitectureFiles(dependencies, this).assert(additionalMessage, testName)
    }

    override fun architecture(dependencies: DependencyRules.() -> Unit): DependencyRules =
        instanceDependencyRules(dependencies = dependencies)

    /**
     * Obtain the dependency rules from dependencies literal function
     */
    private fun instanceDependencyRules(dependencies: DependencyRules.() -> Unit): DependencyRules {
        val dependencyRules = LayerDependencyCore()
        dependencies(dependencyRules)
        return dependencyRules
    }
}
