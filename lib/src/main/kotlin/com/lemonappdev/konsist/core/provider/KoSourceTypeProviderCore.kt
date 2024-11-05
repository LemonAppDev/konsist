package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.api.provider.KoSourceTypeProvider
import com.lemonappdev.konsist.core.declaration.KoFileDeclarationCore
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.psi.KtElement

internal interface KoSourceTypeProviderCore :
    KoSourceTypeProvider,
    KoTextProviderCore,
    KoBaseProviderCore {
    val ktElement: KtElement

    private val file: KoFileDeclaration
        get() = KoFileDeclarationCore(ktElement.containingKtFile)

    override val sourceType: String
        get() =
            if ((this as? KoDeclarationCastProvider)?.isImportAlias == true) {
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
                this is KoTypeDeclaration && isTypeAlias ->
                    asTypeAliasDeclaration()?.type?.text ?: text
                else -> TypeUtil.getBareType(sourceType)
            }
}
