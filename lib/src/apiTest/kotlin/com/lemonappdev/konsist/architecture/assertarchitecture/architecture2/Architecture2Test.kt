package com.lemonappdev.konsist.architecture.assertarchitecture.architecture2

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.filesystem.PathProvider
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class Architecture2Test {
    private val rootPath = PathProvider.rootProjectPath

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

    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture2/project",
        )

    // region passes when dependency is set that presentation layer is depend on domain layer
    @Test
    fun `passes when dependency is set that presentation layer is depend on domain layer (lambda scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
            }
    }

    @Test
    fun `passes when dependency is set that presentation layer is depend on domain layer (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
            }
    }

    @Test
    fun `passes when dependency is set to presentation layer depends on domain layer (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
            }

        // then
        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when dependency is set to presentation layer depends on domain layer (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
            }

        // then
        scope
            .files
            .assertArchitecture(layerDependencies)
    }

    // endregion

    // fails when dependency is set that domain layer depend on presentation domain layer
    @Test
    fun `fails when dependency is set that domain layer depend on presentation domain layer (lambda scope)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
            scope
                .assertArchitecture {
                    domain.dependsOn(presentation)
                }
        }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that domain layer depend on presentation domain layer (lambda scope)' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer."
            )
    }

    @Test
    fun `fails when dependency is set that domain layer depend on presentation domain layer (lambda files)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture {
                        domain.dependsOn(presentation)
                    }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that domain layer depend on presentation domain layer (lambda files)' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer."
            )
    }

    @Test
    fun `fails when dependency is set to domain layer depends on presentation layer (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOn(presentation)
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
                "'fails when dependency is set to domain layer depends on presentation layer (parameter scope)' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer."
            )
    }

    @Test
    fun `fails when dependency is set to domain layer depends on presentation layer (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOn(presentation)
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
                "'fails when dependency is set to domain layer depends on presentation layer (parameter files)' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer."
            )
    }

    // endregion

    // region passes when dependency is set that domain layer not depends on presentation layer
    @Test
    fun `passes when dependency is set that domain layer not depends on presentation layer (lambda scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.doesNotDependOn(presentation)
            }
    }

    @Test
    fun `passes when dependency is set that domain layer not depends on presentation layer (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.doesNotDependOn(presentation)
            }
    }

    @Test
    fun `passes when dependency is set that domain layer not depends on presentation layer (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                domain.doesNotDependOn(presentation)
            }

        // then
        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when dependency is set that domain layer not depends on presentation layer (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.doesNotDependOn(presentation)
            }

        // then
        scope
            .files
            .assertArchitecture(layerDependencies)
    }

    // endregion

    // region fails when dependency is set that domain layer is depend on presentation layer

    @Test
    fun `fails when dependency is set that domain layer is depend on presentation layer (parameter scope)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    presentation.dependsOnNothing()
                    domain.dependsOn(presentation)
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that domain layer is depend on presentation layer (parameter scope)' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.",
            )
    }

    @Test
    fun `fails when dependency is set that domain layer is depend on presentation layer (parameter files)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture {
                        presentation.dependsOnNothing()
                        domain.dependsOn(presentation)
                    }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that domain layer is depend on presentation layer (parameter files)' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.",
            )
    }

    @Suppress("detekt.MaxLineLength")
    @Test
    fun `fails when dependency is set that domain layer is depend on presentation layer (lambda scope)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
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
                "'fails when dependency is set that domain layer is depend on presentation layer (lambda scope)' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.",
            )
    }

    @Suppress("detekt.MaxLineLength")
    @Test
    fun `fails when dependency is set that domain layer is depend on presentation layer (lambda files)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
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
                "'fails when dependency is set that domain layer is depend on presentation layer (lambda files)' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer."
            )
    }

    //endregion

    // region fails when dependency is set that presentation layer not depends on domain
    @Test
    fun `fails when dependency is set that presentation layer not depends on domain (lambda scope)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    presentation.doesNotDependOn(domain)
                    domain.dependsOnNothing()
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that presentation layer not depends on domain (lambda scope)' test has failed. \n" +
                        "'Presentation' layer does not depends on 'Domain' layer failed. Files that depend on 'Domain' layer:\n" +
                        "\t└──file /Users/igorwojda/IdeaProjects/konsist/lib/src/apiTest/kotlin/com/lemonappdev/konsist/" +
                        "architecture/assertarchitecture/architecture2/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "\t\t└── import com.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `fails when dependency is set that presentation layer not depends on domain (lambda files)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture {
                        presentation.doesNotDependOn(domain)
                        domain.dependsOnNothing()
                    }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that presentation layer not depends on domain (lambda files)' test has failed. \n" +
                        "'Presentation' layer does not depends on 'Domain' layer failed. Files that depend on 'Domain' layer:\n" +
                        "\t└──file /Users/igorwojda/IdeaProjects/konsist/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "assertarchitecture/architecture2/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "\t\t└── import com.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.domain.DomainFirstClass"
            )
    }

    @Suppress("detekt.MaxLineLength")
    @Test
    fun `fails when dependency is set that presentation layer not depends on domain (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.doesNotDependOn(domain)
                domain.dependsOnNothing()
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
                "'fails when dependency is set that presentation layer not depends on domain (parameter scope)' test has failed. \n" +
                        "'Presentation' layer does not depends on 'Domain' layer failed. Files that depend on 'Domain' layer:\n" +
                        "\t└──file /Users/igorwojda/IdeaProjects/konsist/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture" +
                        "/assertarchitecture/architecture2/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "\t\t└── import com.lemonappdev.konsist.architecture.assertarchitecture." +
                        "architecture2.project.domain.DomainFirstClass"
            )
    }

    @Suppress("detekt.MaxLineLength")
    @Test
    fun `fails when dependency is set that presentation layer not depends on domain (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.doesNotDependOn(domain)
                domain.dependsOnNothing()
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
                "'fails when dependency is set that presentation layer not depends on domain (parameter files)' test has failed. \n" +
                        "'Presentation' layer does not depends on 'Domain' layer failed. Files that depend on 'Domain' layer:\n" +
                        "\t└──file /Users/igorwojda/IdeaProjects/konsist/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture2/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "\t\t└── import com.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.domain.DomainFirstClass",
            )
    }

    // endregion
}
