package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.core.verify.assert
import org.junit.jupiter.api.Test

class ApiKonsistTest {
    @Test
    fun `every api declaration has kdoc`() {
        apiPackageScope
            .namedDeclarations(includeNested = true)
            .assert { it.hasKDoc() }
    }

    @Test
    fun `every api declaration has explicit return type`() {
        apiPackageScope
            .functions(includeNested = true)
            .assert { it.hasReturnType() }
    }

    companion object {
        val apiPackageScope = Konsist.scopeFromPackage("com.lemonappdev.konsist.api..", sourceSetName = "main")
    }
}
