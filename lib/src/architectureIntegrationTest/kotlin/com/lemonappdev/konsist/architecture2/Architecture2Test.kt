package com.lemonappdev.konsist.architecture2

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.verify.assert
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class Architecture2Test {
    @Test
    fun `presentation layer is depend on domain layer`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture2.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture2.project.presentation..")
        val koArchitecture = Konsist
            .architecture(domain, presentation)
            .addDependencies {
                domain.notDependOnAnyLayer()
                presentation.dependsOn(domain)
            }
        val sut = Konsist.scopeFromDirectory("lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture2/project")

        // then
        sut.assert(koArchitecture)
    }

    @Test
    fun `domain layer is depend on presentation layer fails`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture2.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture2.project.presentation..")
        val koArchitecture = Konsist
            .architecture(domain, presentation)
            .addDependencies {
                presentation.notDependOnAnyLayer()
                domain.dependsOn(presentation)
            }
        val sut = Konsist.scopeFromDirectory("lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture2/project")

        // when
        val func = {
            sut.assert(koArchitecture)
        }

        // then
        func shouldThrow KoCheckFailedException::class withMessage """
            Assert 'invoke' has failed. Invalid dependencies at (1):
            Layer: Presentation defined by: com.lemonappdev.konsist.architecture2.project.presentation.. . Invalid files:
            /Users/natalia/IdeaProjects/konsist/lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture2/project/presentation/PresentationSecondClass.kt
        """.trimIndent()
    }
}
