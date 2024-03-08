package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoClassProviderCore : KoClassProvider, KoDeclarationProviderCore, KoBaseProviderCore {
    override fun classes(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoClassDeclaration> =
        KoDeclarationProviderCoreUtil.getKoDeclarations(
            declarations(includeNested = false, includeLocal = false),
            includeNested,
            includeLocal,
        )

    @Deprecated("Will be removed in v0.16.0", replaceWith = ReplaceWith("hasClass()"))
    override fun containsClass(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Boolean =
        classes(includeNested, includeLocal).any { predicate(it) }

    override fun numClasses(includeNested: Boolean, includeLocal: Boolean): Int =
        classes(includeNested, includeLocal).size

    override fun countClasses(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoClassDeclaration) -> Boolean,
    ): Int = classes(includeNested, includeLocal).count { predicate(it) }

    override fun hasClasses(includeNested: Boolean, includeLocal: Boolean): Boolean =
        classes(includeNested, includeLocal).isNotEmpty()

    override fun hasClassWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            classes(includeNested, includeLocal).any { koClass -> it == koClass.name }
        }
    }

    override fun hasClassesWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
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
