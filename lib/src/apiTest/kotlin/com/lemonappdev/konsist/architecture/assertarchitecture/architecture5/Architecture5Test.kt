package com.lemonappdev.konsist.architecture.assertarchitecture.architecture5

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class Architecture5Test {
    private val layer = Layer("EmptyLayer", "com/lemonappdev/konsist/assertarchitecture/architecture5/project/emptylayer..")

    @Test
    fun `throws exception when layer contains no files`() {
        // given
        val scope =
            Konsist.scopeFromPackage("com.lemonappdev.konsist.architecture.assertarchitecture.architecture5.project")

        // when
        val func = {
            scope.assertArchitecture { layer.dependsOnNothing() }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Layer EmptyLayer doesn't contain any files."
    }

    @Test
    fun `throws exception when architecture contains no layers`() {
        // given
        val scope =
            Konsist.scopeFromPackage("com.lemonappdev.konsist.architecture.assertarchitecture.architecture5.project")

        // when
        val func = {
            scope.assertArchitecture { }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Architecture doesn't contain layers or dependencies."
    }

    @Test
    fun `throws exception when architecture contains no dependencies`() {
        // given
        val scope =
            Konsist.scopeFromPackage("com.lemonappdev.konsist.architecture.assertarchitecture.architecture5.project")

        // when
        val func = {
            scope.assertArchitecture { layer }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Architecture doesn't contain layers or dependencies."
    }
}
