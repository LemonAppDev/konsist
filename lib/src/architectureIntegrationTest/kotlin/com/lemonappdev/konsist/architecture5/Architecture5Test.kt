package com.lemonappdev.konsist.architecture5

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.verify.assert
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class Architecture5Test {
    @Test
    fun `presentation and data layers are depend on domain layer`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture5.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture5.project.presentation..")
        val data = Layer("Data", "com.lemonappdev.konsist.architecture5.project.data..")
        val koArchitecture = Konsist
            .architecture(domain, presentation, data)
            .addDependencies {
                domain.notDependOnAnyLayer()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }
        val sut = Konsist.scopeFromDirectory("lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture5/project")

        // then
        sut.assert(koArchitecture)
    }

    @Test
    fun `fails when bad dependency is set`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture5.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture5.project.presentation..")
        val data = Layer("Data", "com.lemonappdev.konsist.architecture5.project.data..")
        val koArchitecture = Konsist
            .architecture(domain, presentation, data)
            .addDependencies {
                data.notDependOnAnyLayer()
                presentation.dependsOn(data)
                domain.dependsOn(data)
            }
        val sut = Konsist.scopeFromDirectory("lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture5/project")

        // when
        val func = {
            sut.assert(koArchitecture)
        }
        // then
        func shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `throws exception when circular dependency is set`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture5.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture5.project.presentation..")
        val data = Layer("Data", "com.lemonappdev.konsist.architecture5.project.data..")

        // when
        val func = {
            Konsist
                .architecture(domain, presentation, data)
                .addDependencies {
                    domain.dependsOn(presentation)
                    presentation.dependsOn(data)
                    data.dependsOn(domain)
                }
        }

        // then
        func shouldThrow IllegalArgumentException::class // circular dependency (set message)
    }
}
