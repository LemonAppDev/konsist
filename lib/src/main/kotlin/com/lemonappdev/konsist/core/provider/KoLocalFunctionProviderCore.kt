package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalFunctionProvider

internal interface KoLocalFunctionProviderCore : KoLocalFunctionProvider, KoLocalDeclarationProviderCore,
    KoBaseProviderCore {
    override val localFunctions: List<KoFunctionDeclaration>
        get() = localDeclarations.filterIsInstance<KoFunctionDeclaration>()

    override val numLocalFunctions: Int
        get() = localFunctions.size

    override fun countLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Int =
        localFunctions.count { predicate(it) }

    override fun containsLocalFunction(predicate: (KoFunctionDeclaration) -> Boolean): Boolean =
        localFunctions.any { predicate(it) }
}
