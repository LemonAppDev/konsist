package com.lemonappdev.konsist.architecture.assertarchitecture.architecture1

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.jupiter.api.Test

class Architecture1Test {
    private val domain = Layer("Domain", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture1.project.domain..")
    private val presentation =
        Layer("Presentation", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture1.project.presentation..")
    private val scope =
        Konsist.scopeFromDirectory("lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture1/project")

    @Test
    fun `passes when dependency is set that layers are independent (scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOnNothing()
            }
    }

    @Test
    fun `passes when dependency is set that layers are independent (files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOnNothing()
            }
    }

    @Test
    fun `passes when dependency is set that layers are independent when architecture is passed as parameter (scope)`() {
        // given
        val koArchitecture =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOnNothing()
            }

        // then
        scope.assertArchitecture(koArchitecture)
    }

    @Test
    fun `passes when dependency is set that layers are independent when architecture is passed as parameter (files)`() {
        // given
        val koArchitecture =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOnNothing()
            }

        // then
        scope
            .files
            .assertArchitecture(koArchitecture)
    }

    @Test
    fun `passes when dependency is set that Domain not depends on Presentation (scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.doesNotDependOn(presentation)
                presentation.dependsOnNothing()
            }
    }

    @Test
    fun `passes when dependency is set that Domain not depends on Presentation (files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.doesNotDependOn(presentation)
                presentation.dependsOnNothing()
            }
    }

    @Test
    fun `passes when dependency is set that Domain not depends on Presentation when architecture is passed as parameter (scope)`() {
        // given
        val koArchitecture =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOnNothing()
            }

        // then
        scope.assertArchitecture(koArchitecture)
    }

    @Test
    fun `passes when dependency is set that Domain not depends on Presentation when architecture is passed as parameter (files)`() {
        // given
        val koArchitecture =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOnNothing()
            }

        // then
        scope
            .files
            .assertArchitecture(koArchitecture)
    }
}
