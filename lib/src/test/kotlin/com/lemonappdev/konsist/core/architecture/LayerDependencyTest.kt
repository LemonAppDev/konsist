package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.Layer
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class LayerDependencyTest {
    @Test
    fun `should create LayerDependency successfully when layer2 is provided with DEPEND_ON type`() {
        // given
        val layer1 = Layer("layer1", "package1..")
        val layer2 = Layer("layer2", "package2..")

        // when / Then
        LayerDependency(layer1, LayerDependencyType.DEPENDS_ON_LAYER, layer2) // Should not throw
    }

    @Test
    fun `should create LayerDependency successfully with null layer2 when type is not DEPEND_ON`() {
        // given
        val layer1 = Layer("layer1", "package1..")

        // when
        LayerDependency(layer1, LayerDependencyType.DEPEND_ON_NOTHING)

        // then
        // Does not throw
    }

    @Test
    fun `should throw exception when creating LayerDependency with null layer2 and DEPEND_ON_LAYER type`() {
        // given
        val layer1 = Layer("layer1", "package1..")

        // when
        val func = { LayerDependency(layer1, LayerDependencyType.DEPENDS_ON_LAYER, null) }

        // then
        func shouldThrow IllegalArgumentException::class withMessage "layer2 cannot be null when dependency type is DEPEND_ON_LAYER"
    }
}
