package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.api.ext.list.modifierprovider.withoutPrivateModifier
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocParamTags
import com.lemonappdev.konsist.api.ext.provider.hasValidKDocReturnTag
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

class KdocKonsistTest {
    private val apiPackageScope = Konsist.scopeFromPackage("com.lemonappdev.konsist.api..", sourceSetName = "main")

    @Test
    fun `every api function has valid KDoc`() {
        apiPackageScope
            .functions()
            .withoutPrivateModifier()
            .assertTrue { it.hasValidKDocParamTags() && it.hasValidKDocReturnTag() }
    }

    @Test
    fun `every api property has KDoc`() {
        apiPackageScope
            .properties()
            .assertTrue { it.hasKDoc }
    }

    @Test
    fun `every api declaration has KDoc`() {
        apiPackageScope
            .classesAndInterfacesAndObjects()
            .assertTrue { it.hasKDoc }
    }
}
