package com.lemonappdev.konsist.core.verify.koarchitectureassert

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.verify.assert
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class KoArchitectureAssertTest {
    @Test
    fun `assert-passes`() {
        // given
        val layer1 = Layer("Name1", "com.lemonappdev.konsist.core.verify.koarchitectureassert..")
        val layer2 = Layer("Name2", "com.lemonappdev.konsist..")
        val architecture = Konsist.architecture(layer1, layer2)
            .addDependencies { layer1.dependsOn(layer2) }
        val sut = Konsist.scopeFromProduction()

        // then
        sut.assert(architecture)
    }

    @Test
    fun `assert-fails`() {
        // given
        val layer1 = Layer("Name1", "com.lemonappdev.konsist.core..")
        val layer2 = Layer("Name2", "com.lemonappdev.konsist.api..")
        val layer3 = Layer("Name3", "com.lemonappdev.konsist.architecture..")
        val architecture = Konsist.architecture(layer1, layer2, layer3)
            .addDependencies { layer3.dependsOn(layer1) }
        val sut = Konsist.scopeFromProduction()

        // when
        val func = {
            sut.assert(architecture)
        }

        // then
        func shouldThrow KoCheckFailedException::class
    }
}
