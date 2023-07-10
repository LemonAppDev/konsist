package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.declaration.KoTypeDeclarationImpl
import org.jetbrains.kotlin.psi.KtTypeReference

object ReceiverUtil {
    internal fun getType(types: List<KtTypeReference>, isExtension: Boolean, parentDeclaration: KoParentProvider?): KoTypeDeclaration? {
        val type = if (isExtension && types.size > 1) {
            // We choose last because when we have extension the first one is receiver and the second one is (return) type.
            types.last()
        } else if (!isExtension) {
            types.firstOrNull()
        } else {
            null
        }

        return type?.let { KoTypeDeclarationImpl.getInstance(it, parentDeclaration) }
    }

    internal fun getReceiverType(
        types: List<KtTypeReference>,
        isExtension: Boolean,
        parentDeclaration: KoParentProvider?,
    ): KoTypeDeclaration? {
        val type = if (isExtension) {
            types.first()
        } else {
            null
        }

        return type?.let { KoTypeDeclarationImpl.getInstance(type, parentDeclaration) }
    }

    internal fun hasReceiverType(receiverType: KoTypeDeclaration?, name: String?): Boolean = when (name) {
        null -> receiverType != null
        else -> receiverType?.name == name
    }
}
