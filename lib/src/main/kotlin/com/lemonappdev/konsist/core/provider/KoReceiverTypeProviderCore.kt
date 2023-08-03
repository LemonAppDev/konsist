package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.core.util.ReceiverUtil
import org.jetbrains.kotlin.psi.KtTypeReference

internal interface KoReceiverTypeProviderCore :
    KoReceiverTypeProvider,
    KoContainingDeclarationProviderCore,
    KoExtensionProviderCore,
    KoBaseProviderCore {
    override val receiverType: KoTypeDeclaration?
        get() = ReceiverUtil.getReceiverType(getTypeReferences(), isExtension, this)

    override fun hasReceiverType(name: String?): Boolean = ReceiverUtil.hasReceiverType(receiverType, name)

    private fun getTypeReferences(): List<KtTypeReference> = ktCallableDeclaration
        .children
        .filterIsInstance<KtTypeReference>()
}
