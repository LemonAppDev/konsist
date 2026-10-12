package com.lemonappdev.konsist.api

import com.lemonappdev.konsist.api.KoModifier.OVERRIDE
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withoutModifier
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
            .withoutModifier(PRIVATE, OVERRIDE)
            .assertTrue {
                it.hasKDoc && it.hasValidKDocParamTags() && it.hasValidKDocReturnTag()
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
    fun `every api class, interface and object has KDoc`() {
        apiPackageScope
            .classesAndInterfacesAndObjects()
            .withoutModifier(PRIVATE)
            .assertTrue { it.hasKDoc }
    }

    @Test
    fun `every api enum constant has KDoc`() {
        apiPackageScope
            .classes()
            .withoutModifier(PRIVATE)
            .flatMap { it.enumConstants }
            .assertTrue { it.hasKDoc }
    }
}
