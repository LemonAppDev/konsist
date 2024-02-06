package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider
import com.lemonappdev.konsist.api.provider.KoTestClassProvider
import com.lemonappdev.konsist.api.provider.KoValueProvider
import com.lemonappdev.konsist.core.ext.hasTacitType

internal interface KoTestClassProviderCore : KoTestClassProvider, KoNameProviderCore, KoBaseProviderCore {
    override fun testClasses(
        testPropertyName: String,
        moduleName: String?,
        sourceSetName: String?,
    ): List<KoClassDeclaration> = Konsist
        .scopeFromTest(moduleName, sourceSetName)
        .classes()
        .filter {
            it.hasProperty { property -> property.hasTestProperty(testPropertyName, name) } ||
                it.hasFunction { function ->
                    function.hasVariable { variable ->
                        variable.hasTestProperty(testPropertyName, name)
                    }
                }
        }

    override fun testClasses(
        moduleName: String?,
        sourceSetName: String?,
        predicate: (KoClassDeclaration) -> Boolean,
    ): List<KoClassDeclaration> = Konsist
        .scopeFromTest(moduleName, sourceSetName)
        .classes()
        .filter { predicate(it) }

    override fun numTestClasses(testPropertyName: String, moduleName: String?, sourceSetName: String?): Int =
        testClasses(testPropertyName, moduleName, sourceSetName).size

    override fun countTestClasses(
        moduleName: String?,
        sourceSetName: String?,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Int = testClasses(moduleName, sourceSetName, predicate).size

    override fun hasTestClasses(testPropertyName: String, moduleName: String?, sourceSetName: String?): Boolean =
        testClasses(testPropertyName, moduleName, sourceSetName).isNotEmpty()

    override fun hasTestClass(
        moduleName: String?,
        sourceSetName: String?,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean = testClasses(moduleName, sourceSetName, predicate).isNotEmpty()
}

private fun <T> T.hasTestProperty(name: String, type: String) where
      T : KoNameProvider,
      T : KoNullableTypeProvider,
      T: KoValueProvider = this.name == name && hasTacitType(type)
