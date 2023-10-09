package com.lemonappdev.konsist.architecture.assertarchitecture.architecture5

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class Architecture5Test {
    private val presentation =
        Layer("Presentation", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture5.project.presentation..")
    private val application =
        Layer("Application", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture5.project.application..")
    private val domain = Layer("Domain", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture5.project.domain..")
    private val infrastructure =
        Layer("Infrastructure", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture5.project.infrastructure..")
    private val scope = Konsist.scopeFromDirectory(
        "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture5/project",
    )

    @Test
    fun `passes when good dependency is set`() {
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
