package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeAliasProvider
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationImpl
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeAlias

internal interface KoTypeAliasProviderCore : KoTypeAliasProvider, KoBaseProviderCore, KoParentProviderCore {
    val ktFile: KtFile

    override val typeAliases: List<KoTypeAliasDeclaration>
        get() = ktFile
                .children
                .filterIsInstance<KtTypeAlias>()
                .map { KoTypeAliasDeclarationImpl.getInstance(it, this) }

    override val numTypeAliases: Int
        get() = typeAliases.size

    override fun hasTypeAliases(vararg names: String): Boolean = when {
        names.isEmpty() -> typeAliases.isNotEmpty()
        else -> names.all {
            typeAliases.any { typeAlias -> typeAlias.name == it }
        }
    }
}
