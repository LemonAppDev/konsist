package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoObjectProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoObjectProviderCore :
    KoObjectProvider,
    KoDeclarationProviderCore,
    KoBaseProviderCore {
    override fun objects(includeNested: Boolean): List<KoObjectDeclaration> =
        KoDeclarationProviderCoreUtil.getKoDeclarations(
            declarations(includeNested = false, includeLocal = false),
            includeNested,
        )

    override fun numObjects(includeNested: Boolean): Int = objects(includeNested).size

    override fun countObjects(
        includeNested: Boolean,
        predicate: (KoObjectDeclaration) -> Boolean,
    ): Int = objects(includeNested).count { predicate(it) }

    override fun hasObjects(includeNested: Boolean): Boolean = objects(includeNested).isNotEmpty()

    override fun hasObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean = hasObjectWithName(listOf(name, *names), includeNested)

    override fun hasObjectWithName(
        names: Collection<String>,
        includeNested: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasObjects(includeNested)
            else ->
                names.any {
                    objects(includeNested).any { koObject -> it == koObject.name }
                }
        }

    override fun hasObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean = hasObjectsWithAllNames(listOf(name, *names), includeNested)

    override fun hasObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasObjects(includeNested)
            else ->
                names.all {
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
