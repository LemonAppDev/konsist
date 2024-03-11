package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeAliasProvider
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationCore
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeAlias

internal interface KoTypeAliasProviderCore :
    KoTypeAliasProvider,
    KoBaseProviderCore,
    KoContainingDeclarationProviderCore {
    val ktFile: KtFile

    override val typeAliases: List<KoTypeAliasDeclaration>
        get() = ktFile
            .children
            .filterIsInstance<KtTypeAlias>()
            .map { KoTypeAliasDeclarationCore.getInstance(it, this) }

    override val numTypeAliases: Int
        get() = typeAliases.size

    override fun countTypeAliases(predicate: (KoTypeAliasDeclaration) -> Boolean): Int =
        typeAliases.count { predicate(it) }

    @Deprecated(
        """
            Will be removed in v1.0.0. 
            If you passed one argument - replace with `hasTypeAliasWithName`, otherwise with `hasTypeAliasesWithAllNames`.
            """,
    )
    override fun hasTypeAliases(vararg names: String): Boolean = when {
        names.isEmpty() -> typeAliases.isNotEmpty()
        else -> names.all {
            typeAliases.any { typeAlias -> typeAlias.name == it }
        }
    }

    override fun hasTypeAliases(): Boolean = typeAliases.isNotEmpty()

    override fun hasTypeAliasWithName(name: String, vararg names: String): Boolean {
        val givenNames = names.toList() + name

        return givenNames.any {
            typeAliases.any { typeAlias -> it == typeAlias.name }
        }
    }

    override fun hasTypeAliasesWithAllNames(name: String, vararg names: String): Boolean {
        val givenNames = names.toList() + name

        return givenNames.all {
            typeAliases.any { typeAlias -> it == typeAlias.name }
        }
    }

    override fun hasTypeAlias(predicate: (KoTypeAliasDeclaration) -> Boolean): Boolean = typeAliases.any(predicate)

    override fun hasAllTypeAliases(predicate: (KoTypeAliasDeclaration) -> Boolean): Boolean = typeAliases.all(predicate)
}
