package com.lemonappdev.konsist.architecture3

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitecture.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitecture.assertArchitecture
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class Architecture3Test {
    @Test
    fun `passes when dependency is set that presentation and data layers are depend on domain layer`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture3.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture3.project.presentation..")
        val data = Layer("Data", "com.lemonappdev.konsist.architecture3.project.data..")
        val scope = Konsist.scopeFromDirectory("lib/src/konsistArchitectureApiTest/kotlin/com/lemonappdev/konsist/architecture3/project")

        // then
        scope
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }
    }

    @Test
    fun `passes when dependency is set that presentation and data layers are depend on domain layer and architecture is passed as parameter`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture3.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture3.project.presentation..")
        val data = Layer("Data", "com.lemonappdev.konsist.architecture3.project.data..")
        val scope = Konsist.scopeFromDirectory("lib/src/konsistArchitectureApiTest/kotlin/com/lemonappdev/konsist/architecture3/project")

        val architecture = architecture {
            domain.dependsOnNothing()
            presentation.dependsOn(domain)
            data.dependsOn(domain)
        }
        // then
        scope.assertArchitecture(architecture)
    }

    @Test
    fun `fails when bad dependency is set`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture3.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture3.project.presentation..")
        val data = Layer("Data", "com.lemonappdev.konsist.architecture3.project.data..")
        val scope = Konsist.scopeFromDirectory("lib/src/konsistArchitectureApiTest/kotlin/com/lemonappdev/konsist/architecture3/project")

        val sut = {
            scope
                .assertArchitecture {
                    data.dependsOnNothing()
                    presentation.dependsOn(data)
                    domain.dependsOn(data)
                }
        }

        // then
        sut shouldThrow KoCheckFailedException::class
    }

    @Test
    fun `fails when bad dependency is set and architecture is passed as parameter`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture3.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture3.project.presentation..")
        val data = Layer("Data", "com.lemonappdev.konsist.architecture3.project.data..")
        val scope = Konsist.scopeFromDirectory("lib/src/konsistArchitectureApiTest/kotlin/com/lemonappdev/konsist/architecture3/project")

        val architecture = architecture {
            data.dependsOnNothing()
            presentation.dependsOn(data)
            domain.dependsOn(data)
        }

        val sut = {
            scope.assertArchitecture(architecture)
        }

        // then
        sut shouldThrow KoCheckFailedException::class
    }
}
