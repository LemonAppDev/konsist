package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReturnTypeProvider
import com.lemonappdev.konsist.core.util.ReceiverUtil
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal interface KoReturnTypeProviderCore :
    KoReturnTypeProvider,
    KoContainingDeclarationProviderCore,
    KoKDocProviderCore,
    KoBaseProviderCore {
    val ktFunction: KtFunction

    private fun getTypeReferences(): List<KtTypeReference> = ktFunction
        .children
        .filterIsInstance<KtTypeReference>()

    override val returnType: KoTypeDeclaration?
        get() = ReceiverUtil.getType(getTypeReferences(), ktFunction.isExtensionDeclaration(), this)

    override val hasReturnType: Boolean
        get() = ktFunction.hasDeclaredReturnType()
}
