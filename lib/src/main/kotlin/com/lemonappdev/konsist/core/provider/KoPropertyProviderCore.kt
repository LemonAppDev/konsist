package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoPropertyProviderCore : KoPropertyProvider, KoDeclarationProviderCore, KoBaseProviderCore {
    override fun properties(includeNested: Boolean): List<KoPropertyDeclaration> =
        KoDeclarationProviderCoreUtil.getKoDeclarations(
            declarations(includeNested = false, includeLocal = false),
            includeNested,
        )

    @Deprecated("Will be removed in v0.16.0", replaceWith = ReplaceWith("hasProperty()"))
    override fun containsProperty(
        includeNested: Boolean,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Boolean =
        properties(includeNested).any { predicate(it) }

    override fun numProperties(includeNested: Boolean): Int = properties(includeNested).size

    override fun countProperties(
        includeNested: Boolean,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Int = properties(includeNested).count { predicate(it) }

    override fun hasProperties(includeNested: Boolean): Boolean = properties(includeNested).isNotEmpty()

    override fun hasPropertyWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            properties(includeNested).any { koProperty -> it == koProperty.name }
        }
    }

    override fun hasPropertiesWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            properties(includeNested).any { koProperty -> it == koProperty.name }
        }
    }

    override fun hasProperty(
        includeNested: Boolean,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Boolean = properties(includeNested).any(predicate)

    override fun hasAllProperties(
        includeNested: Boolean,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Boolean = properties(includeNested).all(predicate)
}
