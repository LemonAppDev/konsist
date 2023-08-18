package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.core.util.ReceiverUtil
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal interface KoReceiverTypeProviderCore :
    KoReceiverTypeProvider,
    KoContainingDeclarationProviderCore,
    KoBaseProviderCore {
    val ktCallableDeclaration: KtCallableDeclaration

    override val receiverType: KoTypeDeclaration?
        get() = ReceiverUtil.getReceiverType(
            getTypeReferences(),
            ktCallableDeclaration.isExtensionDeclaration(),
            this,
        )

    override fun hasReceiverType(name: String?): Boolean = ReceiverUtil.hasReceiverType(receiverType, name)

    private fun getTypeReferences(): List<KtTypeReference> = ktCallableDeclaration
        .children
        .filterIsInstance<KtTypeReference>()
}
