package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil

internal interface KoPropertyProviderCore : KoPropertyProvider, KoDeclarationProviderCore, KoBaseProviderCore {
    override fun properties(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoPropertyDeclaration> =
        KoDeclarationProviderCoreUtil.getKoDeclarations(
            declarations(includeNested = false, includeLocal = false),
            includeNested,
            includeLocal,
        )

    @Deprecated("Will be removed in v1.0.0", replaceWith = ReplaceWith("hasProperty()"))
    override fun containsProperty(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Boolean =
        properties(includeNested, includeLocal).any { predicate(it) }

    override fun numProperties(includeNested: Boolean, includeLocal: Boolean): Int =
        properties(includeNested, includeLocal).size

    override fun countProperties(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoPropertyDeclaration) -> Boolean,
    ): Int = properties(includeNested, includeLocal).count { predicate(it) }

    override fun hasProperties(includeNested: Boolean, includeLocal: Boolean): Boolean =
        properties(includeNested, includeLocal).isNotEmpty()

    override fun hasPropertyWithName(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            properties(includeNested, includeLocal).any { koProperty -> it == koProperty.name }
        }
    }

    override fun hasPropertiesWithAllNames(
        name: String,
        vararg names: String,
        includeNested: Boolean,
        includeLocal: Boolean
    ): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            properties(includeNested, includeLocal).any { koProperty -> it == koProperty.name }
        }
    }

    override fun hasProperty(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoPropertyDeclaration) -> Boolean
    ): Boolean = properties(includeNested, includeLocal).any(predicate)

    override fun hasAllProperties(
        includeNested: Boolean,
        includeLocal: Boolean,
        predicate: (KoPropertyDeclaration) -> Boolean
    ): Boolean = properties(includeNested, includeLocal).all(predicate)
}
