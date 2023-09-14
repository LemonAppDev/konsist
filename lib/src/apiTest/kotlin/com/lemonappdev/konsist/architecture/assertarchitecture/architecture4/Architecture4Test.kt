package com.lemonappdev.konsist.architecture.assertarchitecture.architecture4

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

class Architecture4Test {
    private val domain =
        Layer("Domain", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture4.project.domain..")
    private val presentation =
        Layer(
            "Presentation",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture4.project.presentation..",
        )
    private val data =
        Layer("Data", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture4.project.data..")
    private val scope = Konsist.scopeFromDirectory(
        "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture4/project",
    )

    @Test
    fun `passes when dependency is set that presentation and data layers are depend on domain layer`() {
        // then
        scope
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }
    }

    @Test
    fun `passes when dependency is set correctly and architecture is passed as parameter`() {
        // given
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
        // then
        try {
            scope
                .assertArchitecture {
                    data.dependsOnNothing()
                    presentation.dependsOn(data)
                    domain.dependsOn(data)
                }
        } catch (e: KoCheckFailedException) {
            assertSoftly {
                e.message?.shouldContain("'fails when bad dependency is set' test has failed.\n")
                e.message?.shouldContain("Presentation depends on Data assertion failure:\n")
            }
        }
    }

    @Test
    fun `fails when bad dependency is set and architecture is passed as parameter`() {
        // given
        val architecture = architecture {
            data.dependsOnNothing()
            presentation.dependsOn(data)
            domain.dependsOn(data)
        }

        // then
        try {
            scope
                .assertArchitecture(architecture)
        } catch (e: KoCheckFailedException) {
            assertSoftly {
                e.message?.shouldContain("'fails when bad dependency is set and architecture is passed as parameter' test has failed.\n")
                e.message?.shouldContain("Presentation depends on Data assertion failure:\n")
            }
        }
    }
}
