package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoSourceAndAliasTypeProvider
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationCore
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtElement

internal interface KoSourceAndAliasTypeProviderCore :
    KoSourceAndAliasTypeProvider,
    KoTextProviderCore,
    KoBaseProviderCore {
    val ktElement: KtElement

    private val file: KoFileDeclaration
        get() = KoFileDeclarationCore(ktElement.containingKtFile)

    override val isAlias: Boolean
        get() = (this as? KoTypeDeclaration)?.isImportAlias == true

    override val sourceType: String
        get() =
            if (isAlias) {
                file
                    .imports
                    .first { it.alias?.name == ktElement.text.removeSuffix("?") }
                    .name
                    .split(".")
                    .toMutableList()
                    .last { it.isNotBlank() }
            } else {
                text
            }

    override val bareSourceType: String
        get() = when {
            this is KoTypeDeclaration && isTypeAlias ->
                asTypeAliasDeclaration()?.type?.text ?: text
            else -> TypeUtil.getBareType(sourceType)
        }


}
