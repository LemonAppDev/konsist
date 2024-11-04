package com.lemonappdev.konsist.core.architecture.validator.rule

import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.architecture.LayerDependency
import com.lemonappdev.konsist.core.architecture.LayerDependencyType
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class DependentOnNothingThenDependOnLayerDependenciesRuleTest {
    private val sut = DependentOnNothingThenDependOnLayerDependenciesRule()

    @Test
    fun `should throw exception when single layer violates dependent on nothing rule`() {
        // given
        val layer: Layer = mockk()
        val dependentLayer: Layer = mockk()

        every { layer.name } returns "layer name"
        every { dependentLayer.name } returns "dependent layer name"

        val layerDependencies =
            setOf(
                LayerDependency(
                    layer1 = layer,
                    layer2 = null,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                ),
                LayerDependency(
                    layer1 = layer,
                    layer2 = dependentLayer,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
            )

        // when
        val func = {
            sut.validate(
                layerDependencies = layerDependencies,
            )
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Conflicting dependency configurations:\n" +
            "Layer layer name was previously set as depend on nothing, so it cannot depend on dependent layer name layer."
    }

    @Test
    fun `should throw exception when multiple layers violate dependent on nothing rule`() {
        // given
        val layer1: Layer = mockk()
        val layer2: Layer = mockk()
        val dependentLayer1: Layer = mockk()
        val dependentLayer2: Layer = mockk()

        every { layer1.name } returns "layer name 1"
        every { layer2.name } returns "layer name 2"
        every { dependentLayer1.name } returns "dependent layer name 1"
        every { dependentLayer2.name } returns "dependent layer name 2"

        val layerDependencies =
            setOf(
                LayerDependency(
                    layer1 = layer1,
                    layer2 = null,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                ),
                LayerDependency(
                    layer1 = layer2,
                    layer2 = null,
                    dependencyType = LayerDependencyType.DEPEND_ON_NOTHING,
                ),
                LayerDependency(
                    layer1 = layer1,
                    layer2 = dependentLayer1,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
                LayerDependency(
                    layer1 = layer2,
                    layer2 = dependentLayer2,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
            )

        // when
        val func = {
            sut.validate(
                layerDependencies = layerDependencies,
            )
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Conflicting dependency configurations:\n" +
            "Layer layer name 1 was previously set as depend on nothing, so it cannot depend on dependent layer name 1 layer.\n" +
            "Layer layer name 2 was previously set as depend on nothing, so it cannot depend on dependent layer name 2 layer."
    }

    @Test
    fun `should not throw exception when no layer violates dependent on nothing rule`() {
        // given
        val layer: Layer = mockk()
        val dependentLayer: Layer = mockk()

        val layerDependencies =
            setOf(
                LayerDependency(
                    layer1 = layer,
                    layer2 = dependentLayer,
                    dependencyType = LayerDependencyType.DEPENDS_ON_LAYER,
                ),
            )

        // when
        sut.validate(
            layerDependencies = layerDependencies,
        )

        // then
        // No exception thrown
    }
}
