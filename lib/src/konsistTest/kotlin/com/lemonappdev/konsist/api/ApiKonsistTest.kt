package com.lemonappdev.konsist.api

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
    fun `every api method has return tag in KDoc`() {
        testScope
            .functions(includeNested = true, includeLocal = true)
            .assert { it.hasValidKDoc(verifyParamTag = true, verifyReturnTag = true) }
    }

    companion object {
        val apiPackageScope = Konsist.scopeFromPackage("com.lemonappdev.konsist.api..", sourceSetName = "main")

        // change `testScope` to `apiPackageScope` in above tests and remove `testScope` property
        val testScope =
            Konsist.scopeFromProjectFile("lib/src/main/kotlin/com/lemonappdev/konsist/api/declaration/KoNamedDeclaration.kt") +
                Konsist.scopeFromPackage("lib/src/main/kotlin/com/lemonappdev/konsist/api/ext")
    }
}
