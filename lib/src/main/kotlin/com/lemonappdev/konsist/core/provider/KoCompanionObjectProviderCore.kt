package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoCompanionObjectDeclaration
import com.lemonappdev.konsist.api.provider.KoCompanionObjectProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoCompanionObjectProviderCore :
    KoCompanionObjectProvider,
    KoObjectProviderCore,
    KoBaseProviderCore {
    override val companionObject: KoCompanionObjectDeclaration?
        get() =  companionObjects(false).firstOrNull()

    override fun companionObjects(includeNested: Boolean): List<KoCompanionObjectDeclaration> =
        KoDeclarationProviderCoreUtil.getKoDeclarations(
            declarations(includeNested = false, includeLocal = false),
            includeNested,
        )

    override fun numCompanionObjects(includeNested: Boolean): Int = companionObjects(includeNested).size

    override fun countCompanionObjects(
        includeNested: Boolean,
        predicate: (KoCompanionObjectDeclaration) -> Boolean,
    ): Int = companionObjects(includeNested).count { predicate(it) }

    override fun hasCompanionObject(): Boolean = companionObject != null

    override fun hasCompanionObject(
        includeNested: Boolean,
        predicate: (KoCompanionObjectDeclaration) -> Boolean,
    ): Boolean = companionObjects(includeNested).any(predicate)

    override fun hasCompanionObjects(includeNested: Boolean): Boolean = companionObjects(includeNested).isNotEmpty()

    override fun hasAllCompanionObjects(
        includeNested: Boolean,
        predicate: (KoCompanionObjectDeclaration) -> Boolean,
    ): Boolean = companionObjects(includeNested).all(predicate)

    override fun hasCompanionObjectWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        ignoreCase: Boolean,
    ): Boolean = hasCompanionObjectWithName(listOf(name, *names), includeNested, ignoreCase)

    override fun hasCompanionObjectWithName(
        names: Collection<String>,
        includeNested: Boolean,
        ignoreCase: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasCompanionObjects(includeNested)
            else -> names.any { companionObjects(includeNested).any { parentClass -> parentClass.hasName(it, ignoreCase) } }
        }

    override fun hasCompanionObjectsWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        ignoreCase: Boolean,
    ): Boolean = hasCompanionObjectsWithAllNames(listOf(name, *names), includeNested, ignoreCase)

    override fun hasCompanionObjectsWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
        ignoreCase: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasCompanionObjects(includeNested)
            else -> names.all { companionObjects(includeNested).any { parentClass -> parentClass.hasName(it, ignoreCase) } }
        }
}
