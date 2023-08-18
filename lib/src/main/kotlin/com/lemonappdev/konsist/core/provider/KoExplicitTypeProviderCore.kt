package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoExplicitTypeProvider
import com.lemonappdev.konsist.core.util.ReceiverUtil
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration

internal interface KoExplicitTypeProviderCore :
    KoExplicitTypeProvider,
    KoContainingDeclarationProviderCore,
    KoBaseProviderCore {
    val ktProperty: KtProperty
    override val explicitType: KoTypeDeclaration?
        get() = ReceiverUtil.getType(getTypeReferences(), ktProperty.isExtensionDeclaration(), this)

    private fun getTypeReferences(): List<KtTypeReference> = ktProperty
        .children
        .filterIsInstance<KtTypeReference>()

    override fun hasExplicitType(type: String?): Boolean = when (type) {
        null -> this.explicitType != null
        else -> this.explicitType?.name == type
    }
}
