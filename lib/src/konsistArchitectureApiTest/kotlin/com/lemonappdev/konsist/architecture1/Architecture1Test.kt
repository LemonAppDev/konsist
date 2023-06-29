package com.lemonappdev.konsist.architecture1

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitecture.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitecture.assertArchitecture
import com.lemonappdev.konsist.core.architecture.Layer
import org.junit.jupiter.api.Test

class Architecture1Test {
    @Test
    fun `passes when dependency is set that layers are independent`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture1.project.presentation..")
        val scope = Konsist.scopeFromDirectory("lib/src/konsistArchitectureApiTest/kotlin/com/lemonappdev/konsist/architecture1/project")

        // then
        scope
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOnNothing()
            }
    }

    @Test
    fun `passes when dependency is set that layers are independent when architecture is passed as parameter`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture1.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture1.project.presentation..")
        val scope = Konsist.scopeFromDirectory("lib/src/konsistArchitectureApiTest/kotlin/com/lemonappdev/konsist/architecture1/project")

        val koArchitecture = architecture {
            domain.dependsOnNothing()
            presentation.dependsOnNothing()
        }

        // then
        scope.assertArchitecture(koArchitecture)
    }
}
