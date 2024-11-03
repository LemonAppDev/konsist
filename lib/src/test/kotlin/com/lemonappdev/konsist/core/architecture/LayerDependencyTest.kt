package com.lemonappdev.konsist.core.architecture

import com.lemonappdev.konsist.api.architecture.Layer
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class LayerDependencyTest {
    @Test
    fun `should create LayerDependency successfully when layer2 is provided with DEPEND_ON type`() {
        // Given
        val layer1 = Layer("layer1", "package1..")
        val layer2 = Layer("layer2", "package2..")

        // When / Then
        LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, layer2) // Should not throw
    }

    @Test
    fun `should create LayerDependency successfully with null layer2 when type is not DEPEND_ON`() {
        // Given
        val layer1 = Layer("layer1", "package1..")

        // When
        LayerDependency(layer1, LayerDependencyType.DEPEND_ON_NOTHING)

        // Then
        // Does not throw
    }

    @Test
    fun `should throw exception when creating LayerDependency with null layer2 and DEPEND_ON_LAYER type`() {
        // Given
        val layer1 = Layer("layer1", "package1..")

        // When
        val func = { LayerDependency(layer1, LayerDependencyType.DEPEND_ON_LAYER, null) }

        // Then
        func shouldThrow IllegalArgumentException::class withMessage "layer2 cannot be null when dependency type is DEPEND_ON_LAYER"
    }
}
