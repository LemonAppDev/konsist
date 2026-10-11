package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseTestClassProvider

internal interface KoBaseTestClassProviderCore :
    KoBaseTestClassProvider,
    KoBaseProviderCore {
    override fun testClasses(
        moduleName: String?,
        sourceSetName: String?,
        predicate: (KoClassDeclaration) -> Boolean,
    ): List<KoClassDeclaration> =
        Konsist
            .scopeFromTest(moduleName, sourceSetName)
            .classes()
            .filter { predicate(it) }

    override fun countTestClasses(
        moduleName: String?,
        sourceSetName: String?,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Int = testClasses(moduleName, sourceSetName, predicate).size

    override fun hasTestClass(
        moduleName: String?,
        sourceSetName: String?,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean = testClasses(moduleName, sourceSetName, predicate).isNotEmpty()
}
