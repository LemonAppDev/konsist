package com.lemonappdev.konsist.architecture.assertarchitecture.architecture9

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.jupiter.api.Test

class Architecture9Test {
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

    // region passes when presentation depends on domain (strict = false)
    @Test
    fun `passes when presentation depends on domain and strict is false (lambda scope)`() {
        // then
        scope
            .assertArchitecture {
                presentation.dependsOn(domain, strict = false)
            }
    }

    @Test
    fun `passes when presentation depends on domain and strict is false (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                presentation.dependsOn(domain, strict = false)
            }
    }

    @Test
    fun `passes when presentation depends on domain and strict is false (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.dependsOn(domain, strict = false)
            }

        // then
        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when presentation depends on domain and strict is false (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.dependsOn(domain, strict = false)
            }

        // then
        scope
            .files
            .assertArchitecture(layerDependencies)
    }

    // endregion

    // region passes when presentation depends on domain (strict = true)
    @Test
    fun `passes when presentation depends on domain and strict is true (lambda scope)`() {
        // then
        scope
            .assertArchitecture {
                presentation.dependsOn(domain, strict = true)
            }
    }

    @Test
    fun `passes when presentation depends on domain and strict is true (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                presentation.dependsOn(domain, strict = true)
            }
    }

    @Test
    fun `passes when presentation depends on domain and strict is true (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.dependsOn(domain, strict = true)
            }

        // then
        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when presentation depends on domain and strict is true (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.dependsOn(domain, strict = true)
            }

        // then
        scope
            .files
            .assertArchitecture(layerDependencies)
    }

    // endregion
}
