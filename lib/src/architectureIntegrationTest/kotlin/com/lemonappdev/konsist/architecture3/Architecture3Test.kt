package com.lemonappdev.konsist.architecture3

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.ext.toOsSeparator
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.verify.assert
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class Architecture3Test {
    private val rootPath = PathProvider.getInstance().rootProjectPath

    @Test
    fun `throws an exception when circular dependency is set using dependsOnAllLayers() twice`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture3.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture3.project.presentation..")

        // when
        val func = {
            Konsist
                .architecture(domain, presentation)
                .addDependencies {
                    domain.dependsOnAllLayers()
                    presentation.dependsOnAllLayers()
                }
        }
        println("***************$rootPath")

        // then
        func shouldThrow IllegalArgumentException::class withMessage """
            Illegal circular dependencies (1):
            Layer(name=Presentation, isDefinedBy=com.lemonappdev.konsist.architecture3.project.presentation..) with Layer(name=Domain, isDefinedBy=com.lemonappdev.konsist.architecture3.project.domain..)
        """.trimIndent()
    }

    @Test
    fun `throws an exception when circular dependency is set using dependsOn() twice`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture3.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture3.project.presentation..")

        // when
        val func = {
            Konsist
                .architecture(domain, presentation)
                .addDependencies {
                    domain.dependsOn(presentation)
                    presentation.dependsOn(domain)
                }
        }

        // then
        func shouldThrow IllegalArgumentException::class withMessage """
            Illegal circular dependencies (1):
            Layer(name=Presentation, isDefinedBy=com.lemonappdev.konsist.architecture3.project.presentation..) with Layer(name=Domain, isDefinedBy=com.lemonappdev.konsist.architecture3.project.domain..)
        """.trimIndent()
    }

    @Test
    fun `fails when layers have circular dependency but in architecture is set that domain layer is depend on presentation layer`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture3.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture3.project.presentation..")
        val architecture = Konsist
            .architecture(domain, presentation)
            .addDependencies {
                domain.dependsOn(presentation)
            }
        val sut = Konsist.scopeFromDirectory(
            "lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture3/project".toOsSeparator(),
        )

        // when
        val func = {
            sut.assert(architecture)
        }

        // then
        func shouldThrow KoCheckFailedException::class withMessage """
            Assert 'fails when layers have circular dependency but in architecture is set that domain layer is depend on presentation layer' has failed. Invalid dependencies at (1):
            Layer: Presentation defined by: com.lemonappdev.konsist.architecture3.project.presentation.. . Invalid files:
            $rootPath/lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture3/project/presentation/PresentationFirstClass.kt
        """.trimIndent().toOsSeparator()
    }

    @Test
    fun `fails when layers have circular dependency but in architecture is set that they are independent`() {
        // given
        val domain = Layer("Domain", "com.lemonappdev.konsist.architecture3.project.domain..")
        val presentation = Layer("Presentation", "com.lemonappdev.konsist.architecture3.project.presentation..")
        val koArchitecture = Konsist
            .architecture(domain, presentation)
            .addDependencies {
                domain.notDependOnAnyLayer()
                presentation.notDependOnAnyLayer()
            }
        val sut = Konsist.scopeFromDirectory(
            "lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture3/project".toOsSeparator(),
        )

        // when
        val func = {
            sut.assert(koArchitecture)
        }

        // then
        func shouldThrow KoCheckFailedException::class withMessage """
            Assert 'fails when layers have circular dependency but in architecture is set that they are independent' has failed. Invalid dependencies at (2):
            Layer: Domain defined by: com.lemonappdev.konsist.architecture3.project.domain.. . Invalid files:
            $rootPath/lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture3/project/domain/DomainFirstClass.kt
            Layer: Presentation defined by: com.lemonappdev.konsist.architecture3.project.presentation.. . Invalid files:
            $rootPath/lib/src/architectureIntegrationTest/kotlin/com/lemonappdev/konsist/architecture3/project/presentation/PresentationFirstClass.kt
        """.trimIndent().toOsSeparator()
    }
}
