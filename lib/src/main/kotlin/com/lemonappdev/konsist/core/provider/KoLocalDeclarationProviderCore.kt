package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider

internal interface KoLocalDeclarationProviderCore : KoLocalDeclarationProvider, KoBaseProviderCore {
    override val numLocalDeclarations: Int
        get() = localDeclarations.size

    override fun countLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): Int = localDeclarations.count { predicate(it) }

    override fun hasLocalDeclarations(): Boolean = localDeclarations.isNotEmpty()

    override fun hasLocalDeclaration(predicate: (KoBaseDeclaration) -> Boolean): Boolean = localDeclarations.any(predicate)

    override fun hasAllLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): Boolean = localDeclarations.all(predicate)
}
