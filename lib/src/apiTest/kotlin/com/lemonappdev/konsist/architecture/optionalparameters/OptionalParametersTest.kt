package com.lemonappdev.konsist.architecture.optionalparameters

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.filesystem.PathProvider
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class OptionalParametersTest {
    private val rootPath = PathProvider.rootProjectPath
    private val domain =
        Layer("Domain", "com.lemonappdev.konsist.architecture.optionalparameters.project.domain..")
    private val presentation =
        Layer(
            "Presentation",
            "com.lemonappdev.konsist.architecture.optionalparameters.project.presentation..",
        )
    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters/project",
        )

    // region error message when additionalMessage and testName parameters are not provided
    @Test
    fun `error message when additionalMessage and testName parameters are not provided (lambda scope)`() {
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
                "'error message when additionalMessage and testName parameters are not provided (scope)' " +
                    "test has failed.\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when additionalMessage and testName parameters are not provided (lambda files)`() {
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
                "'error message when additionalMessage and testName parameters are not provided (files)' " +
                    "test has failed.\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when additionalMessage and testName parameters are not provided (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(layerDependencies)
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'error message when additionalMessage and testName parameters are not provided and architecture" +
                    " is passed as parameter (scope)' test has failed.\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when additionalMessage and testName parameters are not provided (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture(layerDependencies)
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'error message when additionalMessage and testName parameters are not provided and architecture" +
                    " is passed as parameter (files)' test has failed.\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    // endregion

    // region error message when additionalMessage is provided and testName is not provided
    @Test
    fun `error message when additionalMessage is provided and testName is not provided (lambda scope)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(additionalMessage = "SOME ADDITIONAL MESSAGE") {
                    presentation.dependsOnNothing()
                    domain.dependsOn(presentation)
                }
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'error message when additionalMessage is provided and testName is not provided (scope)' " +
                    "test has failed.\n" +
                    "SOME ADDITIONAL MESSAGE\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when additionalMessage is provided and testName is not provided (lambda files)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture(additionalMessage = "SOME ADDITIONAL MESSAGE") {
                        presentation.dependsOnNothing()
                        domain.dependsOn(presentation)
                    }
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'error message when additionalMessage is provided and testName is not provided (files)' " +
                    "test has failed.\n" +
                    "SOME ADDITIONAL MESSAGE\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when additionalMessage is provided and testName is not provided (parameter scope)`() {
        // given
        val architecture =
            architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(
                    dependencies = architecture,
                    additionalMessage = "SOME ADDITIONAL MESSAGE",
                )
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'error message when additionalMessage is provided and testName is not provided and architecture " +
                    "is passed as parameter (scope)' test has failed.\n" +
                    "SOME ADDITIONAL MESSAGE\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when additionalMessage is provided and testName is not provided (parameter files)`() {
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
                    .assertArchitecture(
                        dependencies = architecture,
                        additionalMessage = "SOME ADDITIONAL MESSAGE",
                    )
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'error message when additionalMessage is provided and testName is not provided and architecture " +
                    "is passed as parameter (files)' test has failed.\n" +
                    "SOME ADDITIONAL MESSAGE\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }
// endregion

    // region error message when testName is provided and additionalMessage is not provided
    @Test
    fun `error message when testName is provided and additionalMessage is not provided (lambda scope)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(testName = "SOME CUSTOM TEST NAME") {
                    presentation.dependsOnNothing()
                    domain.dependsOn(presentation)
                }
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed.\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when testName is provided and additionalMessage is not provided (lambda files)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture(testName = "SOME CUSTOM TEST NAME") {
                        presentation.dependsOnNothing()
                        domain.dependsOn(presentation)
                    }
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed.\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when testName is provided and additionalMessage is not provided (parameter scope)`() {
        // given
        val architecture =
            architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(
                    dependencies = architecture,
                    testName = "SOME CUSTOM TEST NAME",
                )
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed.\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when testName is provided and additionalMessage is not provided (parameter files)`() {
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
                    .assertArchitecture(
                        dependencies = architecture,
                        testName = "SOME CUSTOM TEST NAME",
                    )
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed.\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }
// endregion

    // region error message when testName and additionalMessage are provided
    @Test
    fun `error message when testName and additionalMessage are provided (lambda scope)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(
                    additionalMessage = "SOME ADDITIONAL MESSAGE",
                    testName = "SOME CUSTOM TEST NAME",
                ) {
                    presentation.dependsOnNothing()
                    domain.dependsOn(presentation)
                }
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed.\n" +
                    "SOME ADDITIONAL MESSAGE\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when testName and additionalMessage are provided (lambda files)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture(
                        additionalMessage = "SOME ADDITIONAL MESSAGE",
                        testName = "SOME CUSTOM TEST NAME",
                    ) {
                        presentation.dependsOnNothing()
                        domain.dependsOn(presentation)
                    }
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed.\n" +
                    "SOME ADDITIONAL MESSAGE\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when testName and additionalMessage are provided (parameter scope)`() {
        // given
        val architecture =
            architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(
                    additionalMessage = "SOME ADDITIONAL MESSAGE",
                    testName = "SOME CUSTOM TEST NAME",
                    dependencies = architecture,
                )
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed.\n" +
                    "SOME ADDITIONAL MESSAGE\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when testName and additionalMessage are provided (parameter files)`() {
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
                    .assertArchitecture(
                        additionalMessage = "SOME ADDITIONAL MESSAGE",
                        testName = "SOME CUSTOM TEST NAME",
                        dependencies = architecture,
                    )
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed.\n" +
                    "SOME ADDITIONAL MESSAGE\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    // endregion

    // region error message when additionalMessage is provided, testName is not provided
    @Test
    fun `error message when additionalMessage is provided, testName is not provided (parameter scope)`() {
        // given
        val architecture =
            architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(additionalMessage = "SOME ADDITIONAL MESSAGE", dependencies = architecture)
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'error message when additionalMessage is provided, testName is not provided and architecture " +
                    "is passed as parameter (scope)' test has failed.\n" +
                    "SOME ADDITIONAL MESSAGE\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when testName is provided, additionalMessage is not provided (parameter scope)`() {
        // given
        val architecture =
            architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(testName = "SOME CUSTOM TEST NAME", dependencies = architecture)
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed.\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when additionalMessage is provided, testName is not provided (parameter files)`() {
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
                    .assertArchitecture(additionalMessage = "SOME ADDITIONAL MESSAGE", dependencies = architecture)
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'error message when additionalMessage is provided, testName is not provided and architecture" +
                    " is passed as parameter (files)' test has failed.\n" +
                    "SOME ADDITIONAL MESSAGE\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `error message when testName is provided, additionalMessage is not provided (parameter files)`() {
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
                    .assertArchitecture(testName = "SOME CUSTOM TEST NAME", dependencies = architecture)
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed.\n" +
                    "Presentation depends on nothing assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters" +
                    "/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.optionalparameters.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "optionalparameters/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    // endregion
}
