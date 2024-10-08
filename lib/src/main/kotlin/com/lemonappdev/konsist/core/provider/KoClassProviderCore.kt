package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoClassProviderCore :
    KoClassProvider,
    KoDeclarationProviderCore,
    KoBaseProviderCore {
    override fun classes(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassDeclaration> =
        KoDeclarationProviderCoreUtil.getKoDeclarations(
            declarations(includeNested = false, includeLocal = false),
            includeNested,
            includeLocal,
        )

    override fun numClasses(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Int = classes(includeNested, includeLocal).size

    override fun countClasses(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Int = classes(includeNested, includeLocal).count { predicate(it) }

    override fun hasClasses(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = classes(includeNested, includeLocal).isNotEmpty()

    override fun hasClassWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = hasClassWithName(listOf(name, *names), includeNested, includeLocal)

    override fun hasClassWithName(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasClasses(includeNested, includeLocal)
            else ->
                names.any {
                    classes(includeNested, includeLocal).any { koClass -> it == koClass.name }
                }
        }

    override fun hasClassesWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean = hasClassesWithAllNames(listOf(name, *names), includeNested, includeLocal)

    override fun hasClassesWithAllNames(
        names: Collection<String>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean =
        when {
            names.isEmpty() -> hasClasses(includeNested, includeLocal)
            else ->
                names.all {
                    classes(includeNested, includeLocal).any { koClass -> it == koClass.name }
                }
        }

    override fun hasClass(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean = classes(includeNested, includeLocal).any(predicate)

    override fun hasAllClasses(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean = classes(includeNested, includeLocal).all(predicate)
}
