package com.lemonappdev.konsist.architecture.assertarchitecture.architecture8

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Architecture8Test {

    @DisplayName("ScopeFromDirectory(ies)")
    @Nested
    inner class FromDirectory {
        // project 1
        private val businessProject1 =
            Layer("Domain_1", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture8.project1.domain..")
        private val dtoProject1 =
            Layer("Presentation_1", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture8.project1.presentation..")

        // project 2
        private val domainProject2 =
            Layer("Domain_2", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture8.project2.domain..")
        private val presentationProject2 =
            Layer("Presentation_2", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture8.project2.presentation..")

        private val scope = Konsist.scopeFromDirectory(
            path = "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture8/project1",
            "lib/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture8/project2",
        )

        @Test
        fun `passes when dependency is set that layers are independent (scope)`() {
            // then
            scope
                .assertArchitecture {
                    businessProject1.dependsOnNothing()
                    dtoProject1.dependsOnNothing()

                    domainProject2.dependsOnNothing()
                    presentationProject2.dependsOnNothing()
                }
        }

        @Test
        fun `passes when dependency is set that layers are independent when architecture is passed as parameter (scope)`() {
            // given
            val koArchitecture = KoArchitectureCreator.architecture {
                businessProject1.dependsOnNothing()
                dtoProject1.dependsOnNothing()

                domainProject2.dependsOnNothing()
                presentationProject2.dependsOnNothing()
            }

            // then
            scope.assertArchitecture(koArchitecture)
        }
    }

    @DisplayName("ScopeFromExternalDirectory(ies)")
    @Nested
    inner class FromExternalDirectory {
        private val businessProject1 =
            Layer("Domain1", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture8.project1.domain..")
        private val dtoProject1 =
            Layer("Presentation1", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture8.project1.presentation..")

        private val domainProject2 =
            Layer("Domain2", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture8.project2.domain..")
        private val presentationProject2 =
            Layer("Presentation2", "com.lemonappdev.konsist.architecture.assertarchitecture.architecture8.project2.presentation..")

        private val path = System.getProperty("user.dir")

        private val scope = Konsist.scopeFromExternalDirectory(
            "$path/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture8/project1",
            "$path/src/apiTest/kotlin/com/lemonappdev/konsist/architecture/assertarchitecture/architecture8/project2",
        )

        @Test
        fun `passes when dependency is set that layers are independent (scope)`() {
            // then
            scope
                .assertArchitecture {
                    businessProject1.dependsOnNothing()
                    dtoProject1.dependsOnNothing()

                    domainProject2.dependsOnNothing()
                    presentationProject2.dependsOnNothing()
                }
        }

        @Test
        fun `passes when dependency is set that layers are independent when architecture is passed as parameter (scope)`() {
            // given
            val koArchitecture = KoArchitectureCreator.architecture {
                businessProject1.dependsOnNothing()
                dtoProject1.dependsOnNothing()

                domainProject2.dependsOnNothing()
                presentationProject2.dependsOnNothing()
            }

            // then
            scope.assertArchitecture(koArchitecture)
        }
    }
}
