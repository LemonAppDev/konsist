package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.container.KoFile
import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import com.lemonappdev.konsist.core.container.KoFileImpl
import org.jetbrains.kotlin.psi.KtTypeReference

internal interface KoSourceAndAliasTypeProviderCore : KoSourceAndAliasTypeProvider, KoNameProviderCore, KoBaseProviderCore {
    val ktTypeReference: KtTypeReference

    private val file: KoFile
        get() = KoFileImpl(ktTypeReference.containingKtFile)

    override val aliasType: String?
        get() = file
            .imports
            .firstOrNull { it.alias == ktTypeReference.text.removeSuffix("?") }
            ?.alias

    override val sourceType: String
        get() = if (isAlias()) {
            file
                .imports
                .first { it.alias == ktTypeReference.text.removeSuffix("?") }
                .name
                .split(".")
                .toMutableList()
                .last { it.isNotBlank() }
        } else {
            name
                .removeSuffix("?")
        }

    override fun isAlias(): Boolean = aliasType != null
}
