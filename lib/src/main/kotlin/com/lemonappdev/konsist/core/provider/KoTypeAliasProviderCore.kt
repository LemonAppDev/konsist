package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeAliasProvider
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationCore
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeAlias

internal interface KoTypeAliasProviderCore : KoTypeAliasProvider, KoBaseProviderCore,
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

    override fun hasTypeAliases(vararg names: String): Boolean = when {
        names.isEmpty() -> typeAliases.isNotEmpty()
        else -> names.all {
            typeAliases.any { typeAlias -> typeAlias.name == it }
        }
    }
}
