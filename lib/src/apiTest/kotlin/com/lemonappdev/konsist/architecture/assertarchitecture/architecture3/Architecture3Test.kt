package com.lemonappdev.konsist.architecture.assertarchitecture.architecture3

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import com.lemonappdev.konsist.core.filesystem.PathProvider
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class Architecture3Test {
    private val rootPath = PathProvider.rootProjectPath

    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture3/project",
        )

    private val domain =
        Layer(
            "Domain",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture3.project.domain..",
        )

    private val presentation =
        Layer(
            "Presentation",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture3.project.presentation..",
        )

    // region passes when dependency is set that presentation and domain layers are dependent on each other
    @Test
    fun `passes when dependency is set that presentation and domain layers are dependent on each other (lambda scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.dependsOn(presentation)
                presentation.dependsOn(domain)
            }
    }

    @Test
    fun `passes when dependency is set that presentation and domain layers are dependent on each other (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.dependsOn(presentation)
                presentation.dependsOn(domain)
            }
    }

    @Test
    fun `passes when dependency is set that two layers are dependent on each other (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOn(presentation)
                presentation.dependsOn(domain)
            }

        // then
        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when dependency is set that two layers are dependent on each other (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.dependsOn(presentation)
                presentation.dependsOn(domain)
            }

        // then
        scope
            .files
            .assertArchitecture(layerDependencies)
    }

    // endregion

    // region fails when dependency is set that presentation layer not depends on domain
    @Test
    fun `fails when dependency is set that presentation layer not depends on domain (lambda scope)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture {
                    presentation.doesNotDependOn(domain)
                }
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that presentation layer not depends on domain (scope)' " +
                    "test has failed.\n" +
                    "Presentation does not depend on Domain assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture" +
                    "/architecture3/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.assertarchitecture.architecture3.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "assertarchitecture/architecture3/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Test
    fun `fails when dependency is set that presentation layer not depends on domain (lambda files)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture {
                        presentation.doesNotDependOn(domain)
                    }
            }

        // then
        sut
            .message
            .shouldBeEqualTo(
                "'fails when dependency is set that presentation layer not depends on domain (files)' " +
                    "test has failed.\n" +
                    "Presentation does not depend on Domain assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture" +
                    "/architecture3/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.assertarchitecture.architecture3.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "assertarchitecture/architecture3/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Suppress("detekt.MaxLineLength")
    @Test
    fun `fails when dependency is set that presentation layer not depends on domain (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.doesNotDependOn(domain)
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
                "'fails when dependency is set that presentation layer not depends on domain and architecture " +
                    "is passed as parameter (scope)' test has failed.\n" +
                    "Presentation does not depend on Domain assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture" +
                    "/architecture3/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.assertarchitecture.architecture3.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "assertarchitecture/architecture3/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    @Suppress("detekt.MaxLineLength")
    @Test
    fun `fails when dependency is set that presentation layer not depends on domain (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.doesNotDependOn(domain)
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
                "'fails when dependency is set that presentation layer not depends on domain and architecture " +
                    "is passed as parameter (files)' test has failed.\n" +
                    "Presentation does not depend on Domain assertion failure:\n" +
                    "A file $rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture" +
                    "/architecture3/project/presentation/sample/PresentationThirdClass.kt in a Presentation layer " +
                    "depends on Domain layer, imports:\n" +
                    "\tcom.lemonappdev.konsist.architecture.assertarchitecture.architecture3.project.domain." +
                    "DomainFirstClass ($rootPath/lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/" +
                    "assertarchitecture/architecture3/project/presentation/sample/PresentationThirdClass.kt:3:1)",
            )
    }

    // endregion
}
