package com.lemonappdev.konsist.architecture.assertarchitecture.architecture2

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.filesystem.PathProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class Architecture2Test {
    private val rootPath = PathProvider.getInstance().rootProjectPath
    private val domain = Layer("Domain", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.domain..")
    private val presentation =
        Layer("Presentation", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.presentation..")
    private val scope = Konsist.scopeFromDirectory(
        "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture2/project",
    )

    @Test
    fun `passes when dependency is set that presentation layer is depend on domain layer`() {
        // then
        scope
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
            }
    }

    @Test
    fun `passes when dependency is set that presentation layer is depend on domain layer and architecture is passed as parameter`() {
        // given
        val architecture = architecture {
            domain.dependsOnNothing()
            presentation.dependsOn(domain)
        }

        // then
        scope.assertArchitecture(architecture)
    }

    @Test
    fun `fails when dependency is set that domain layer is depend on presentation layer`() {
        // then
        try {
            scope.assertArchitecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }
        } catch (e: KoCheckFailedException) {
            e.message?.shouldBeEqualTo(
                """
                'fails when dependency is set that domain layer is depend on presentation layer' test has failed. Invalid dependencies:
                Presentation depends on nothing assertion failure:
                $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture2/project/presentation/sample/PresentationThirdClass.kt
                """.trimIndent(),
            ) ?: throw e
        }
    }

    @Test
    fun `fails when dependency is set that domain layer is depend on presentation layer and architecture is passed as parameter`() {
        // given
        val architecture = architecture {
            presentation.dependsOnNothing()
            domain.dependsOn(presentation)
        }

        // then
        try {
            scope.assertArchitecture(architecture)
        } catch (e: KoCheckFailedException) {
            e.message?.shouldBeEqualTo(
                """
                'fails when dependency is set that domain layer is depend on presentation layer and architecture is passed as parameter' test has failed. Invalid dependencies:
                Presentation depends on nothing assertion failure:
                $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture2/project/presentation/sample/PresentationThirdClass.kt
                """.trimIndent(),
            ) ?: throw e
        }
    }
}
