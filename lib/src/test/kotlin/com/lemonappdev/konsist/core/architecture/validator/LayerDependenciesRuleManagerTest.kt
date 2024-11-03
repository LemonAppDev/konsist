package com.lemonappdev.konsist.core.architecture.validator

import com.lemonappdev.konsist.core.architecture.LayerDependency
import com.lemonappdev.konsist.core.architecture.validator.rule.LayerDependenciesRule
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Test

class LayerDependenciesRuleManagerTest {
    private val layerDependenciesRules = listOf<LayerDependenciesRule>(mockk(), mockk())

    private val sut =
        LayerValidatorManager(
            layerDependenciesRules,
        )

    @Test
    fun `should validate layer dependencies`() {
        // given
        val dependencies = setOf(mockk<LayerDependency>())

        layerDependenciesRules.forEach {
            justRun { it.validate(dependencies) }
        }

        // when
        sut.validateLayerDependencies(dependencies)

        // then
        layerDependenciesRules.forEach {
            it.validate(dependencies)
        }
    }
}
