package com.lemonappdev.konsist.architecture4

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.Architecture.assertArchitecture
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.verify.assert
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class Architecture4Test {
    @Test
    fun `passes when good dependency is set`() {
        // given
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture4.project.presentation..")
        val application = Layer("Application", "com.lemonappdev.konsist.architecture4.project.application..")
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture4.project.domain..")
        val infrastructure = Layer("Infrastructure", "com.lemonappdev.konsist.architecture4.project.infrastructure..")
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
    fun `fails when bad dependency is set`() {
        // given
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture4.project.presentation..")
        val application = Layer("Application", "com.lemonappdev.konsist.architecture4.project.application..")
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture4.project.domain..")
        val infrastructure = Layer("Infrastructure", "com.lemonappdev.konsist.architecture4.project.infrastructure..")
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
}
