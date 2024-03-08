package com.lemonappdev.konsist.architecture.assertarchitecture.architecture3

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.jupiter.api.Test

class Architecture3Test {
    private val domain =
        Layer("Domain", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture3.project.domain..")
    private val presentation =
        Layer(
            "Presentation",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture3.project.presentation..",
        )
    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture3/project",
        )

    @Test
    fun `passes when dependency is set that presentation and domain layers are dependent on each other (scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.dependsOn(presentation)
                presentation.dependsOn(domain)
            }
    }

    @Test
    fun `passes when dependency is set that presentation and domain layers are dependent on each other (files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.dependsOn(presentation)
                presentation.dependsOn(domain)
            }
    }

    @Test
    fun `passes when dependency is set that two layers are dependent on each other and architecture is passed as parameter (scope)`() {
        // given
        val architecture =
            architecture {
                domain.dependsOn(presentation)
                presentation.dependsOn(domain)
            }

        // then
        scope.assertArchitecture(architecture)
    }

    @Test
    fun `passes when dependency is set that two layers are dependent on each other and architecture is passed as parameter (files)`() {
        // given
        val architecture =
            architecture {
                domain.dependsOn(presentation)
                presentation.dependsOn(domain)
            }

        // then
        scope
            .files
            .assertArchitecture(architecture)
    }
}
