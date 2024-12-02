package com.lemonappdev.konsist.architecture.assertarchitecture.architecture8

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.jupiter.api.Test

class Architecture8Test {
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

    // region passes when domain depends on presentation (strict = false)
    @Test
    fun `passes when domain depends on presentation and strict is false (lambda scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.dependsOn(presentation, strict = false)
            }
    }

    @Test
    fun `passes when domain depends on presentation and strict is false (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.dependsOn(presentation, strict = false)
            }
    }

    @Test
    fun `passes when domain depends on presentation and strict is false (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOn(presentation, strict = false)
            }

        // then
        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when domain depends on presentation and strict is false (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOn(presentation, strict = false)
            }

        // then
        scope
            .files
            .assertArchitecture(layerDependencies)
    }
    // endregion

    // region fails when domain depends on presentation (strict = true)
    @Test
    fun `passes when domain depends on presentation and strict is true (lambda scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.dependsOn(presentation, strict = true)
            }
    }

    @Test
    fun `passes when domain depends on presentation and strict is true (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.dependsOn(presentation, strict = true)
            }
    }

    @Test
    fun `passes when domain depends on presentation and strict is true (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOn(presentation, strict = true)
            }

        // then
        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when domain depends on presentation and strict is true (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOn(presentation, strict = true)
            }

        // then
        scope
            .files
            .assertArchitecture(layerDependencies)
    }
    // endregion
}
