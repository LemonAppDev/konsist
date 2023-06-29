package com.lemonappdev.konsist.architecture4

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.core.architecture.LayerImpl
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class Architecture4Test {
    @Test
    fun `passes when good dependency is set`() {
        // given
        val presentation = LayerImpl("Presentation", "com.lemonappdev.konsist.architecture4.project.presentation..")
        val application = LayerImpl("Application", "com.lemonappdev.konsist.architecture4.project.application..")
        val domain = LayerImpl("Domain", "com.lemonappdev.konsist.architecture4.project.domain..")
        val infrastructure = LayerImpl("Infrastructure", "com.lemonappdev.konsist.architecture4.project.infrastructure..")
        val scope = Konsist.scopeFromDirectory("lib/src/konsistArchitectureApiTest/kotlin/com/lemonappdev/konsist/architecture4/project")

        // then
        scope
            .assertArchitecture {
                presentation.dependsOn(application)
                application.dependsOn(domain, infrastructure)
                domain.dependsOn(infrastructure)
                infrastructure.dependsOnNothing()
            }
    }

    @Test
    fun `passes when good dependency is set and architecture is passed as parameter`() {
        // given
        val presentation = LayerImpl("Presentation", "com.lemonappdev.konsist.architecture4.project.presentation..")
        val application = LayerImpl("Application", "com.lemonappdev.konsist.architecture4.project.application..")
        val domain = LayerImpl("Domain", "com.lemonappdev.konsist.architecture4.project.domain..")
        val infrastructure = LayerImpl("Infrastructure", "com.lemonappdev.konsist.architecture4.project.infrastructure..")
        val scope = Konsist.scopeFromDirectory("lib/src/konsistArchitectureApiTest/kotlin/com/lemonappdev/konsist/architecture4/project")

        val architecture = architecture {
            presentation.dependsOn(application)
            application.dependsOn(domain, infrastructure)
            domain.dependsOn(infrastructure)
            infrastructure.dependsOnNothing()
        }

        // then
        scope
            .assertArchitecture(architecture)
    }

    @Test
    fun `fails when bad dependency is set`() {
        // given
        val presentation = LayerImpl("Presentation", "com.lemonappdev.konsist.architecture4.project.presentation..")
        val application = LayerImpl("Application", "com.lemonappdev.konsist.architecture4.project.application..")
        val domain = LayerImpl("Domain", "com.lemonappdev.konsist.architecture4.project.domain..")
        val infrastructure = LayerImpl("Infrastructure", "com.lemonappdev.konsist.architecture4.project.infrastructure..")
        val scope = Konsist.scopeFromDirectory("lib/src/konsistArchitectureApiTest/kotlin/com/lemonappdev/konsist/architecture4/project")

        val sut = {
            scope
                .assertArchitecture {
                    presentation.dependsOn(application, infrastructure)
                    application.dependsOn(infrastructure)
                    domain.dependsOn(infrastructure)
                    infrastructure.dependsOnNothing()
                }
        }

        // then
        sut shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `fails when bad dependency is set and architecture is passed as parameter`() {
        // given
        val presentation = LayerImpl("Presentation", "com.lemonappdev.konsist.architecture4.project.presentation..")
        val application = LayerImpl("Application", "com.lemonappdev.konsist.architecture4.project.application..")
        val domain = LayerImpl("Domain", "com.lemonappdev.konsist.architecture4.project.domain..")
        val infrastructure = LayerImpl("Infrastructure", "com.lemonappdev.konsist.architecture4.project.infrastructure..")
        val scope = Konsist.scopeFromDirectory("lib/src/konsistArchitectureApiTest/kotlin/com/lemonappdev/konsist/architecture4/project")

        val architecture = architecture {
            presentation.dependsOn(application, infrastructure)
            application.dependsOn(infrastructure)
            domain.dependsOn(infrastructure)
            infrastructure.dependsOnNothing()
        }

        val sut = {
            scope.assertArchitecture(architecture)
        }

        // then
        sut shouldThrow KoCheckFailedException::class
    }
}
