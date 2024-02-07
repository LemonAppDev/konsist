package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.TypeUtil
import com.lemonappdev.konsist.core.util.TypeUtil.hasTypeOf
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import kotlin.reflect.KClass

internal interface KoReceiverTypeProviderCore :
    KoReceiverTypeProvider,
    KoContainingDeclarationProviderCore,
    KoKDocProviderCore,
    KoBaseProviderCore {
    val ktCallableDeclaration: KtCallableDeclaration

    override val receiverType: KoTypeDeclaration?
        get() = TypeUtil.getReceiverType(
            getTypeReferences(),
            ktCallableDeclaration.isExtensionDeclaration(),
            this.castToKoBaseDeclaration(),
        )

    @Deprecated("Will be removed in v1.0.0", ReplaceWith("hasReceiverType { it.name == name }"))
    override fun hasReceiverType(name: String): Boolean = TypeUtil.hasReceiverType(receiverType, name)

    override fun hasReceiverType(predicate: ((KoTypeDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> receiverType != null
            else -> receiverType?.let { predicate(it) } ?: false
        }

    override fun hasReceiverTypeOf(kClass: KClass<*>): Boolean = hasTypeOf(receiverType, kClass)

    private fun getTypeReferences(): List<KtTypeReference> = ktCallableDeclaration
        .children
        .filterIsInstance<KtTypeReference>()
}
