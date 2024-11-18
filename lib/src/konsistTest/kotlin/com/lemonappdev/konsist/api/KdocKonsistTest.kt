package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.api.KoModifier.OVERRIDE
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withoutModifier
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withoutPrivateModifier
import com.lemonappdev.konsist.api.ext.list.withName
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
            .withName("isSortedBy")
            .assertTrue {
                it.hasValidKDocParamTags() && it.hasValidKDocReturnTag()
            }
    }

    @Test
    fun `every api property has KDoc`() {
        apiPackageScope
            .properties()
            .withoutModifier(PRIVATE, OVERRIDE)
            .assertTrue { it.hasKDoc }
    }

    @Test
    fun `every api declaration has KDoc`() {
        apiPackageScope
            .classesAndInterfacesAndObjects()
            .withoutPrivateModifier()
            .assertTrue { it.hasKDoc }
    }
}
