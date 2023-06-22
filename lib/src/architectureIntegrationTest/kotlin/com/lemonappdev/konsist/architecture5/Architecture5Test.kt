package com.lemonappdev.konsist.architecture5

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.verify.assert
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class Architecture5Test {
    @Test
    fun `passes when good dependency is set`() {
        // given
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture5.project.presentation..")
        val application = Layer("Application", "com.lemonappdev.konsist.architecture5.project.application..")
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture5.project.domain..")
        val infrastructure = Layer("Infrastructure", "com.lemonappdev.konsist.architecture5.project.infrastructure..")
        val koArchitecture = Konsist
            .architecture(presentation, application, domain, infrastructure)
            .addDependencies {
                presentation.dependsOn(application, infrastructure)
                application.dependsOn(domain, infrastructure)
                domain.dependsOn(infrastructure)
                infrastructure.notDependOnAnyLayer()
            }
        val sut = Konsist.scopeFromDirectory("lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture5/project")

        // then
        sut.assert(koArchitecture)
    }

    @Test
    fun `fails when bad dependency is set`() {
        // given
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture5.project.presentation..")
        val application = Layer("Application", "com.lemonappdev.konsist.architecture5.project.application..")
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture5.project.domain..")
        val infrastructure = Layer("Infrastructure", "com.lemonappdev.konsist.architecture5.project.infrastructure..")
        val koArchitecture = Konsist
            .architecture(presentation, application, domain, infrastructure)
            .addDependencies {
                presentation.dependsOn(application)
                application.dependsOn(infrastructure)
                domain.dependsOn(infrastructure)
                infrastructure.notDependOnAnyLayer()
            }
        val sut = Konsist.scopeFromDirectory("lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture5/project")

        // when
        val func = {
            sut.assert(koArchitecture)
        }
        // then
        func shouldThrow KoCheckFailedException::class
    }
}
