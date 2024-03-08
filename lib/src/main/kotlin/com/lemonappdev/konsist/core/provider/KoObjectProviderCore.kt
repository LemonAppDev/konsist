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

    @Deprecated("Will be removed in v0.16.0", replaceWith = ReplaceWith("hasObject()"))
    override fun containsObject(
        includeNested: Boolean,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Boolean = objects(includeNested).any { predicate(it) }

    override fun numObjects(includeNested: Boolean): Int = objects(includeNested).size

    override fun countObjects(
        includeNested: Boolean,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Int = objects(includeNested).count { predicate(it) }

    override fun hasObjects(includeNested: Boolean): Boolean =
        objects(includeNested).isNotEmpty()

    override fun hasObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            objects(includeNested).any { koObject -> it == koObject.name }
        }
    }

    override fun hasObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            objects(includeNested).any { koObject -> it == koObject.name }
        }
    }

    override fun hasObject(
        includeNested: Boolean,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Boolean = objects(includeNested).any(predicate)

    override fun hasAllObjects(
        includeNested: Boolean,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Boolean = objects(includeNested).all(predicate)
}
