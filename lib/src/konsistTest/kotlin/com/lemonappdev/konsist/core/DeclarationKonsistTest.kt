package com.lemonappdev.konsist.core

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.sequence.withReturnType
import com.lemonappdev.konsist.api.ext.sequence.withType
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
            .assert { it.hasReturnType() }
    }

    @Test
    fun `every property has explicit type declaration`() {
        declarationPackageScope
            .properties(includeNested = true)
            .assert { it.hasType() }
    }

    @Test
    fun `none function return type has the 'Impl' suffix`() {
        declarationPackageScope
            .functions(includeNested = true)
            .withReturnType()
            .mapNotNull { it.returnType }
            .assertNot { it.sourceType.endsWith("Impl") }
    }

    @Test
    fun `none property type has the 'Impl' suffix`() {
        declarationPackageScope
            .properties(includeNested = true)
            .withType()
            .mapNotNull { it.type }
            .assertNot { it.sourceType.endsWith("Impl") }
    }

    companion object {
        val declarationPackageScope = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.declaration..", sourceSetName = "main")
    }
}
