package com.lemonappdev.konsist.architecture.assertarchitecture.architecture5

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.core.exception.KoPreconditionFailedException
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class Architecture5Test {
    @Test
    fun `throws exception when layer contain no files`() {
        // given
        val layer = Layer("EmptyLayer", "com/lemonappdev/konsist/assertarchitecture/architecture5/project/emptylayer..")
        val scope =
            Konsist.scopeFromPackage("com.lemonappdev.konsist.architecture.assertarchitecture.architecture5.project")

        // when
        val func = {
            scope.assertArchitecture { layer.dependsOnNothing() }
        }

        // then
        func shouldThrow KoPreconditionFailedException::class withMessage "Layer EmptyLayer doesn't contain any files."
    }
}
