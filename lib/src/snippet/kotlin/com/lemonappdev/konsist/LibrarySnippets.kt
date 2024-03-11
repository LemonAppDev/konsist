package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocParamTags
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocReceiverTag
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocReturnTag
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.api.verify.assertTrue

class LibrarySnippets {
    fun `every api declaration has KDoc`() {
        Konsist
            .scopeFromPackage("..api..")
            .declarationsOf<KoKDocProvider>()
            .assertTrue { it.hasKDoc }
    }

    fun `every function with parameters has a param tags`() {
        Konsist.scopeFromPackage("..api..")
            .functions()
            .assertTrue { it.hasValidKDocParamTags() }
    }

    fun `every function with return value has a return tag`() {
        Konsist.scopeFromPackage("..api..")
            .functions()
            .assertTrue { it.hasValidKDocReturnTag() }
    }

    fun `every extension has a receiver tag`() {
        Konsist.scopeFromPackage("..api..")
            .declarationsOf<KoReceiverTypeProvider>()
            .assertTrue { it.hasValidKDocReceiverTag() }
    }

    fun `every public function in api package must have explicit return type`() {
        Konsist
            .scopeFromPackage("..api..")
            .functions()
            .assertTrue { it.hasReturnType() }
    }

    fun `every public property in api package must have specify type explicitly`() {
        Konsist
            .scopeFromPackage("..api..")
            .properties()
            .assertTrue { it.hasType() }
    }
}
