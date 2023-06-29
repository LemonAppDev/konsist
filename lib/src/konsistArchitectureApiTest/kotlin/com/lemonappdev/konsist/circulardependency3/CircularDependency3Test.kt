package com.lemonappdev.konsist.circulardependency3

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.Architecture.assertArchitecture
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CircularDependency3Test {
    @Test
    fun `circular dependency 3`() {
        // given
        val layer1 = Layer("layer1", "layer1..")
        val layer2 = Layer("layer2", "layer2..")
        val layer3 = Layer("layer3", "layer3..")
        val layer4 = Layer("layer4", "layer4..")
        val layer5 = Layer("layer5", "layer5..")
        val scope = Konsist.scopeFromProduction()

        // when
        val sut = {
            scope
                .assertArchitecture {
                    layer1.dependsOn(layer2, layer3)
                    layer2.dependsOn(layer4, layer5)
                    layer3.dependsOn(layer5)
                    layer4.dependsOn(layer1)
                }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Illegal circular dependencies:
            Layer layer4 -->
            Layer layer1 -->
            Layer layer2 -->
            Layer layer4.
        """.trimIndent()
    }
}
