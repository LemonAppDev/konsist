package com.lemonappdev.konsist.core.verify.koarchitectureassert

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.core.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoCheckFailedException
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.verify.assert
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoArchitectureAssertTest {
    private val root = PathProvider.getInstance().rootProjectPath

    @Test
    fun `assert-passes`() {
        // given
        val layer1 = Layer("Presentation", "com.lemonappdev.konsist.core.verify.koarchitectureassert.project.presentation..")
        val layer2 = Layer("Business", "com.lemonappdev.konsist.core.verify.koarchitectureassert.project.business..")

        val architecture = Konsist.architecture(layer1, layer2)
            .addDependencies { layer1.dependsOn(layer2) }

        val scope =
            Konsist.scopeFromDirectory("lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/verify/koarchitectureassert/project")

        // then
        architecture.assert(scope)
    }

    @Test
    fun `assert-fails`() {
        // given
        val layer1 = Layer("Presentation", "com.lemonappdev.konsist.core.verify.koarchitectureassert.project.presentation..")
        val layer2 = Layer("Business", "com.lemonappdev.konsist.core.verify.koarchitectureassert.project.business..")
        val layer3 = Layer("Data", "com.lemonappdev.konsist.core.verify.koarchitectureassert.project.data..")

        val architecture = Konsist.architecture(layer1, layer2, layer3)
            .addDependencies { layer1.dependsOn(layer3) }

        val scope =
            Konsist.scopeFromDirectory("lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/verify/koarchitectureassert/project")

        // when
        val func = {
            architecture.assert(scope)
        }

        // then
        func shouldThrow KoCheckFailedException::class withMessage """
            Assert 'assert-fails' has failed. Invalid dependencies at (1):
            Layer: Presentation defined by: com.lemonappdev.konsist.core.verify.koarchitectureassert.project.presentation.. . Invalid files:
            $root/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/verify/koarchitectureassert/project/presentation/PresentationClass.kt
        """.trimIndent()
    }
}
