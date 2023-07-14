package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.api.provider.KoReturnTypeProvider
import com.lemonappdev.konsist.core.util.ReceiverUtil
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtTypeReference

internal interface KoReturnTypeProviderCore :
    KoReturnTypeProvider,
    KoParentProviderCore,
    KoExtensionProviderCore {
    val ktFunction: KtFunction

    private fun getTypeReferences(): List<KtTypeReference> = ktFunction
        .children
        .filterIsInstance<KtTypeReference>()

    override val returnType: KoTypeDeclaration?
        get() = ReceiverUtil.getType(getTypeReferences(), isExtension(), this)

    override fun hasReturnType(): Boolean = ktFunction.hasDeclaredReturnType()
}
