package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider

internal interface KoLocalClassProviderCore : KoLocalClassProvider, KoLocalDeclarationProviderCore, KoBaseProviderCore {
    override val localClasses: List<KoClassDeclaration>
        get() = localDeclarations.filterIsInstance<KoClassDeclaration>()

    override val numLocalClasses: Int
        get() = localClasses.size

    override fun countLocalClasses(predicate: (KoClassDeclaration) -> Boolean): Int =
        localClasses.count { predicate(it) }

    override fun containsLocalClass(predicate: (KoClassDeclaration) -> Boolean): Boolean =
        localClasses.any { predicate(it) }
}
