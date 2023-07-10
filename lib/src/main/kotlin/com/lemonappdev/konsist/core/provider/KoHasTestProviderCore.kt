package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.provider.KoHasTestProvider

internal interface KoHasTestProviderCore : KoHasTestProvider, KoNameProviderCore {
    override fun hasTest(testFileNameSuffix: String, moduleName: String?, sourceSetName: String?): Boolean = Konsist
        .scopeFromTest(moduleName, sourceSetName)
        .classes()
        .any { it.name == name + testFileNameSuffix }
}
