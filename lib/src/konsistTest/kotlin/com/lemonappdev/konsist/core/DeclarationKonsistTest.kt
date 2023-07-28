package com.lemonappdev.konsist.core

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.sequence.withExplicitReturnType
import com.lemonappdev.konsist.api.ext.sequence.withExplicitType
import com.lemonappdev.konsist.api.ext.sequence.withoutName
import com.lemonappdev.konsist.core.verify.assert
import com.lemonappdev.konsist.core.verify.assertNot
import org.junit.jupiter.api.Test

class DeclarationKonsistTest {
    @Test
    fun `every function has explicit return type declaration`() {
        declarationPackageScope
            .functions(includeNested = true)
            .withoutName("print")
            .assert { it.hasExplicitReturnType }
    }

    @Test
    fun `every property has explicit type declaration`() {
        declarationPackageScope
            .properties(includeNested = true)
            .assert { it.hasExplicitType() }
    }

    @Test
    fun `none function return type has the 'Impl' suffix`() {
        declarationPackageScope
            .functions(includeNested = true)
            .withExplicitReturnType()
            .mapNotNull { it.explicitReturnType }
            .assertNot { it.sourceType.endsWith("Impl") }
    }

    @Test
    fun `none property type has the 'Impl' suffix`() {
        declarationPackageScope
            .properties(includeNested = true)
            .withExplicitType()
            .mapNotNull { it.explicitType }
            .assertNot { it.sourceType.endsWith("Impl") }
    }

    companion object {
        val declarationPackageScope = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.declaration..", sourceSetName = "main")
    }
}
