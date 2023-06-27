package com.lemonappdev.konsist.circulardependency

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CircularDependencyTest {
    @Test
    fun `simply circular dependency with two layers`() {
        // given
        val layer1 = Layer("layer1", "layer1")
        val layer2 = Layer("layer2", "layer2")

        // when
        val sut = {
            Konsist
                .architecture(layer1, layer2)
                .addDependencies {
                    layer1.dependsOn(layer2)
                    layer2.dependsOn(layer1)
                }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Illegal circular dependencies:
            Layer(name=layer2, isDefinedBy=layer2) -->
            Layer(name=layer1, isDefinedBy=layer1) -->
            Layer(name=layer2, isDefinedBy=layer2).
        """.trimIndent()
    }

    @Test
    fun `simply circular dependency with three layers`() {
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

    @Test
    fun `complex circular dependency 1`() {
        // given
        val layer1 = Layer("layer1", "layer1")
        val layer2 = Layer("layer2", "layer2")
        val layer3 = Layer("layer3", "layer3")
        val layer4 = Layer("layer4", "layer4")
        val layer5 = Layer("layer5", "layer5")

        // when
        val sut = {
            Konsist
                .architecture(layer1, layer2, layer3, layer4, layer5)
                .addDependencies {
                    layer1.dependsOn(layer2, layer3)
                    layer2.dependsOn(layer4, layer5)
                    layer3.dependsOn(layer5)
                    layer4.dependsOn(layer1)
                }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Illegal circular dependencies:
            Layer(name=layer4, isDefinedBy=layer4) -->
            Layer(name=layer1, isDefinedBy=layer1) -->
            Layer(name=layer2, isDefinedBy=layer2) -->
            Layer(name=layer4, isDefinedBy=layer4).
        """.trimIndent()
    }

    @Test
    fun `complex circular dependency 2`() {
        // given
        val layer1 = Layer("layer1", "layer1")
        val layer2 = Layer("layer2", "layer2")
        val layer3 = Layer("layer3", "layer3")
        val layer4 = Layer("layer4", "layer4")

        // when
        val sut = {
            Konsist
                .architecture(layer1, layer2, layer3, layer4)
                .addDependencies {
                    layer1.dependsOn(layer2)
                    layer2.dependsOn(layer3)
                    layer3.dependsOn(layer1, layer4)
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

    @Test
    fun `complex circular dependency 3`() {
        // given
        val layer1 = Layer("layer1", "layer1")
        val layer2 = Layer("layer2", "layer2")
        val layer3 = Layer("layer3", "layer3")
        val layer4 = Layer("layer4", "layer4")

        // when
        val sut = {
            Konsist
                .architecture(layer1, layer2, layer3, layer4)
                .addDependencies {
                    layer1.dependsOn(layer2, layer3)
                    layer2.dependsOn(layer4)
                    layer3.dependsOn(layer4)
                    layer4.dependsOn(layer1)
                }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Illegal circular dependencies:
            Layer(name=layer4, isDefinedBy=layer4) -->
            Layer(name=layer1, isDefinedBy=layer1) -->
            Layer(name=layer2, isDefinedBy=layer2) -->
            Layer(name=layer4, isDefinedBy=layer4).
        """.trimIndent()
    }
}
