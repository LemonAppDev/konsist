package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoObjectProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoObjectProviderCore : KoObjectProvider, KoDeclarationProviderCore, KoBaseProviderCore {
    override fun objects(
        includeNested: Boolean,
    ): List<KoObjectDeclaration> = KoDeclarationProviderCoreUtil.getKoDeclarations(declarations(), includeNested)

    override fun containsObject(
        name: String,
        includeNested: Boolean,
    ): Boolean = objects(includeNested).any { it.name == name }

    override fun numObjects(includeNested: Boolean): Int = objects(includeNested).size
}
