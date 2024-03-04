package com.lemonappdev.konsist.core

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.returnTypes
import com.lemonappdev.konsist.api.ext.list.types
import com.lemonappdev.konsist.api.ext.list.withoutName
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

class ProviderKonsistTest {
    private val providerPackageScope =
        Konsist.scopeFromPackage("com.lemonappdev.konsist.core.provider..", sourceSetName = "main")

    @Test
    fun `every function has explicit return type declaration`() {
        providerPackageScope
            .functions()
            .withoutName("print")
            .assertTrue { it.hasReturnType() }
    }

    @Test
    fun `none function return type has the 'Impl' suffix`() {
        providerPackageScope
            .functions()
            .returnTypes
            .assertFalse { it.sourceType.endsWith("Impl") }
    }

    @Test
    fun `none property type has the 'Impl' suffix`() {
        providerPackageScope
            .properties()
            .types
            .assertFalse { it.sourceType.endsWith("Impl") }
    }

    @Test
    fun `includeNested parameter is always before includeLocal parameter`() {
        providerPackageScope
            .functions()
            .assertTrue {
                val includeNestedParameter =
                    it.parameters.indexOfFirst { parameter -> parameter.name == "includeNested" }
                val includeLocalParameter =
                    it.parameters.indexOfFirst { parameter -> parameter.name == "includeLocal" }

                includeNestedParameter <= includeLocalParameter || (includeNestedParameter != -1 && includeLocalParameter == -1)
            }
    }

    @Test
    fun `every core provider implements its api equivalent`() {
        providerPackageScope
            .interfaces()
            .withoutName("KoPackageDeclarationProviderCore", "KoDeclarationFullyQualifiedNameProviderCore")
            .assertTrue {
                val name = it.name.removeSuffix("Core")

                it.hasParentWithName(name, indirectParents = true)
            }
    }
}
