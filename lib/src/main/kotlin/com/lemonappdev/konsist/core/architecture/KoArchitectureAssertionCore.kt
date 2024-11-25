package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.KoArchitectureAssertion
import com.lemonappdev.konsist.api.architecture.LayerDependencies
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.verify.assert

class KoArchitectureAssertionCore : KoArchitectureAssertion {
    override fun KoScope.assertArchitecture(layerDependenciesFunc: LayerDependencies.() -> Unit) =
        assertArchitecture(null, null, layerDependenciesFunc)

    override fun KoScope.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        layerDependenciesFunc: LayerDependencies.() -> Unit,
    ) {
        val layerDependencies = instanceLayerDependencies(layerDependenciesFunc)
        val architectureScope = KoArchitectureScope(layerDependencies, this)
        architectureScope.assert(additionalMessage, testName)
    }

    override fun KoScope.assertArchitecture(layerDependencies: LayerDependencies) = assertArchitecture(null, null, layerDependencies)

    override fun KoScope.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        layerDependenciesFunc: LayerDependencies,
    ) {
        KoArchitectureScope(layerDependenciesFunc, this).assert(additionalMessage, testName)
    }

    override fun List<KoFileDeclaration>.assertArchitecture(layerDependenciesFunc: LayerDependencies.() -> Unit) =
        assertArchitecture(null, null, layerDependenciesFunc)

    override fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        layerDependenciesFunc: LayerDependencies.() -> Unit,
    ) {
        val layerDependencies = instanceLayerDependencies(layerDependenciesFunc)
        KoArchitectureFiles(layerDependencies, this).assert(additionalMessage, testName)
    }

    override fun List<KoFileDeclaration>.assertArchitecture(layerDependenciesFunc: LayerDependencies) =
        assertArchitecture(null, null, layerDependenciesFunc)

    override fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        layerDependenciesFunc: LayerDependencies,
    ) {
        KoArchitectureFiles(layerDependenciesFunc, this).assert(additionalMessage, testName)
    }

    override fun architecture(layerDependenciesFunc: LayerDependencies.() -> Unit): LayerDependencies =
        instanceLayerDependencies(layerDependenciesFunc)

    /**
     * Obtain the dependency rules from dependencies literal function
     */
    private fun instanceLayerDependencies(layerDependenciesFunc: LayerDependencies.() -> Unit): LayerDependencies {
        val layerDependenciesCore = LayerDependenciesCore()
        layerDependenciesFunc(layerDependenciesCore)
        return layerDependenciesCore
    }
}
