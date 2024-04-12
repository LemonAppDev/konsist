package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReceiverTypeProvider
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
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
        get() {
            val references = getTypeReferences()

            val type =
                if (ktCallableDeclaration.isExtensionDeclaration()) {
                    references.firstOrNull()
                } else {
                    null
                }

            return type?.let {
                KoTypeDeclarationCore.getInstance(it, this.castToKoBaseDeclaration())
            }
        }

    override fun hasReceiverType(predicate: ((KoTypeDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> receiverType != null
            else -> receiverType?.let { predicate(it) } ?: false
        }

    override fun hasReceiverTypeOf(kClass: KClass<*>): Boolean = hasTypeOf(receiverType, kClass)

    private fun getTypeReferences(): List<KtTypeReference> =
        ktCallableDeclaration
            .children
            .filterIsInstance<KtTypeReference>()
}
