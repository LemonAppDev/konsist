package com.lemonappdev.konsist.architecture.assertarchitecture.architecture9

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldBeEqualTo
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

    // region fails when presentation depends on domain (strict = true)
    @Test
    fun `fails when presentation depends on domain and strict is true (lambda scope)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .assertArchitecture {
                        presentation.dependsOn(domain, strict = true)
                    }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when presentation depends on domain and strict is true (lambda scope)' test has failed. \n" +
                    "Layer 'Presentation' does not depends on 'Domain' layer.",
            )
    }

    @Test
    fun `fails when presentation depends on domain and strict is true (lambda files)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture {
                        presentation.dependsOn(domain, strict = true)
                    }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when presentation depends on domain and strict is true (lambda files)' test has failed. \n" +
                    "Layer 'Presentation' does not depends on 'Domain' layer.",
            )
    }

    @Test
    fun `fails when presentation depends on domain and strict is true (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.dependsOn(domain, strict = true)
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
                "'fails when presentation depends on domain and strict is true (parameter scope)' test has failed. \n" +
                    "Layer 'Presentation' does not depends on 'Domain' layer.",
            )
    }

    @Test
    fun `fails when presentation depends on domain and strict is true (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.dependsOn(domain, strict = true)
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
                "'fails when presentation depends on domain and strict is true (parameter files)' test has failed. \n" +
                    "Layer 'Presentation' does not depends on 'Domain' layer.",
            )
    }

    // endregion
}
