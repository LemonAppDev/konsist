package com.lemonappdev.konsist.circulardependency1

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitecture.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitecture.assertArchitecture
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CircularDependency1Test {
    @Test
    fun `circular dependency 1`() {
        // given
        val layer1 = Layer("layer1", "layer1..")
        val layer2 = Layer("layer2", "layer2..")

        // when
        val sut = {
            architecture {
                layer1.dependsOn(layer2)
                layer2.dependsOn(layer1)
            }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Illegal circular dependencies:
            Layer layer2 -->
            Layer layer1 -->
            Layer layer2.
        """.trimIndent()
    }
}
