package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocParamTags
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocReturnTag
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.verify.assert
import org.junit.jupiter.api.Test

class ApiKonsistTest {
    @Test
    fun `every api declaration has kdoc`() {
        apiPackageScope
            .declarationsOf<KoKDocProvider>()
            .assert { it.hasKDoc }
    }

    @Test
    fun `every api declaration has explicit return type`() {
        apiPackageScope
            .functions()
            .assert { it.hasReturnType() }
    }

    @Test
    fun `every api function has valid KDoc`() {
        apiPackageScope
            .functions()
            .assert { it.hasValidKDocParamTags() && it.hasValidKDocReturnTag() }
    }

    @Test
    fun `includeNested parameter is always before includeLocal parameter`() {
        apiPackageScope
            .functions()
            .assert {
                val includeNestedParameter =
                    it.parameters.indexOfFirst { parameter -> parameter.name == "includeNested" }
                val includeLocalParameter =
                    it.parameters.indexOfFirst { parameter -> parameter.name == "includeLocal" }

                includeNestedParameter <= includeLocalParameter || (includeNestedParameter != -1 && includeLocalParameter == -1)
            }
    }

    companion object {
        val apiPackageScope = Konsist.scopeFromPackage("com.lemonappdev.konsist.api..", sourceSetName = "main")
    }
}
