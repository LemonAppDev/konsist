package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoExplicitReturnTypeProvider
import com.lemonappdev.konsist.core.util.ReceiverUtil
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtTypeReference

internal interface KoExplicitReturnTypeProviderCore :
    KoExplicitReturnTypeProvider,
    KoParentProviderCore,
    KoExtensionProviderCore,
    KoBaseProviderCore {
    val ktFunction: KtFunction

    private fun getTypeReferences(): List<KtTypeReference> = ktFunction
        .children
        .filterIsInstance<KtTypeReference>()

    override val explicitReturnType: KoTypeDeclaration?
        get() = ReceiverUtil.getType(getTypeReferences(), isExtension, this)

    override val hasExplicitReturnType: Boolean
        get() = ktFunction.hasDeclaredReturnType()
}
