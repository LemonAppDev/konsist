package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeAliasProvider
import com.lemonappdev.konsist.core.declaration.KoTypeAliasDeclarationImpl
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTypeAlias

internal interface KoTypeAliasProviderCore : KoTypeAliasProvider {
    val ktFile: KtFile?
    val koFiles: Sequence<KoFile>?

    override val typeAliases: Sequence<KoTypeAliasDeclaration>
        get() = if (ktFile != null) {
            ktFile
                ?.children
                ?.filterIsInstance<KtTypeAlias>()
                ?.map { KoTypeAliasDeclarationImpl.getInstance(it, null) }
                ?.asSequence() ?: emptySequence()
        } else {
            koFiles?.flatMap { it.typeAliases } ?: emptySequence()
        }

    override fun hasTypeAliases(vararg names: String): Boolean = when {
        names.isEmpty() -> typeAliases.toList().isNotEmpty()
        else -> names.all {
            typeAliases.any { typeAlias -> typeAlias.name == it }
        }
    }
}
