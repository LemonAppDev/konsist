package com.lemonappdev.konsist.architecture.assertarchitecture.architecture1

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.jupiter.api.Test

class Architecture1Test {
    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture1/project",
        )

    private val domain =
        Layer(
            "Domain",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture1.project.domain..",
        )

    private val presentation =
        Layer(
            "Presentation",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture1.project.presentation..",
        )

    // region passes when dependency is set that layers are independent
    @Test
    fun `passes when dependency is set that layers are independent (lambda scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOnNothing()
            }
    }

    @Test
    fun `passes when dependency is set that layers are independent (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOnNothing()
            }
    }

    @Test
    fun `passes when dependency is set that layers are independent (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOnNothing()
            }

        // then
        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when dependency is set that layers are independent (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOnNothing()
            }

        // then
        scope
            .files
            .assertArchitecture(layerDependencies)
    }

    // endregion

    //region passes when dependency is set that Domain not depends on Presentation

    @Test
    fun `passes when dependency is set that Domain not depends on Presentation (lambda scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.doesNotDependOn(presentation)
                presentation.dependsOnNothing()
            }
    }

    @Test
    fun `passes when dependency is set that Domain not depends on Presentation (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.doesNotDependOn(presentation)
                presentation.dependsOnNothing()
            }
    }

    @Test
    fun `passes when dependency is set that Domain not depends on Presentation (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOnNothing()
            }

        // then
        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when dependency is set that Domain not depends on Presentation (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOnNothing()
            }

        // then
        scope
            .files
            .assertArchitecture(layerDependencies)
    }

    // endregion
}
