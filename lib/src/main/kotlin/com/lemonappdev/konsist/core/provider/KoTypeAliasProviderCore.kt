package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeAliasProvider
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeAlias

internal interface KoTypeAliasProviderCore :
    KoTypeAliasProvider,
    KoBaseProviderCore,
    KoContainingDeclarationProviderCore {
    val ktFile: KtFile

    override val typeAliases: List<KoTypeAliasDeclaration>
        get() =
            ktFile
                .children
                .filterIsInstance<KtTypeAlias>()
                .map { KoTypeAliasDeclarationCore.getInstance(it, this.castToKoBaseDeclaration()) }

    override val numTypeAliases: Int
        get() = typeAliases.size

    override fun countTypeAliases(predicate: (KoTypeAliasDeclaration) -> Boolean): Int = typeAliases.count { predicate(it) }

    override fun hasTypeAliases(): Boolean = typeAliases.isNotEmpty()

    override fun hasTypeAliasWithName(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = hasTypeAliasWithName(listOf(name, *names), ignoreCase)

    override fun hasTypeAliasWithName(names: Collection<String>, ignoreCase: Boolean,): Boolean =
        when {
            names.isEmpty() -> hasTypeAliases()
            else ->
                names.any {
                    typeAliases.any { typeAlias -> typeAlias.hasName(it, ignoreCase) }
                }
        }

    override fun hasTypeAliasesWithAllNames(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = hasTypeAliasesWithAllNames(listOf(name, *names), ignoreCase)

    override fun hasTypeAliasesWithAllNames(names: Collection<String>, ignoreCase: Boolean,): Boolean =
        when {
            names.isEmpty() -> hasTypeAliases()
            else ->
                names.all {
                    typeAliases.any { typeAlias -> typeAlias.hasName(it, ignoreCase) }
                }
        }

    override fun hasTypeAlias(predicate: (KoTypeAliasDeclaration) -> Boolean): Boolean = typeAliases.any(predicate)

    override fun hasAllTypeAliases(predicate: (KoTypeAliasDeclaration) -> Boolean): Boolean = typeAliases.all(predicate)
}
