package com.lemonappdev.konsist.core

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withReturnType
import com.lemonappdev.konsist.api.ext.list.withType
import com.lemonappdev.konsist.api.ext.list.withoutName
import com.lemonappdev.konsist.api.verify.assert
import com.lemonappdev.konsist.api.verify.assertNot
import org.junit.jupiter.api.Test

class DeclarationKonsistTest {
    @Test
    fun `every function has explicit return type declaration`() {
        declarationPackageScope
            .functions(includeNested = true)
            .withoutName("print")
            .assert { it.hasReturnType }
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

    @Test
    fun `none function2 return type has the 'Impl' suffix`() {
        declarationPackageScope
            .classes(includeNested = true)
            .first()
            .name
    }

    companion object {
        val declarationPackageScope = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.declaration..", sourceSetName = "main")
    }
}
