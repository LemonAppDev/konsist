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
                domain.dependOnNothing()
                presentation.dependOnNothing()
            }
        val scope = Konsist.scopeFromDirectory("lib/src/konsistArchitectureApiTest/kotlin/com/lemonappdev/konsist/architecture1/project")

        // then
        koArchitecture.assert(scope)
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
            Presentation.
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
        sut shouldThrow KoPreconditionFailedException::class withMessage "Layer Domain is not added to the architecture."
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
        sut shouldThrow KoPreconditionFailedException::class withMessage "Layer Domain cannot be dependent on itself."
    }

    @Test
    fun `throws an exception when layer is set as independent and then set as depend on other layer`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture1.project.presentation..")

        val sut = {
            Konsist
                .architecture(domain, presentation)
                .addDependencies {
                    domain.dependOnNothing()
                    domain.dependsOn(presentation)
                }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Layer Domain was previously set as depend on nothing, so it cannot be depend on Presentation layer.
        """.trimIndent()
    }

    @Test
    fun `throws an exception when layer is set as independent twice`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")

        val sut = {
            Konsist
                .architecture(domain)
                .addDependencies {
                    domain.dependOnNothing()
                    domain.dependOnNothing()
                }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage "Duplicated the dependency that Domain layer should be depend on nothing."
    }

    @Test
    fun `throws an exception when layer is set as dependent on other layer and then as independent`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture1.project.presentation..")

        val sut = {
            Konsist
                .architecture(domain, presentation)
                .addDependencies {
                    domain.dependsOn(presentation)
                    domain.dependOnNothing()
                }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Layer Domain had a dependency previously set with Presentation layer, so it cannot be depend on nothing.
        """.trimIndent()
    }

    @Test
    fun `throws an exception when layer is set as dependent on the same layer twice`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture1.project.presentation..")

        val sut = {
            Konsist
                .architecture(domain, presentation)
                .addDependencies {
                    domain.dependsOn(presentation)
                    domain.dependsOn(presentation)
                }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage "Duplicated the dependency between Domain and Presentation layers."
    }
}
