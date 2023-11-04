package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoHasTestProvider

@Deprecated("Will be removed in v1.0.0", ReplaceWith("KoHasTestClassProviderCore"))
internal interface KoHasTestProviderCore : KoHasTestProvider, KoHasTestClassProviderCore {
    @Deprecated("Will be removed in v1.0.0", replaceWith = ReplaceWith("hasTestClass()"))
    override fun hasTest(
        testFileNameSuffix: String,
        moduleName: String?,
        sourceSetName: String?,
    ): Boolean = hasTestClass(testFileNameSuffix, moduleName, sourceSetName)
}
