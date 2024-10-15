package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoExtensionTypeProvider
import com.lemonappdev.konsist.core.declaration.KoSourceDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal interface KoExtensionTypeProviderCore :
    KoBaseProviderCore,
    KoExtensionTypeProvider,
    KoSourceDeclarationCore {
    override val isExtension: Boolean get() = psiElement.isExtensionDeclaration()

    override val extensionReceiverType: KoTypeDeclaration?
        get() =
            if (isExtension) {
                val type =
                    psiElement.children
                        .filterIsInstance<KtTypeReference>()
                        .firstOrNull()

                type?.let {
                    KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration())
                }
            } else {
                null
            }
}
