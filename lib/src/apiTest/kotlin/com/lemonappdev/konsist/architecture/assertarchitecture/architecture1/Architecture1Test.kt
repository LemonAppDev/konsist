package com.lemonappdev.konsist.architecture.assertarchitecture.architecture1

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.core.architecture.LayerImpl
import org.junit.jupiter.api.Test

class Architecture1Test {
    @Test
    fun `passes when dependency is set that layers are independent`() {
        // given
        val domain = LayerImpl("Domain", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture1.project.domain..")
        val presentation =
            LayerImpl("Presentation", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture1.project.presentation..")
        val scope = Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture1/project",
        )

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
        val domain = LayerImpl("Domain", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture1.project.domain..")
        val presentation =
            LayerImpl("Presentation", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture1.project.presentation..")
        val scope = Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture1/project",
        )

        val koArchitecture = architecture {
            domain.dependsOnNothing()
            presentation.dependsOnNothing()
        }

        // then
        scope.assertArchitecture(koArchitecture)
    }
}
