package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoPropertyTypeProvider
import com.lemonappdev.konsist.core.util.ReceiverUtil
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal interface KoPropertyTypeProviderCore :
    KoPropertyTypeProvider,
    KoContainingDeclarationProviderCore,
    KoBaseProviderCore {
    val ktCallableDeclaration: KtCallableDeclaration
    override val type: KoTypeDeclaration?
        get() = ReceiverUtil.getType(getTypeReferences(), ktCallableDeclaration.isExtensionDeclaration(), this)

    private fun getTypeReferences(): List<KtTypeReference> = ktCallableDeclaration
        .children
        .filterIsInstance<KtTypeReference>()

    override fun hasType(name: String?): Boolean = when (name) {
        null -> this.type != null
        else -> this.type?.name == name
    }
}
