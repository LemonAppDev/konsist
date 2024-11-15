package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationCore
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtElement

@Deprecated("Will be removed in version 0.19.0", ReplaceWith("KoSourceTypeProvider"))
internal interface KoSourceAndAliasTypeProviderCore :
    KoSourceAndAliasTypeProvider,
    KoTextProviderCore,
    KoBaseProviderCore {
    val ktElement: KtElement

    private val file: KoFileDeclaration
        get() = KoFileDeclarationCore(ktElement.containingKtFile)

    @Deprecated("Will be removed in version 0.19.0", replaceWith = ReplaceWith("isImportAlias"))
    override val isAlias: Boolean
        get() = (this as? KoTypeDeclaration)?.sourceDeclaration?.isImportAlias == true

    override val sourceType: String
        get() =
            if (isAlias) {
                file
                    .imports
                    .firstOrNull { it.alias?.name == ktElement.text.removeSuffix("?") }
                    ?.name
                    ?.split(".")
                    ?.toMutableList()
                    ?.lastOrNull { it.isNotBlank() }
                    ?: text
            } else {
                text
            }

    override val bareSourceType: String
        get() =
            when {
                this is KoTypeDeclaration && sourceDeclaration?.isTypeAlias == true ->
                    sourceDeclaration?.asTypeAliasDeclaration()?.type?.text ?: text
                else -> TypeUtil.getBareType(sourceType)
            }
}
