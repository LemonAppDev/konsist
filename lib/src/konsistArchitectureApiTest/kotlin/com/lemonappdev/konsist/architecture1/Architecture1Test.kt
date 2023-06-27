package com.lemonappdev.konsist.architecture1

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import com.lemonappdev.konsist.core.verify.assert
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class Architecture1Test {
    @Test
    fun `passes when dependency is set that layers are independent`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture1.project.presentation..")

        val koArchitecture = Konsist
            .architecture(domain, presentation)
            .addDependencies {
                domain.notDependOnAnyLayer()
                presentation.notDependOnAnyLayer()
            }
        val scope = Konsist.scopeFromDirectory("lib/src/konsistArchitectureApiTest/kotlin/com/lemonappdev/konsist/architecture1/project")

        // then
        assert(koArchitecture, scope)
    }

    @Test
    fun `throws an exception when self dependency is set`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")

        val sut = {
            Konsist
                .architecture(domain)
                .addDependencies { domain.dependsOn(domain) }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Layer: Layer(name=Domain, isDefinedBy=com.lemonappdev.konsist.architecture1.project.domain..) cannot be dependent on itself.
        """.trimIndent()
    }

    @Test
    fun `throws an exception when the layer that the domain layer should depend on is not added to the architecture`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture1.project.presentation..")

        val sut = {
            Konsist
                .architecture(domain)
                .addDependencies { domain.dependsOn(presentation) }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Layers not added to the architecture:
            Layer(name=Presentation, isDefinedBy=com.lemonappdev.konsist.architecture1.project.presentation..).
        """.trimIndent()
    }

    @Test
    fun `throws an exception when the layer on which the dependency is set is not added to architecture`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture1.project.presentation..")

        val sut = {
            Konsist
                .architecture(presentation)
                .addDependencies { domain.dependsOn(presentation) }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Layer not added to the architecture:
            Layer(name=Domain, isDefinedBy=com.lemonappdev.konsist.architecture1.project.domain..).
        """.trimIndent()
    }
}
