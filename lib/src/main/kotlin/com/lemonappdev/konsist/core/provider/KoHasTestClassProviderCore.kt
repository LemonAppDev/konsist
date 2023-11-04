package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.provider.KoHasTestClassProvider

internal interface KoHasTestClassProviderCore : KoHasTestClassProvider, KoNameProviderCore, KoBaseProviderCore {
    override fun hasTestClass(
        testFileNameSuffix: String,
        moduleName: String?,
        sourceSetName: String?,
    ): Boolean =
        Konsist
            .scopeFromTest(moduleName, sourceSetName)
            .classes()
            .any { it.name == name + testFileNameSuffix }
}
