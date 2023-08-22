package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.ext.provider.hasValidParameterKDoc
import com.lemonappdev.konsist.api.ext.provider.hasValidReceiverTypeKDoc
import com.lemonappdev.konsist.api.ext.provider.hasValidReturnTypeKDoc
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.api.verify.assert

class LibrarySnippets {
    fun `every api declaration has KDoc`() {
        Konsist.scopeFromPackage("..api..")
            .declarationsOf<KoKDocProvider>(includeNested = true)
            .assert { it.hasKDoc }
    }

    fun `every function with parameters has a param tags`() {
        Konsist.scopeFromPackage("..api..")
            .functions(includeNested = true)
            .assert { it.hasValidParameterKDoc() }
    }

    fun `every function with return value has a return tag`() {
        Konsist.scopeFromPackage("..api..")
            .functions(includeNested = true)
            .assert { it.hasValidReturnTypeKDoc() }
    }

    fun `option 1 - every extension has a receiver tag`() {
        val scope1 = Konsist.scopeFromPackage("..api..")
            .functions(includeNested = true, includeLocal = true)

        val scope2 = Konsist.scopeFromPackage("..api..")
            .properties(includeNested = true, includeLocal = true)

        (scope1 + scope2)
            .assert { it.hasValidReceiverTypeKDoc() }
    }

    fun `option 2 - every extension has a receiver tag`() {
        Konsist.scopeFromPackage("..api..")
            .declarationsOf<KoReceiverTypeProvider>()
            .assert { it is KoKDocProvider && it.hasValidReceiverTypeKDoc() }
    }

    fun `every public function in api package must have explicit return type`() {
        Konsist.scopeFromPackage("..api..")
            .functions(includeNested = true)
            .assert { it.hasReturnType }
    }

    fun `every public property in api package must have specify type explicitly`() {
        Konsist.scopeFromPackage("..api..")
            .properties(includeNested = true)
            .assert { it.hasType() }
    }
}
