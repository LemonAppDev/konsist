package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoObjectProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationCoreProviderUtil

internal interface KoObjectProviderCore: KoObjectProvider, KoDeclarationProviderCore {
    override fun objects(
        includeNested: Boolean,
    ): Sequence<KoObjectDeclaration> = KoDeclarationCoreProviderUtil.getKoDeclarations(declarations(), includeNested)

    override fun containsObject(
        name: String,
        includeNested: Boolean,
    ): Boolean = objects(includeNested).any { it.name == name }

    override fun numObjects(includeNested: Boolean): Int = objects(includeNested).toList().size
}
