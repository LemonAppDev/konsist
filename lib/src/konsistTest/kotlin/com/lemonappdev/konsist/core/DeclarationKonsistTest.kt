package com.lemonappdev.konsist.core

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.core.filesystem.PathProvider
import com.lemonappdev.konsist.core.util.KotlinFileParser
import com.lemonappdev.konsist.core.verify.assert
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class DeclarationKonsistTest {
    @Test
    fun `every function has explicit return type declaration`() {
        declarationPackageScope
            .functions(includeNested = true)
            .assert { it.hasReturnType() }
    }

    companion object {
        val declarationPackageScope = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.declaration..", sourceSetName = "main")
    }
}
