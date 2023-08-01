package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeAliasProvider
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationImpl
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeAlias

internal interface KoTypeAliasProviderCore : KoTypeAliasProvider, KoBaseProviderCore {
    val ktFile: KtFile?
    val koFiles: List<KoFile>?

    override val typeAliases: List<KoTypeAliasDeclaration>
        get() = if (ktFile != null) {
            ktFile
                ?.children
                ?.filterIsInstance<KtTypeAlias>()
                ?.map { KoTypeAliasDeclarationImpl.getInstance(it, null) }
                ?: emptyList()
        } else {
            koFiles?.flatMap { it.typeAliases } ?: emptyList()
        }

    override fun hasTypeAliases(vararg names: String): Boolean = when {
        names.isEmpty() -> typeAliases.isNotEmpty()
        else -> names.all {
            typeAliases.any { typeAlias -> typeAlias.name == it }
        }
    }
}
