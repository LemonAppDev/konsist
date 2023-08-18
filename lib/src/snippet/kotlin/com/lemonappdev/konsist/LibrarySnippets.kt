package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.verify.assert

class LibrarySnippets {
    fun `every api declaration has KDoc`() {
        Konsist.scopeFromPackage("..api..")
            .declarationsOf<KoKDocProvider>(includeNested = true)
            .assert { it.hasKDoc }
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
