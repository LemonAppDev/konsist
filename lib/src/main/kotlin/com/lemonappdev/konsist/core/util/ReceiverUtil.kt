package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.core.declaration.KoTypeDeclarationCore
import org.jetbrains.kotlin.psi.KtTypeReference

object ReceiverUtil {
    internal fun getType(
        types: List<KtTypeReference>,
        isExtension: Boolean,
        parentDeclaration: KoBaseDeclaration,
    ): KoTypeDeclaration? {
        val type = if (isExtension && types.size > 1) {
            // We choose last because when we have extension the first one is receiver and the second one is (return) type.
            types.last()
        } else if (!isExtension) {
            types.firstOrNull()
        } else {
            null
        }

        return type?.let { KoTypeDeclarationCore.getInstance(it, parentDeclaration) }
    }

    /*
    1.0.0 CleanUp - When we remove KoReceiverTypeProviderCore.hasReceiverType it will be unused.
     */
    internal fun getReceiverType(
        types: List<KtTypeReference>,
        isExtension: Boolean,
        parentDeclaration: KoBaseDeclaration,
    ): KoTypeDeclaration? {
        val type = if (isExtension) {
            types.first()
        } else {
            null
        }

        return type?.let { KoTypeDeclarationCore.getInstance(type, parentDeclaration) }
    }

    /*
    1.0.0 CleanUp - When we remove KoReceiverTypeProviderCore.hasReceiverType it will be unused.
     */
    internal fun hasReceiverType(receiverType: KoTypeDeclaration?, name: String?): Boolean = when (name) {
        null -> receiverType != null
        else -> receiverType?.name == name
    }
}
