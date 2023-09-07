package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoObjectProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoObjectProviderCore : KoObjectProvider, KoDeclarationProviderCore, KoBaseProviderCore {
    override fun objects(
        includeNested: Boolean,
    ): List<KoObjectDeclaration> = KoDeclarationProviderCoreUtil.getKoDeclarations(
        declarations(includeNested = false, includeLocal = false),
        includeNested,
    )

    override fun containsObject(
        includeNested: Boolean,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Boolean = objects(includeNested).any { predicate(it) }

    override fun numObjects(includeNested: Boolean): Int = objects(includeNested).size

    override fun countObjects(
        includeNested: Boolean,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Int = objects(includeNested).count { predicate(it) }
}
