package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.provider.KoHasTestClassProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoTacitTypeProvider

internal interface KoHasTestClassProviderCore : KoHasTestClassProvider, KoNameProviderCore, KoBaseProviderCore {
    override fun hasTestClass(testPropertyName: String, moduleName: String?, sourceSetName: String?): Boolean = Konsist
        .scopeFromTest(moduleName, sourceSetName)
        .classes()
        .any {
            it.hasProperty { property -> property.hasTestProperty(testPropertyName, name) } ||
                it.hasFunction { function ->
                    function.hasVariable { variable ->
                        variable.hasTestProperty(testPropertyName, name)
                    }
                }
        }
}

private fun <T> T.hasTestProperty(name: String, type: String) where
      T : KoNameProvider,
      T : KoTacitTypeProvider = this.name == name && hasTacitType(type)
