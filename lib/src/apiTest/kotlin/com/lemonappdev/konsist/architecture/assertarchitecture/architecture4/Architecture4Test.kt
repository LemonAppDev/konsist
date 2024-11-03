package com.lemonappdev.konsist.architecture.assertarchitecture.architecture4

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.filesystem.PathProvider
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class Architecture4Test {
    private val rootPath = PathProvider.rootProjectPath

    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture4/project",
        )

    private val domain =
        Layer(
            "Domain",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture4.project.domain..",
        )

    private val presentation =
        Layer(
            "Presentation",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture4.project.presentation..",
        )

    private val data =
        Layer(
            "Data",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture4.project.data..",
        )

    // region passes when dependency is set that presentation and data layers are depend on domain layer
    @Test
    fun `passes when dependency is set that presentation and data layers are depend on domain layer (lambda scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }
    }

    @Test
    fun `passes when dependency is set that presentation and data layers are depend on domain layer (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }
    }

    @Test
    fun `passes when dependency is set that presentation and data layers are depend on domain layer (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }

        // then
        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when dependency is set that presentation and data layers are depend on domain layer (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }

        // then
        scope
            .files
            .assertArchitecture(layerDependencies)
    }

    // endregion

    // region fails when bad dependency is set
    @Test
    fun `passes when dependency is set correctly using doesNotDependsOn (lambda scope)`() {
        // then
        scope.assertArchitecture {
            domain.doesNotDependOn(data, presentation)
        }
    }

    @Test
    fun `passes when dependency is set correctly using doesNotDependsOn (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.doesNotDependOn(data, presentation)
            }
    }

    @Test
    fun `passes when dependency is set correctly using doesNotDependsOn (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                domain.doesNotDependOn(data, presentation)
            }
        // then
        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when dependency is set correctly using doesNotDependsOn (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.doesNotDependOn(data, presentation)
            }
        // then
        scope
            .files
            .assertArchitecture(layerDependencies)
    }
    // endregion

    // region fails when bad dependency is set
    @Test
    fun `fails when bad dependency is set (lambda scope)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
            scope
                .assertArchitecture {
                    data.dependsOnNothing()
                    presentation.dependsOn(data)
                    domain.dependsOn(data)
                }
        }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when bad dependency is set (lambda scope)' test has failed. \n" +
                        "Layer 'Presentation' does not depends on 'Data' layer.\n" +
                        "Layer 'Domain' does not depends on 'Data' layer."
            )
    }

    @Test
    fun `fails when bad dependency is set (lambda files)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
            scope
                .files
                .assertArchitecture {
                    data.dependsOnNothing()
                    presentation.dependsOn(data)
                    domain.dependsOn(data)
                }
        }

        // then
        result
            .message
            .shouldBeEqualTo("'fails when bad dependency is set (lambda files)' test has failed. \n" +
                    "Layer 'Presentation' does not depends on 'Data' layer.\n" +
                    "Layer 'Domain' does not depends on 'Data' layer."
            )
    }

    @Test
    fun `fails when bad dependency is set (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                data.dependsOnNothing()
                presentation.dependsOn(data)
                domain.dependsOn(data)
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
                "'fails when bad dependency is set (parameter scope)' test has failed. \n" +
                        "Layer 'Presentation' does not depends on 'Data' layer.\n" +
                        "Layer 'Domain' does not depends on 'Data' layer."
            )
    }

    @Test
    fun `fails when bad dependency is set (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                data.dependsOnNothing()
                presentation.dependsOn(data)
                domain.dependsOn(data)
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
                "'fails when bad dependency is set (parameter files)' test has failed. \n" +
                        "Layer 'Presentation' does not depends on 'Data' layer.\n" +
                        "Layer 'Domain' does not depends on 'Data' layer."
            )
    }

    // endregion

    // region fails when bad dependency is set using doesNotDependsOn
    @Test
    fun `fails when bad dependency is set using doesNotDependsOn (lambda scope)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    presentation.doesNotDependOn(data, domain)
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when bad dependency is set using doesNotDependsOn (lambda scope)' test has failed. \n" +
                        "'Presentation' layer does not depends on 'Domain' layer failed. Files that depend on 'Domain' layer:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "assertarchitecture/architecture4/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.assertarchitecture.architecture4.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `fails when bad dependency is set using doesNotDependsOn (lambda files)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture {
                        presentation.doesNotDependOn(data, domain)
                    }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when bad dependency is set using doesNotDependsOn (lambda files)' test has failed. \n" +
                        "'Presentation' layer does not depends on 'Domain' layer failed. Files that depend on 'Domain' layer:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "assertarchitecture/architecture4/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.assertarchitecture.architecture4.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `fails when bad dependency is set using doesNotDependsOn (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.doesNotDependOn(data, domain)
            }

        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .assertArchitecture(layerDependencies)
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'fails when bad dependency is set using doesNotDependsOn (parameter scope)' test has failed. \n" +
                        "'Presentation' layer does not depends on 'Domain' layer failed. Files that depend on 'Domain' layer:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "assertarchitecture/architecture4/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.assertarchitecture.architecture4.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `fails when bad dependency is set using doesNotDependsOn (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.doesNotDependOn(data, domain)
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
                "'fails when bad dependency is set using doesNotDependsOn (parameter files)' test has failed. \n" +
                        "'Presentation' layer does not depends on 'Domain' layer failed. Files that depend on 'Domain' layer:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "assertarchitecture/architecture4/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.assertarchitecture.architecture4.project.domain.DomainFirstClass"
            )
    }

    // endregion
}
