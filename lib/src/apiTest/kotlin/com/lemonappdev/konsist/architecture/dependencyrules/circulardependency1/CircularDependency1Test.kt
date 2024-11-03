package com.lemonappdev.konsist.architecture.dependencyrules.circulardependency1

import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CircularDependency1Test {
    @Test
    fun `circular dependency 1`() {
        // given
        val layer1 = Layer("name1", "layer1..")
        val layer2 = Layer("name2", "layer2..")
        val layer3 = Layer("name3", "layer3..")

        // when
        val func = {
            architecture {
                layer1.dependsOn(layer2)
                layer2.dependsOn(layer3)
                layer3.dependsOn(layer1)
            }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage
            "Circular dependency detected: 'name1' -> 'name2' -> 'name3' -> 'name1'."
    }
}
