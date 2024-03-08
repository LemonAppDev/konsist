package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.provider.KoHasTestProvider

@Deprecated("Will be removed in v0.16.0", ReplaceWith("KoTestClassProviderCore"))
internal interface KoHasTestProviderCore : KoHasTestProvider, KoHasTestClassProviderCore {
    @Deprecated("Will be removed in v0.16.0", replaceWith = ReplaceWith("hasTestClasses()"))
    override fun hasTest(
        testFileNameSuffix: String,
        moduleName: String?,
        sourceSetName: String?,
    ): Boolean = hasTestClass(testFileNameSuffix, moduleName, sourceSetName)
}
