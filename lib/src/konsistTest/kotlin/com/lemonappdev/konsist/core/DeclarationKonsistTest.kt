package com.lemonappdev.konsist.core

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.declarations
import com.lemonappdev.konsist.api.ext.list.returnTypes
import com.lemonappdev.konsist.api.ext.list.types
import com.lemonappdev.konsist.api.ext.list.withoutName
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

class DeclarationKonsistTest {
    private val declarationPackageScope =
        Konsist.scopeFromPackage("com.lemonappdev.konsist.core.declaration..", sourceSetName = "main")

    @Test
    fun `every function has explicit return type declaration`() {
        declarationPackageScope
            .functions()
            .withoutName("print")
            .assertTrue { it.hasReturnType() }
    }

    @Test
    fun `every property has explicit type declaration`() {
        declarationPackageScope
            .properties()
            .assertTrue { it.hasType() }
    }

    @Test
    fun `none function return type has the 'Impl' suffix`() {
        declarationPackageScope
            .functions()
            .returnTypes
            .assertFalse { it.sourceType.endsWith("Impl") }
    }

    @Test
    fun `none property type has the 'Impl' suffix`() {
        declarationPackageScope
            .properties()
            .types
            .assertFalse { it.sourceType.endsWith("Impl") }
    }

    @Test
    fun `includeNested parameter is always before includeLocal parameter`() {
        declarationPackageScope
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
    fun `every declaration overrides toString()`() {
        declarationPackageScope
            .classes()
            .assertTrue {
                it.hasFunction { function -> function.hasOverrideModifier && function.name == "toString" }
            }
    }

    @Test
    fun `every core declaration implements KoBaseProviderCore and its api equivalent(`() {
        declarationPackageScope
            .classesAndInterfaces()
            .assertTrue {
                val name = it.name.removeSuffix("Core")

                it.hasParentsWithAllNames("KoBaseProviderCore", name, indirectParents = true)
            }
    }
}
