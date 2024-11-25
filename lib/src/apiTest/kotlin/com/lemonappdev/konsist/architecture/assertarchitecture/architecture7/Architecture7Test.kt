package com.lemonappdev.konsist.architecture.assertarchitecture.architecture7

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class Architecture7Test {
    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture7/project",
        )

    private val adapter =
        Layer(
            "Adapter",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.adapter..",
        )

    private val common =
        Layer(
            "Common",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.common..",
        )

    private val domain =
        Layer(
            "Domain",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.domain..",
        )

    private val port =
        Layer(
            "Port",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.port..",
        )

    // region passes when
    @Test
    fun `passes when  (lambda scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.dependsOn(common)
                adapter.dependsOn(common)
                port.dependsOn(domain, common)
                adapter.dependsOn(port)
                common.dependsOnNothing()
            }
    }

    @Test
    fun `passes when  (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.dependsOn(common)
                adapter.dependsOn(common)
                port.dependsOn(domain, common)
                adapter.dependsOn(port)
                common.dependsOnNothing()
            }
    }

    @Test
    fun `passes when  (parameter scope)`() {
        // then
        val layerDependencies =
            architecture {
                domain.dependsOn(common)
                adapter.dependsOn(common)
                port.dependsOn(domain, common)
                adapter.dependsOn(port)
                common.dependsOnNothing()
            }

        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when  (parameter files)`() {
        // then
        val layerDependencies =
            architecture {
                domain.dependsOn(common)
                adapter.dependsOn(common)
                port.dependsOn(domain, common)
                adapter.dependsOn(port)
                common.dependsOnNothing()
            }

        scope
            .files
            .assertArchitecture(layerDependencies)
    }
    // endregion

    // region fails when bad dependency is set
    @Test
    fun `passes when  using doesNotDependsOn (lambda scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.doesNotDependOn(adapter, port)
            }
    }

    @Test
    fun `passes when  using doesNotDependsOn (lambda files)`() {
        // then
        scope
            .files
            .assertArchitecture {
                domain.doesNotDependOn(adapter, port)
            }
    }

    @Test
    fun `passes when  using doesNotDependsOn (parameter scope)`() {
        // then
        val layerDependencies =
            architecture {
                domain.doesNotDependOn(adapter, port)
            }

        scope.assertArchitecture(layerDependencies)
    }

    @Test
    fun `passes when  using doesNotDependsOn (parameter files)`() {
        // then
        val layerDependencies =
            architecture {
                domain.doesNotDependOn(adapter, port)
            }

        scope
            .files
            .assertArchitecture(layerDependencies)
    }
    // endregion

    // region fails when bad dependency is set
    @Test
    fun `fails when bad dependency using doesNotDependsOn is set (lambda scope)`() {
        // given
        val sut = {
            scope.assertArchitecture {
                domain.doesNotDependOn(adapter, common)
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
                    domain.doesNotDependOn(adapter, common)
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
                domain.doesNotDependOn(adapter, common)
            }

        val sut = {
            scope.assertArchitecture(layerDependencies)
        }

        // then
        sut shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when bad dependency using doesNotDependsOn is set (parameter files)`() {
        // given
        val layerDependencies =
            architecture {
                domain.doesNotDependOn(adapter, common)
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
