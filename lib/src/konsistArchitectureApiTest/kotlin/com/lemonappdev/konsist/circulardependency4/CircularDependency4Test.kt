package com.lemonappdev.konsist.circulardependency4

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitecture.architecture
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CircularDependency4Test {
    @Test
    fun `circular dependency 4`() {
        // given
        val layer1 = Layer("layer1", "layer1..")
        val layer2 = Layer("layer2", "layer2..")
        val layer3 = Layer("layer3", "layer3..")
        val layer4 = Layer("layer4", "layer4..")

        // when
        val sut = {
            architecture {
                layer1.dependsOn(layer2)
                layer2.dependsOn(layer3)
                layer3.dependsOn(layer1, layer4)
            }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Illegal circular dependencies:
            Layer layer3 -->
            Layer layer1 -->
            Layer layer2 -->
            Layer layer3.
        """.trimIndent()
    }
}
