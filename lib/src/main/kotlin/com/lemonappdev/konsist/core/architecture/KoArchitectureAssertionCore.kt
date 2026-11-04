package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.KoArchitectureAssertion
import com.lemonappdev.konsist.api.architecture.LayerDependencies
import com.lemonappdev.konsist.api.container.KoScope
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.verify.KoRule
import com.lemonappdev.konsist.core.verify.assert

class KoArchitectureAssertionCore : KoArchitectureAssertion {
    override fun KoScope.assertArchitecture(layerDependenciesFunc: LayerDependencies.() -> Unit) =
        assertArchitecture(null, null, false, null, layerDependenciesFunc)

    override fun KoScope.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        allowFilesOutsideLayers: Boolean,
        rule: KoRule?,
        layerDependenciesFunc: LayerDependencies.() -> Unit,
    ) {
        val layerDependencies = instanceLayerDependencies(layerDependenciesFunc)
        val architectureScope = KoArchitectureScope(layerDependencies, this, allowFilesOutsideLayers)
        architectureScope.assert(additionalMessage, testName, rule)
    }

    override fun KoScope.assertArchitecture(layerDependencies: LayerDependencies) =
        assertArchitecture(null, null, false, null, layerDependencies)

    override fun KoScope.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        allowFilesOutsideLayers: Boolean,
        rule: KoRule?,
        layerDependenciesFunc: LayerDependencies,
    ) {
        KoArchitectureScope(layerDependenciesFunc, this, allowFilesOutsideLayers).assert(additionalMessage, testName, rule)
    }

    override fun List<KoFileDeclaration>.assertArchitecture(layerDependenciesFunc: LayerDependencies.() -> Unit) =
        assertArchitecture(null, null, false, null, layerDependenciesFunc)

    override fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        allowFilesOutsideLayers: Boolean,
        rule: KoRule?,
        layerDependenciesFunc: LayerDependencies.() -> Unit,
    ) {
        val layerDependencies = instanceLayerDependencies(layerDependenciesFunc)
        KoArchitectureFiles(layerDependencies, this, allowFilesOutsideLayers).assert(additionalMessage, testName, rule)
    }

    override fun List<KoFileDeclaration>.assertArchitecture(layerDependenciesFunc: LayerDependencies) =
        assertArchitecture(null, null, false, null, layerDependenciesFunc)

    override fun List<KoFileDeclaration>.assertArchitecture(
        additionalMessage: String?,
        testName: String?,
        allowFilesOutsideLayers: Boolean,
        rule: KoRule?,
        layerDependenciesFunc: LayerDependencies,
    ) {
        KoArchitectureFiles(layerDependenciesFunc, this, allowFilesOutsideLayers).assert(additionalMessage, testName, rule)
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
