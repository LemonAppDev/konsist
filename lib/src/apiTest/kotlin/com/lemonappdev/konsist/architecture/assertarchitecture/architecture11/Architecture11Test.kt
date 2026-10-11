package com.lemonappdev.konsist.architecture.assertarchitecture.architecture11

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class Architecture11Test {
    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture2/project",
        )

    private val domain =
        Layer(
            "Domain",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.domain..",
        )

    private val presentation =
        Layer(
            "Presentation",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.presentation..",
        )

    private val data =
        Layer(
            "Data",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.data..",
            optional = true,
        )

    // region passes when optional layer with no files has strict dependency
    @Test
    fun `passes when optional layer with no files has strict dependency (lambda scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain, strict = true)
                data.dependsOn(domain, strict = true)
            }
    }

    @Test
    fun `passes when optional layer with no files has strict dependency (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain, strict = true)
                data.dependsOn(domain, strict = true)
            }
    }

    @Test
    fun `passes when optional layer with no files has strict dependency (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain, strict = true)
                data.dependsOn(domain, strict = true)
            }

        // then
        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when optional layer with no files has strict dependency (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain, strict = true)
                data.dependsOn(domain, strict = true)
            }

        // then
        scope
            .files
            .assertArchitecture(layerDependencies)
    }
    // endregion

    // region fails when layer has strict dependency on optional layer with no files
    @Test
    fun `fails when layer has strict dependency on optional layer with no files (lambda scope)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .assertArchitecture {
                        domain.dependsOnNothing()
                        presentation.dependsOn(domain, data, strict = true)
                    }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when layer has strict dependency on optional layer with no files (lambda scope)' test has failed. \n" +
                    "'Presentation' layer is required to depend on 'Data' layer (strict), but has no dependency on it.",
            )
    }

    @Test
    fun `fails when layer has strict dependency on optional layer with no files (lambda files)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture {
                        domain.dependsOnNothing()
                        presentation.dependsOn(domain, data, strict = true)
                    }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when layer has strict dependency on optional layer with no files (lambda files)' test has failed. \n" +
                    "'Presentation' layer is required to depend on 'Data' layer (strict), but has no dependency on it.",
            )
    }

    @Test
    fun `fails when layer has strict dependency on optional layer with no files (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain, data, strict = true)
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
                "'fails when layer has strict dependency on optional layer with no files (parameter scope)' test has failed. \n" +
                    "'Presentation' layer is required to depend on 'Data' layer (strict), but has no dependency on it.",
            )
    }

    @Test
    fun `fails when layer has strict dependency on optional layer with no files (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain, data, strict = true)
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
                "'fails when layer has strict dependency on optional layer with no files (parameter files)' test has failed. \n" +
                    "'Presentation' layer is required to depend on 'Data' layer (strict), but has no dependency on it.",
            )
    }
    // endregion
}
