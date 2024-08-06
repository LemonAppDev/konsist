package com.lemonappdev.konsist.architecture.assertarchitecture.architecture5

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class Architecture5Test {
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
        Layer("Domain", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture5.project.domain..")
    private val infrastructure =
        Layer(
            "Infrastructure",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture5.project.infrastructure..",
        )
    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture5/project",
        )

    @Test
    fun `passes when good dependency is set (scope)`() {
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
    fun `passes when good dependency is set (files)`() {
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
    fun `passes when good dependency is set and architecture is passed as parameter (scope)`() {
        // given
        val architecture =
            architecture {
                presentation.dependsOn(application)
                application.dependsOn(domain, infrastructure)
                domain.dependsOn(infrastructure)
                infrastructure.dependsOnNothing()
            }

        // then
        scope
            .assertArchitecture(architecture)
    }

    @Test
    fun `passes when good dependency is set and architecture is passed as parameter (files)`() {
        // given
        val architecture =
            architecture {
                presentation.dependsOn(application)
                application.dependsOn(domain, infrastructure)
                domain.dependsOn(infrastructure)
                infrastructure.dependsOnNothing()
            }

        // then
        scope
            .files
            .assertArchitecture(architecture)
    }

    @Test
    fun `passes when good dependency is set using doesNotDependsOn (scope)`() {
        // then
        scope.assertArchitecture { presentation.doesNotDependOn(domain, infrastructure) }
    }

    @Test
    fun `passes when good dependency is set using doesNotDependsOn (files)`() {
        // then
        scope
            .files
            .assertArchitecture { presentation.doesNotDependOn(domain, infrastructure) }
    }

    @Test
    fun `passes when good dependency is set using doesNotDependsOn and architecture is passed as parameter (scope)`() {
        // given
        val architecture = architecture { presentation.doesNotDependOn(domain, infrastructure) }

        // then
        scope.assertArchitecture(architecture)
    }

    @Test
    fun `passes when good dependency is set using doesNotDependsOn and architecture is passed as parameter (files)`() {
        // given
        val architecture = architecture { presentation.doesNotDependOn(domain, infrastructure) }

        // then
        scope
            .files
            .assertArchitecture(architecture)
    }

    @Test
    fun `fails when bad dependency is set (scope)`() {
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
        sut shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when bad dependency is set (files)`() {
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
        sut shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when bad dependency is set and architecture is passed as parameter (scope)`() {
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
        sut shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when bad dependency is set and architecture is passed as parameter (files)`() {
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
        sut shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when bad dependency using doesNotDependsOn is set (scope)`() {
        // given
        val sut = { scope.assertArchitecture { application.doesNotDependOn(infrastructure) } }

        // then
        sut shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when bad dependency using doesNotDependsOn is set (files)`() {
        // given
        val sut = {
            scope
                .files
                .assertArchitecture { application.doesNotDependOn(infrastructure) }
        }

        // then
        sut shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when bad dependency using doesNotDependsOn is set and architecture is passed as parameter (scope)`() {
        // given
        val architecture = architecture { application.doesNotDependOn(infrastructure) }

        val sut = { scope.assertArchitecture(architecture) }

        // then
        sut shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when bad dependency using doesNotDependsOn is set and architecture is passed as parameter (files)`() {
        // given
        val architecture = architecture { application.doesNotDependOn(infrastructure) }

        val sut = {
            scope
                .files
                .assertArchitecture(architecture)
        }

        // then
        sut shouldThrow KoAssertionFailedException::class
    }
}
