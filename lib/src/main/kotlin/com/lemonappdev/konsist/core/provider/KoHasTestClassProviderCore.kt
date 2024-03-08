package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.provider.KoHasTestClassProvider

@Deprecated("Will be removed in v0.16.0", ReplaceWith("KoTestClassProviderCore"))
internal interface KoHasTestClassProviderCore : KoHasTestClassProvider, KoNameProviderCore, KoBaseProviderCore {
    @Deprecated("Will be removed in v0.16.0", ReplaceWith("hasTestClasses"))
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
