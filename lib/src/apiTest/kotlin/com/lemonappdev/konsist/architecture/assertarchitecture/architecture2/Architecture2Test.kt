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

    @Test
    fun `passes when dependency is set that presentation layer is depend on domain layer (scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
            }
    }

    @Test
    fun `passes when dependency is set that presentation layer is depend on domain layer (files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
            }
    }

    @Test
    fun `passes when dependency is set to presentation layer depends on domain layer and arch is passed as parameter (scope)`() {
        // given
        val architecture =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
            }

        // then
        scope.assertArchitecture(architecture)
    }

    @Test
    fun `passes when dependency is set to presentation layer depends on domain layer and arch is passed as parameter (files)`() {
        // given
        val architecture =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
            }

        // then
        scope
            .files
            .assertArchitecture(architecture)
    }

    @Test
    fun `passes when dependency is set that domain layer not depends on presentation one (scope)`() {
        // then
        scope
            .assertArchitecture { domain.doesNotDependOn(presentation) }
    }

    @Test
    fun `passes when dependency is set that domain layer not depends on presentation one (files)`() {
        // then
        scope
            .files
            .assertArchitecture { domain.doesNotDependOn(presentation) }
    }

    @Test
    fun `passes when dependency is set that domain layer not depends on presentation one and arch is passed as parameter (scope)`() {
        // given
        val architecture = architecture { domain.doesNotDependOn(presentation) }

        // then
        scope.assertArchitecture(architecture)
    }

    @Test
    fun `passes when dependency is set that domain layer not depends on presentation one and arch is passed as parameter (files)`() {
        // given
        val architecture = architecture { domain.doesNotDependOn(presentation) }

        // then
        scope
            .files
            .assertArchitecture(architecture)
    }

    @Test
    fun `fails when dependency is set that domain layer is depend on presentation layer (scope)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    presentation.dependsOnNothing()
                    domain.dependsOn(presentation)
                }
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that domain layer is depend on presentation layer (scope)' " +
                        "test has failed.\n" +
                        "Presentation depends on nothing assertion failure:\n" +
                        "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture" +
                        "/architecture2/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                        "depends on Domain layer, imports:\n" +
                        "\tcom.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.domain." +
                        "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "assertarchitecture/architecture2/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `fails when dependency is set that domain layer is depend on presentation layer (files)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture {
                        presentation.dependsOnNothing()
                        domain.dependsOn(presentation)
                    }
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that domain layer is depend on presentation layer (files)' " +
                        "test has failed.\n" +
                        "Presentation depends on nothing assertion failure:\n" +
                        "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture" +
                        "/architecture2/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                        "depends on Domain layer, imports:\n" +
                        "\tcom.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.domain." +
                        "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "assertarchitecture/architecture2/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Suppress("detekt.MaxLineLength")
    @Test
    fun `fails when dependency is set that domain layer is depend on presentation layer and architecture is passed as parameter (scope)`() {
        // given
        val architecture =
            architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(architecture)
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that domain layer is depend on presentation layer and architecture " +
                        "is passed as parameter (scope)' test has failed.\n" +
                        "Presentation depends on nothing assertion failure:\n" +
                        "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture" +
                        "/architecture2/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                        "depends on Domain layer, imports:\n" +
                        "\tcom.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.domain." +
                        "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "assertarchitecture/architecture2/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Suppress("detekt.MaxLineLength")
    @Test
    fun `fails when dependency is set that domain layer is depend on presentation layer and architecture is passed as parameter (files)`() {
        // given
        val architecture =
            architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture(architecture)
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that domain layer is depend on presentation layer and architecture " +
                        "is passed as parameter (files)' test has failed.\n" +
                        "Presentation depends on nothing assertion failure:\n" +
                        "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture" +
                        "/architecture2/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                        "depends on Domain layer, imports:\n" +
                        "\tcom.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.domain." +
                        "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "assertarchitecture/architecture2/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `fails when dependency is set that presentation layer not depends on domain (scope)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    presentation.doesNotDependOn(domain)
                    domain.dependsOnNothing()
                }
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that presentation layer not depends on domain (scope)' " +
                        "test has failed.\n" +
                        "Presentation depends on nothing assertion failure:\n" +
                        "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture" +
                        "/architecture2/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                        "depends on Domain layer, imports:\n" +
                        "\tcom.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.domain." +
                        "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "assertarchitecture/architecture2/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `fails when dependency is set that presentation layer not depends on domain (files)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture {
                        presentation.doesNotDependOn(domain)
                        domain.dependsOnNothing()
                    }
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that presentation layer not depends on domain (files)' " +
                        "test has failed.\n" +
                        "Presentation depends on nothing assertion failure:\n" +
                        "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture" +
                        "/architecture2/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                        "depends on Domain layer, imports:\n" +
                        "\tcom.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.domain." +
                        "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "assertarchitecture/architecture2/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Suppress("detekt.MaxLineLength")
    @Test
    fun `fails when dependency is set that presentation layer not depends on domain and architecture is passed as parameter (scope)`() {
        // given
        val architecture =
            architecture {
                presentation.doesNotDependOn(domain)
                domain.dependsOnNothing()
            }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(architecture)
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that presentation layer not depends on domain and architecture " +
                        "is passed as parameter (scope)' test has failed.\n" +
                        "Presentation depends on nothing assertion failure:\n" +
                        "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture" +
                        "/architecture2/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                        "depends on Domain layer, imports:\n" +
                        "\tcom.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.domain." +
                        "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "assertarchitecture/architecture2/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Suppress("detekt.MaxLineLength")
    @Test
    fun `fails when dependency is set that presentation layer not depends on domain and architecture is passed as parameter (files)`() {
        // given
        val architecture =
            architecture {
                presentation.doesNotDependOn(domain)
                domain.dependsOnNothing()
            }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture(architecture)
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that presentation layer not depends on domain and architecture " +
                        "is passed as parameter (files)' test has failed.\n" +
                        "Presentation depends on nothing assertion failure:\n" +
                        "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture" +
                        "/architecture2/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                        "depends on Domain layer, imports:\n" +
                        "\tcom.lemonappdev.konsist.architecture.assertarchitecture.architecture2.project.domain." +
                        "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "assertarchitecture/architecture2/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }
}
