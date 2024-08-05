package com.lemonappdev.konsist.architecture.assertarchitecture.architecture7

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator
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
        Layer("Adapter", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.adapter..")
    private val common =
        Layer("Common", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.common..")
    private val domain =
        Layer("Domain", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.domain..")
    private val port =
        Layer("Port", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture7.project.port..")

    @Test
    fun `passes when good dependency is set (scope)`() {
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
    fun `passes when good dependency is set (files)`() {
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
    fun `passes when good dependency is set and architecture is passed as parameter (scope)`() {
        // then
        val architecture = architecture {
            domain.dependsOn(common)
            adapter.dependsOn(common)
            port.dependsOn(domain, common)
            adapter.dependsOn(port)
            common.dependsOnNothing()
        }

        scope.assertArchitecture(architecture)
    }

    @Test
    fun `passes when good dependency is set and architecture is passed as parameter (files)`() {
        // then
        val architecture = architecture {
            domain.dependsOn(common)
            adapter.dependsOn(common)
            port.dependsOn(domain, common)
            adapter.dependsOn(port)
            common.dependsOnNothing()
        }

        scope
            .files
            .assertArchitecture(architecture)
    }

    @Test
    fun `passes when good dependency is set using doesNotDependsOn (scope)`() {
        // then
        scope
            .assertArchitecture { domain.doesNotDependOn(adapter) }
    }

    @Test
    fun `passes when good dependency is set using doesNotDependsOn (files)`() {
        // then
        scope
            .files
            .assertArchitecture { domain.doesNotDependOn(adapter) }
    }

    @Test
    fun `passes when good dependency is set using doesNotDependsOn and architecture is passed as parameter (scope)`() {
        // then
        val architecture = architecture { domain.doesNotDependOn(adapter) }

        scope.assertArchitecture(architecture)
    }

    @Test
    fun `passes when good dependency is set using doesNotDependsOn and architecture is passed as parameter (files)`() {
        // then
        val architecture = architecture { domain.doesNotDependOn(adapter) }

        scope
            .files
            .assertArchitecture(architecture)
    }

    @Test
    fun `fails when bad dependency using doesNotDependsOn is set (scope)`() {
        // given
        val sut = {
            scope.assertArchitecture { domain.doesNotDependOn(adapter, common) }
        }

        // then
        sut shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when bad dependency using doesNotDependsOn is set (files)`() {
        // given
        val sut = {
            scope
                .files
                .assertArchitecture { domain.doesNotDependOn(adapter, common) }
        }

        // then
        sut shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when bad dependency using doesNotDependsOn is set and architecture is passed as parameter (scope)`() {
        // given
        val architecture = architecture { domain.doesNotDependOn(adapter, common) }

        val sut = {
            scope.assertArchitecture(architecture)
        }

        // then
        sut shouldThrow KoAssertionFailedException::class
    }

    @Test
    fun `fails when bad dependency using doesNotDependsOn is set and architecture is passed as parameter (files)`() {
        // given
        val architecture = architecture { domain.doesNotDependOn(adapter, common) }

        val sut = {
            scope
                .files
                .assertArchitecture(architecture)
        }

        // then
        sut shouldThrow KoAssertionFailedException::class
    }
}
