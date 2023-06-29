package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.architecture.KoArchitecture.architecture
import com.lemonappdev.konsist.core.architecture.LayerImpl
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class DependencyRulesTest {
    @Test
    fun `throws an exception when self dependency is set`() {
        // given
        val domain = LayerImpl("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")

        val sut = {
            architecture { domain.dependsOn(domain) }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage "Layer Domain cannot be dependent on itself."
    }

    @Test
    fun `throws an exception when layer is set as independent and then set as depend on other layer`() {
        // given
        val domain = LayerImpl("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")
        val presentation = LayerImpl("Presentation", "com.lemonappdev.konsist.architecture1.project.presentation..")

        val sut = {
            architecture {
                domain.dependsOnNothing()
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
        val domain = LayerImpl("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")

        val sut = {
            architecture {
                domain.dependsOnNothing()
                domain.dependsOnNothing()
            }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage """
            Duplicated the dependency that Domain layer should be depend on nothing.
        """.trimIndent()
    }

    @Test
    fun `throws an exception when layer is set as dependent on other layer and then as independent`() {
        // given
        val domain = LayerImpl("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")
        val presentation = LayerImpl("Presentation", "com.lemonappdev.konsist.architecture1.project.presentation..")

        val sut = {
            architecture {
                domain.dependsOn(presentation)
                domain.dependsOnNothing()
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
        val domain = LayerImpl("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")
        val presentation = LayerImpl("Presentation", "com.lemonappdev.konsist.architecture1.project.presentation..")

        val sut = {
            architecture {
                domain.dependsOn(presentation)
                domain.dependsOn(presentation)
            }
        }

        // then
        sut shouldThrow KoPreconditionFailedException::class withMessage "Duplicated the dependency between Domain and Presentation layers."
    }
}
