package com.lemonappdev.konsist.architecture.assertarchitecture.architecture4

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.architecture
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoAssertionFailedException
import io.kotest.assertions.throwables.shouldThrow
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

class Architecture4Test {
    private val domain =
        Layer("Domain", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture4.project.domain..")
    private val presentation =
        Layer(
            "Presentation",
            "com.lemonappdev.konsist.architecture.assertarchitecture.architecture4.project.presentation..",
        )
    private val data =
        Layer("Data", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture4.project.data..")
    private val scope =
        Konsist.scopeFromDirectory(
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture4/project",
        )

    @Test
    fun `passes when dependency is set that presentation and data layers are depend on domain layer (scope)`() {
        // then
        scope
            .assertArchitecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }
    }

    @Test
    fun `passes when dependency is set that presentation and data layers are depend on domain layer (files)`() {
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
    fun `passes when dependency is set correctly and architecture is passed as parameter (scope)`() {
        // given
        val architecture =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }

        // then
        scope.assertArchitecture(architecture)
    }

    @Test
    fun `passes when dependency is set correctly and architecture is passed as parameter (files)`() {
        // given
        val architecture =
            architecture {
                domain.dependsOnNothing()
                presentation.dependsOn(domain)
                data.dependsOn(domain)
            }

        // then
        scope
            .files
            .assertArchitecture(architecture)
    }

    @Test
    fun `passes when dependency is set correctly using doesNotDependsOn (scope)`() {
        // then
        scope.assertArchitecture { domain.doesNotDependOn(data, presentation) }
    }

    @Test
    fun `passes when dependency is set correctly using doesNotDependsOn (files)`() {
        // then
        scope
            .files
            .assertArchitecture { domain.doesNotDependOn(data, presentation) }
    }

    @Test
    fun `passes when dependency is set correctly using doesNotDependsOn and architecture is passed as parameter (scope)`() {
        // given
        val architecture = architecture { domain.doesNotDependOn(data, presentation) }

        // then
        scope.assertArchitecture(architecture)
    }

    @Test
    fun `passes when dependency is set correctly using doesNotDependsOn and architecture is passed as parameter (files)`() {
        // given
        val architecture = architecture { domain.doesNotDependOn(data, presentation) }

        // then
        scope
            .files
            .assertArchitecture(architecture)
    }

    @Test
    fun `fails when bad dependency is set (scope)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .assertArchitecture {
                        data.dependsOnNothing()
                        presentation.dependsOn(data)
                        domain.dependsOn(data)
                    }
            }

        // then
        assertSoftly(sut) {
            message?.shouldContain("'fails when bad dependency is set (scope)' test has failed.\n")
            message?.shouldContain("Presentation depends on Data assertion failure:\n")
        }
    }

    @Test
    fun `fails when bad dependency is set (files)`() {
        // when
        val sut =
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
        assertSoftly(sut) {
            message?.shouldContain("'fails when bad dependency is set (files)' test has failed.\n")
            message?.shouldContain("Presentation depends on Data assertion failure:\n")
        }
    }

    @Test
    fun `fails when bad dependency is set and architecture is passed as parameter (scope)`() {
        // given
        val architecture =
            architecture {
                data.dependsOnNothing()
                presentation.dependsOn(data)
                domain.dependsOn(data)
            }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .assertArchitecture(architecture)
            }

        // then
        assertSoftly(sut) {
            message?.shouldContain(
                "'fails when bad dependency is set and architecture is passed as parameter (scope)' test has failed.\n",
            )
            message?.shouldContain("Presentation depends on Data assertion failure:\n")
        }
    }

    @Test
    fun `fails when bad dependency is set and architecture is passed as parameter (files)`() {
        // given
        val architecture =
            architecture {
                data.dependsOnNothing()
                presentation.dependsOn(data)
                domain.dependsOn(data)
            }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture(architecture)
            }

        // then
        assertSoftly(sut) {
            message?.shouldContain(
                "'fails when bad dependency is set and architecture is passed as parameter (files)' test has failed.\n",
            )
            message?.shouldContain("Presentation depends on Data assertion failure:\n")
        }
    }

    @Test
    fun `fails when bad dependency is set using doesNotDependsOn (scope)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope.assertArchitecture { presentation.doesNotDependOn(data, domain) }
            }

        // then
        assertSoftly(sut) {
            message?.shouldContain("'fails when bad dependency is set using doesNotDependsOn (scope)' test has failed.\n")
            message?.shouldContain("Presentation does not depend on Data, Domain assertion failure:\n")
        }
    }

    @Test
    fun `fails when bad dependency is set using doesNotDependsOn (files)`() {
        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture { presentation.doesNotDependOn(data, domain) }
            }

        // then
        assertSoftly(sut) {
            message?.shouldContain("'fails when bad dependency is set using doesNotDependsOn (files)' test has failed.\n")
            message?.shouldContain("Presentation does not depend on Data, Domain assertion failure:\n")
        }
    }

    @Test
    fun `fails when bad dependency is set using doesNotDependsOn and architecture is passed as parameter (scope)`() {
        // given
        val architecture = architecture { presentation.doesNotDependOn(data, domain) }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .assertArchitecture(architecture)
            }

        // then
        assertSoftly(sut) {
            message?.shouldContain(
                "'fails when bad dependency is set using doesNotDependsOn and architecture is passed as parameter (scope)' test has failed.\n",
            )
            message?.shouldContain("Presentation does not depend on Data, Domain assertion failure:\n")
        }
    }

    @Test
    fun `fails when bad dependency is set using doesNotDependsOn and architecture is passed as parameter (files)`() {
        // given
        val architecture = architecture { presentation.doesNotDependOn(data, domain) }

        // when
        val sut =
            shouldThrow<KoAssertionFailedException> {
                scope
                    .files
                    .assertArchitecture(architecture)
            }

        // then
        assertSoftly(sut) {
            message?.shouldContain(
                "'fails when bad dependency is set using doesNotDependsOn and architecture is passed as parameter (files)' test has failed.\n",
            )
            message?.shouldContain("Presentation does not depend on Data, Domain assertion failure:\n")
        }
    }
}
