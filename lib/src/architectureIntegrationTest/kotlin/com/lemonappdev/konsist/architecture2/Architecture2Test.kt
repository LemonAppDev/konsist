package com.lemonappdev.konsist.architecture2

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.ext.toOsSeparator
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.verify.assert
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class Architecture2Test {
    private val rootPath = PathProvider.getInstance().rootProjectPath

    @Test
    fun `passes when dependency is set that presentation layer is depend on domain layer`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture2.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture2.project.presentation..")
        val koArchitecture = Konsist
            .architecture(domain, presentation)
            .addDependencies {
                domain.notDependOnAnyLayer()
                presentation.dependsOn(domain)
            }
        val sut = Konsist.scopeFromDirectory(
            "lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture2/project".toOsSeparator(),
        )

        // then
        sut.assert(koArchitecture)
    }

    @Test
    fun `fails when dependency is set that domain layer is depend on presentation layer`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture2.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture2.project.presentation..")
        val koArchitecture = Konsist
            .architecture(domain, presentation)
            .addDependencies {
                presentation.notDependOnAnyLayer()
                domain.dependsOn(presentation)
            }
        val sut = Konsist.scopeFromDirectory(
            "lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture2/project".toOsSeparator(),
        )

        // when
        val func = {
            sut.assert(koArchitecture)
        }

        // then
        func shouldThrow KoCheckFailedException::class withMessage """
            Assert 'domain layer is depend on presentation layer fails' has failed. Invalid dependencies at (1):
            Layer: Presentation defined by: com.lemonappdev.konsist.architecture2.project.presentation.. . Invalid files:
            $rootPath/lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture2/project/presentation/sample/PresentationThirdClass.kt
        """.trimIndent().toOsSeparator()
    }
}
