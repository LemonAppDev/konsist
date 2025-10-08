package com.lemonappdev.konsist.architecture.assertarchitecture.architecture5

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import org.amshove.kluent.shouldNotThrow
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class Architecture5Test {
    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture5/project",
        )

    private val presentation =
        Layer(
            "Presentation",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture5.project.presentation..",
        )

    private val application =
        Layer(
            "Application",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture5.project.application..",
        )

    private val domain =
        Layer(
            "Domain",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture5.project.domain..",
        )

    private val infrastructure =
        Layer(
            "Infrastructure",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture5.project.infrastructure..",
        )

    // region passes when
    @Test
    fun `passes when  (lambda scope)`() {
        // then
        scope
            .assertArchitecture {
                presentation.dependsOn(application)
                application.dependsOn(domain, infrastructure)
                domain.dependsOn(infrastructure)
                infrastructure.dependsOnNothing()
            }
    }

    @Test
    fun `passes when  (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                presentation.dependsOn(application)
                application.dependsOn(domain, infrastructure)
                domain.dependsOn(infrastructure)
                infrastructure.dependsOnNothing()
            }
    }

    @Test
    fun `passes when  (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.dependsOn(application)
                application.dependsOn(domain, infrastructure)
                domain.dependsOn(infrastructure)
                infrastructure.dependsOnNothing()
            }

        // then
        scope
            .assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when  (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.dependsOn(application)
                application.dependsOn(domain, infrastructure)
                domain.dependsOn(infrastructure)
                infrastructure.dependsOnNothing()
            }

        // then
        scope
            .files
            .assertArchitecture(layerDependencies)
    }

    // endregion

    // region passes when  using doesNotDependsOn
    @Test
    fun `passes when  using doesNotDependsOn (lambda scope)`() {
        // then
        scope.assertArchitecture {
            presentation.doesNotDependOn(domain, infrastructure)
        }
    }

    @Test
    fun `passes when  using doesNotDependsOn (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                presentation.doesNotDependOn(domain, infrastructure)
            }
    }

    @Test
    fun `passes when  using doesNotDependsOn (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.doesNotDependOn(domain, infrastructure)
            }

        // then
        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when  using doesNotDependsOn (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                presentation.doesNotDependOn(domain, infrastructure)
            }

        // then
        scope
            .files
            .assertArchitecture(layerDependencies)
    }

    // endregion

    // region fails when bad dependency is set
    @Test
    fun `fails when (lambda scope)`() {
        // given
        val sut = {
            scope
                .assertArchitecture {
                    presentation.dependsOn(application, infrastructure)
                    application.dependsOn(infrastructure)
                    domain.dependsOn(infrastructure)
                    infrastructure.dependsOnNothing()
                }
        }

        // then
        sut shouldNotThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when (lambda files)`() {
        // given
        val sut = {
            scope
                .files
                .assertArchitecture {
                    presentation.dependsOn(application, infrastructure)
                    application.dependsOn(infrastructure)
                    domain.dependsOn(infrastructure)
                    infrastructure.dependsOnNothing()
                }
        }

        // then
        sut shouldNotThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when (parameter scope)`() {
        // given
        val architecture =
            architecture {
                presentation.dependsOn(application, infrastructure)
                application.dependsOn(infrastructure)
                domain.dependsOn(infrastructure)
                infrastructure.dependsOnNothing()
            }

        val sut = {
            scope.assertArchitecture(architecture)
        }

        // then
        sut shouldNotThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when (parameter files)`() {
        // given
        val architecture =
            architecture {
                presentation.dependsOn(application, infrastructure)
                application.dependsOn(infrastructure)
                domain.dependsOn(infrastructure)
                infrastructure.dependsOnNothing()
            }

        val sut = {
            scope
                .files
                .assertArchitecture(architecture)
        }

        // then
        sut shouldNotThrow KoAssertionFailedException::class
    }

    // endregion

    // region fails when bad dependency using doesNotDependsOn is set
    @Test
    fun `fails when bad dependency using doesNotDependsOn is set (lambda scope)`() {
        // given
        val sut = {
            scope.assertArchitecture {
                application.doesNotDependOn(infrastructure)
            }
        }

        // then
        sut shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when bad dependency using doesNotDependsOn is set (lambda files)`() {
        // given
        val sut = {
            scope
                .files
                .assertArchitecture {
                    application.doesNotDependOn(infrastructure)
                }
        }

        // then
        sut shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when bad dependency using doesNotDependsOn is set (parameter scope)`() {
        // given
        val layerDependencies =
            architecture {
                application.doesNotDependOn(infrastructure)
            }

        val sut = { scope.assertArchitecture(layerDependencies) }

        // then
        sut shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when bad dependency using doesNotDependsOn is set (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                application.doesNotDependOn(infrastructure)
            }

        val sut = {
            scope
                .files
                .assertArchitecture(layerDependencies)
        }

        // then
        sut shouldThrow KoAssertionFailedException::class
    }

    // endregion
}
