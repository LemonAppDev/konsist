package com.lemonappdev.konsist.architecture.dependencyrules.circulardependency2

import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CircularDependency2Test {
    @Test
    fun `circular dependency 2`() {
        // given
        val layer1 = Layer("layer1", "layer1..")
        val layer2 = Layer("layer2", "layer2..")
        val layer3 = Layer("layer3", "layer3..")
        val layer4 = Layer("layer4", "layer4..")
        val layer5 = Layer("layer5", "layer5..")

        // when
        val sut = {
            architecture {
                layer1.dependsOn(layer2, layer3)
                layer2.dependsOn(layer4, layer5)
                layer3.dependsOn(layer5)
                layer4.dependsOn(layer1)
            }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage
            """
            Illegal circular dependencies:
            Layer layer4 -->
            Layer layer1 -->
            Layer layer2 -->
            Layer layer4.
            """.trimIndent()
    }
}
