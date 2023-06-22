package com.lemonappdev.konsist.architecture1

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.verify.assert
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class Architecture1Test {
    @Test
    fun `layers are independent`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture1.project.presentation..")
        val koArchitecture = Konsist
            .architecture(domain, presentation)
            .addDependencies {
                domain.notDependOnAnyLayer()
                presentation.notDependOnAnyLayer()
            }
        val sut = Konsist.scopeFromDirectory("lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture1/project")

        // then
        sut.assert(koArchitecture)
    }

    @Test
    fun `throws exception when self dependency is set`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")

        // when
        val func = {
            Konsist
                .architecture(domain)
                .addDependencies { domain.dependsOn(domain) }
        }

        // then
        func shouldThrow IllegalArgumentException::class withMessage """
            Layer: Layer(name=Domain, isDefinedBy=com.lemonappdev.konsist.architecture1.project.domain..) cannot be dependent on itself.
        """.trimIndent()
    }

    @Test
    fun `throws an exception when layer that domain layer should depend on is not added to the architecture`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture1.project.presentation..")

        // when
        val func = {
            Konsist
                .architecture(domain)
                .addDependencies { domain.dependsOn(presentation) }
        }

        // then
        func shouldThrow IllegalArgumentException::class withMessage """
            Layers: [Layer(name=Presentation, isDefinedBy=com.lemonappdev.konsist.architecture1.project.presentation..)] is not add to the architecture.
        """.trimIndent()
    }

    @Test
    fun `throws an exception when layer on which the dependency is set is not added to architecture`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture1.project.presentation..")

        // when
        val func = {
            Konsist
                .architecture(presentation)
                .addDependencies { domain.dependsOn(presentation) }
        }

        // then
        func shouldThrow IllegalArgumentException::class withMessage """
            Layer: Layer(name=Domain, isDefinedBy=com.lemonappdev.konsist.architecture1.project.domain..) is not add to the architecture.
        """.trimIndent()
    }
}
