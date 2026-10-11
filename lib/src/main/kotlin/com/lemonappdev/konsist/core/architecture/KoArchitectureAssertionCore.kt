package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.KoArchitectureAssertion
import com.lemonappdev.konsist.api.architecture.LayerDependencies
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.core.verify.assert

class KoArchitectureAssertionCore : KoArchitectureAssertion {
    override fun KoScope.assertArchitecture(layerDependenciesFunc: LayerDependencies.() -> Unit) =
        assertArchitecture(null, null, true, layerDependenciesFunc)

    override fun KoScope.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        requireAllFilesInLayers: Boolean,
        layerDependenciesFunc: LayerDependencies.() -> Unit,
    ) {
        val layerDependencies = instanceLayerDependencies(layerDependenciesFunc)
        val architectureScope = KoArchitectureScope(layerDependencies, this, requireAllFilesInLayers)
        architectureScope.assert(additionalMessage, testName)
    }

    override fun KoScope.assertArchitecture(layerDependencies: LayerDependencies) = assertArchitecture(null, null, true, layerDependencies)

    override fun KoScope.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        requireAllFilesInLayers: Boolean,
        layerDependenciesFunc: LayerDependencies,
    ) {
        KoArchitectureScope(layerDependenciesFunc, this, requireAllFilesInLayers).assert(additionalMessage, testName)
    }

    override fun List<KoFileDeclaration>.assertArchitecture(layerDependenciesFunc: LayerDependencies.() -> Unit) =
        assertArchitecture(null, null, true, layerDependenciesFunc)

    override fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        requireAllFilesInLayers: Boolean,
        layerDependenciesFunc: LayerDependencies.() -> Unit,
    ) {
        val layerDependencies = instanceLayerDependencies(layerDependenciesFunc)
        KoArchitectureFiles(layerDependencies, this, requireAllFilesInLayers).assert(additionalMessage, testName)
    }

    override fun List<KoFileDeclaration>.assertArchitecture(layerDependenciesFunc: LayerDependencies) =
        assertArchitecture(null, null, true, layerDependenciesFunc)

    override fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        requireAllFilesInLayers: Boolean,
        layerDependenciesFunc: LayerDependencies,
    ) {
        KoArchitectureFiles(layerDependenciesFunc, this, requireAllFilesInLayers).assert(additionalMessage, testName)
    }

    override fun architecture(layerDependenciesFunc: LayerDependencies.() -> Unit): LayerDependencies =
        instanceLayerDependencies(layerDependenciesFunc)

    /**
     * Obtains the dependency rules from the dependencies function literal.
     */
    private fun instanceLayerDependencies(layerDependenciesFunc: LayerDependencies.() -> Unit): LayerDependencies {
        val layerDependenciesCore = LayerDependenciesCore()
        layerDependenciesFunc(layerDependenciesCore)
        return layerDependenciesCore
    }
}
