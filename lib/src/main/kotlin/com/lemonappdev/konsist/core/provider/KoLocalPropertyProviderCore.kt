package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalPropertyProvider

internal interface KoLocalPropertyProviderCore :
    KoLocalPropertyProvider,
    KoLocalDeclarationProviderCore,
    KoBaseProviderCore {
    override val localProperties: List<KoPropertyDeclaration>
        get() = localDeclarations.filterIsInstance<KoPropertyDeclaration>()

    override val numLocalProperties: Int
        get() = localProperties.size

    override fun countLocalProperties(predicate: (KoPropertyDeclaration) -> Boolean): Int =
        localProperties.count { predicate(it) }

    override fun containsLocalProperty(predicate: (KoPropertyDeclaration) -> Boolean): Boolean =
        localProperties.any { predicate(it) }
}
