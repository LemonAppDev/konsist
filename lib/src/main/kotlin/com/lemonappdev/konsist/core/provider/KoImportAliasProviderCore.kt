package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoImportAliasProvider

internal interface KoImportAliasProviderCore :
    KoImportAliasProvider,
    KoBaseProviderCore,
    KoContainingDeclarationProviderCore,
    KoContainingFileProviderCore {
    override val importAliases: List<KoImportAliasDeclaration>
        get() =
            containingFile
                .imports
                .mapNotNull { it.alias }

    override val numImportAliases: Int
        get() = importAliases.size

    override fun countImportAliases(predicate: (KoImportAliasDeclaration) -> Boolean): Int = importAliases.count { predicate(it) }

    override fun hasImportAliases(): Boolean = importAliases.isNotEmpty()

    override fun hasImportAliasWithName(
        name: String,
        vararg names: String,
    ): Boolean = hasImportAliasWithName(listOf(name, *names))

    override fun hasImportAliasWithName(names: Collection<String>, ): Boolean = when {
        names.isEmpty() -> hasImportAliases()
        else -> names.any {
            importAliases.any { importAlias -> it == importAlias.name }
        }
    }

    override fun hasImportAliasesWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean = hasImportAliasesWithAllNames(listOf(name, *names))

    override fun hasImportAliasesWithAllNames(names: Collection<String>, ): Boolean = when {
        names.isEmpty() -> hasImportAliases()
        else -> names.all {
            importAliases.any { importAlias -> it == importAlias.name }
        }
    }

    override fun hasImportAlias(predicate: (KoImportAliasDeclaration) -> Boolean): Boolean = importAliases.any(predicate)

    override fun hasAllImportAliases(predicate: (KoImportAliasDeclaration) -> Boolean): Boolean = importAliases.all(predicate)
}
