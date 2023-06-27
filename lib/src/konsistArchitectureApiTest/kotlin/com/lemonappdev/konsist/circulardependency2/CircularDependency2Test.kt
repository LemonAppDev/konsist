package com.lemonappdev.konsist.circulardependency2

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CircularDependency2Test {
    @Test
    fun `circular dependency 2`() {
        // given
        val layer1 = Layer("layer1", "layer1")
        val layer2 = Layer("layer2", "layer2")
        val layer3 = Layer("layer3", "layer3")

        // when
        val sut = {
            Konsist
                .architecture(layer1, layer2, layer3)
                .addDependencies {
                    layer1.dependsOn(layer2)
                    layer2.dependsOn(layer3)
                    layer3.dependsOn(layer1)
                }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Illegal circular dependencies:
            Layer(name=layer3, isDefinedBy=layer3) -->
            Layer(name=layer1, isDefinedBy=layer1) -->
            Layer(name=layer2, isDefinedBy=layer2) -->
            Layer(name=layer3, isDefinedBy=layer3).
        """.trimIndent()
    }
}
