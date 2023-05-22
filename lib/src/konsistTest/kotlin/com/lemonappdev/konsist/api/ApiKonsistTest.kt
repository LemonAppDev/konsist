package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.api.ext.sequence.withReturnType
import com.lemonappdev.konsist.core.verify.assert
import org.junit.jupiter.api.Test

class ApiKonsistTest {
    @Test
    fun `every api declaration has kdoc`() {
        apiPackageScope
            .declarations(includeNested = true)
            .assert { it.hasKDoc() }
    }

    @Test
    fun `every api declaration has explicit return type`() {
        apiPackageScope
            .functions(includeNested = true)
            .assert { it.hasReturnType() }
    }

    @Test
    fun `every api declaration has valid KDoc`() {
        apiPackageScope
            .functions(includeNested = true, includeLocal = true)
            .assert { it.hasValidKDoc(verifyParamTag = true, verifyReturnTag = true) }
    }

    companion object {
        val apiPackageScope = Konsist.scopeFromPackage("com.lemonappdev.konsist.api..", sourceSetName = "main")
    }
}
