package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.api.KoKDocTag.PARAM
import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import com.lemonappdev.konsist.api.ext.koscope.declarationsOf
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.verify.assert
import org.junit.jupiter.api.Test

class ApiKonsistTest {
    @Test
    fun `every api declaration has kdoc`() {
        apiPackageScope
            .declarationsOf<KoKDocProvider>(includeNested = true)
            .assert { it.hasKDoc }
    }

    @Test
    fun `every api declaration has explicit return type`() {
        apiPackageScope
            .functions(includeNested = true)
            .assert { it.hasReturnType }
    }

    @Test
    fun `every api function has valid KDoc`() {
        apiPackageScope
            .functions(includeNested = true, includeLocal = true)
            .assert {
                if (it.parameters.isNotEmpty() && it.returnType?.name != "Unit") {
                    it.kDoc?.hasTags(PARAM, RETURN) == true && it.parameters.count() == it.kDoc?.paramTags?.count()
                } else if (it.parameters.isNotEmpty()) {
                    it.kDoc?.hasTags(PARAM) == true && it.parameters.count() == it.kDoc?.paramTags?.count()
                } else if (it.returnType?.name != "Unit") {
                    it.kDoc?.hasTags(RETURN)
                } else {
                    it.hasKDoc
                }
            }
    }

    @Test
    fun `includeNested parameter is always before includeLocal parameter`() {
        apiPackageScope
            .functions(includeNested = true, includeLocal = true)
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
