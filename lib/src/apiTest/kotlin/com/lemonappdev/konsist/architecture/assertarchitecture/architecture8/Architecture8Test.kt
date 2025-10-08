package com.lemonappdev.konsist.architecture.assertarchitecture.architecture8

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldBeEqualTo
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
    fun `fails when domain depends on presentation and strict is true (lambda scope)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .assertArchitecture {
                        domain.dependsOn(presentation, strict = true)
                    }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when domain depends on presentation and strict is true (lambda scope)' test has failed. \n" +
                    "Layer 'Domain' does not depends on 'Presentation' layer.",
            )
    }

    @Test
    fun `fails when domain depends on presentation and strict is true (lambda files)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture {
                        domain.dependsOn(presentation, strict = true)
                    }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when domain depends on presentation and strict is true (lambda files)' test has failed. \n" +
                    "Layer 'Domain' does not depends on 'Presentation' layer.",
            )
    }

    @Test
    fun `fails when domain depends on presentation and strict is true (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOn(presentation, strict = true)
            }

        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(layerDependencies)
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when domain depends on presentation and strict is true (parameter scope)' test has failed. \n" +
                    "Layer 'Domain' does not depends on 'Presentation' layer.",
            )
    }

    @Test
    fun `fails when domain depends on presentation and strict is true (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOn(presentation, strict = true)
            }

        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture(layerDependencies)
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when domain depends on presentation and strict is true (parameter files)' test has failed. \n" +
                    "Layer 'Domain' does not depends on 'Presentation' layer.",
            )
    }
    // endregion
}
