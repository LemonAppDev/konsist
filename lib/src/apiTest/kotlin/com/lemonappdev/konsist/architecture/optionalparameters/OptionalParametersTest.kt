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
        Layer(
            "Domain",
            "com.lemonappdev.konsist.architecture.optionalparameters.project.domain.."
        )

    private val presentation =
        Layer(
            "Presentation",
            "com.lemonappdev.konsist.architecture.optionalparameters.project.presentation..",
        )

    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters/project",
        )

    @Test
    fun `error message when additionalMessage and testName parameters are not provided (scope)`() {
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
                "'error message when additionalMessage and testName parameters are not provided (scope)' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when additionalMessage is provided and testName is not provided (scope)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(additionalMessage = "SOME ADDITIONAL MESSAGE") {
                    presentation.dependsOnNothing()
                    domain.dependsOn(presentation)
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'error message when additionalMessage is provided and testName is not provided (scope)' test has failed. \n" +
                        "SOME ADDITIONAL MESSAGE\n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when testName is provided and additionalMessage is not provided (scope)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(testName = "SOME CUSTOM TEST NAME") {
                    presentation.dependsOnNothing()
                    domain.dependsOn(presentation)
                }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when testName and additionalMessage are provided (scope)`() {
        // when
        val result =
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
        result
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed. \n" +
                        "SOME ADDITIONAL MESSAGE\n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when additionalMessage and testName parameters are not provided (files)`() {
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
                "'error message when additionalMessage and testName parameters are not provided (files)' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when additionalMessage is provided and testName is not provided (files)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture(additionalMessage = "SOME ADDITIONAL MESSAGE") {
                        presentation.dependsOnNothing()
                        domain.dependsOn(presentation)
                    }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'error message when additionalMessage is provided and testName is not provided (files)' test has failed. \n" +
                        "SOME ADDITIONAL MESSAGE\n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when testName is provided and additionalMessage is not provided (files)`() {
        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture(testName = "SOME CUSTOM TEST NAME") {
                        presentation.dependsOnNothing()
                        domain.dependsOn(presentation)
                    }
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when testName and additionalMessage are provided (files)`() {
        // when
        val result =
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
        result
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed. \n" +
                        "SOME ADDITIONAL MESSAGE\n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when additionalMessage and testName parameters are not provided and architecture is passed as parameter (scope)`() {
        // given
        val layerDependencies = architecture {
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
                "'error message when additionalMessage and testName parameters are not provided and architecture is passed " +
                        "as parameter (scope)' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when additionalMessage is provided, testName is not provided and architecture is passed as parameter (scope)`() {
        // given
        val layerDependencies = architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(additionalMessage = "SOME ADDITIONAL MESSAGE", layerDependenciesFunc = layerDependencies)
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'error message when additionalMessage is provided, testName is not provided and architecture is passed as " +
                        "parameter (scope)' test has failed. \n" +
                        "SOME ADDITIONAL MESSAGE\n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when testName is provided, additionalMessage is not provided and architecture is passed as parameter (scope)`() {
        // given
        val layerDependencies = architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(testName = "SOME CUSTOM TEST NAME", layerDependenciesFunc = layerDependencies)
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when testName and additionalMessage are provided and architecture is passed as parameter (scope)`() {
        // given
        val layerDependencies = architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture(
                    additionalMessage = "SOME ADDITIONAL MESSAGE",
                    testName = "SOME CUSTOM TEST NAME",
                    layerDependenciesFunc = layerDependencies,
                )
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed. \n" +
                        "SOME ADDITIONAL MESSAGE\n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when additionalMessage and testName parameters are not provided and architecture is passed as parameter (files)`() {
        // given
        val layerDependencies = architecture {
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
                "'error message when additionalMessage and testName parameters are not provided and architecture is passed " +
                        "as parameter (files)' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when additionalMessage is provided, testName is not provided and architecture is passed as parameter (files)`() {
        // given
        val layerDependencies = architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture(additionalMessage = "SOME ADDITIONAL MESSAGE", layerDependenciesFunc = layerDependencies)
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'error message when additionalMessage is provided, testName is not provided and architecture is passed " +
                        "as parameter (files)' test has failed. \n" +
                        "SOME ADDITIONAL MESSAGE\n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/optionalparameters/" +
                        "project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when testName is provided, additionalMessage is not provided and architecture is passed as parameter (files)`() {
        // given
        val layerDependencies = architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture(testName = "SOME CUSTOM TEST NAME", layerDependenciesFunc = layerDependencies)
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed. \n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }

    @Test
    fun `error message when testName and additionalMessage are provided and architecture is passed as parameter (files)`() {
        // given
        val layerDependencies = architecture {
                presentation.dependsOnNothing()
                domain.dependsOn(presentation)
            }

        // when
        val result =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture(
                        additionalMessage = "SOME ADDITIONAL MESSAGE",
                        testName = "SOME CUSTOM TEST NAME",
                        layerDependenciesFunc = layerDependencies,
                    )
            }

        // then
        result
            .message
            .shouldBeEqualTo(
                "'SOME CUSTOM TEST NAME' test has failed. \n" +
                        "SOME ADDITIONAL MESSAGE\n" +
                        "Layer 'Domain' does not depends on 'Presentation' layer.\n" +
                        "'Presentation' layer should not depend on anything but has dependencies in files:\n" +
                        "└── file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                        "optionalparameters/project/presentation/sample/PresentationThirdClass.kt\n" +
                        "    └── import com.lemonappdev.konsist.architecture.optionalparameters.project.domain.DomainFirstClass"
            )
    }
}
