package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeDeclaration
import com.lemonappdev.konsist.core.declaration.KoExternalParentDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoKotlinTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.model.getParentClass
import com.lemonappdev.konsist.core.model.getParentInterface
import org.jetbrains.kotlin.psi.KtTypeReference
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

object TypeUtil {
    internal fun getType(
        types: List<KtTypeReference>,
        isExtension: Boolean,
        parentDeclaration: KoBaseDeclaration,
        containingFile: KoFileDeclaration,
    ): KoTypeDeclaration {
        val type = if (isExtension && types.size > 1) {
            // We choose last because when we have extension the first one is receiver and the second one is (return) type.
            types.last()
        } else if (!isExtension) {
            types.firstOrNull()
        } else {
            null
        }

            val name = type?.name ?: throw IllegalArgumentException("") // Todo: change this

            val fqn =
                containingFile
                    .imports
                    .firstOrNull { import ->
                        import.name.substringAfterLast(".") == name || import.alias == name
                    }
                    ?.name
                    ?: (containingFile.packagee?.fullyQualifiedName + "." + name)

        return getParentClass(name, fqn, containingFile)
            ?: getParentInterface(name, fqn, containingFile)
            ?: KoKotlinTypeDeclarationCore.getInstance(type, containingFile)
    }

    internal fun hasTypeOf(type: KoKotlinTypeDeclaration?, kClass: KClass<*>): Boolean =
        if (type?.isKotlinType == true) {
            kClass.simpleName == type.name
        } else {
            kClass.qualifiedName == type?.fullyQualifiedName
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

        return type?.let { KoKotlinTypeDeclarationCore.getInstance(type, parentDeclaration) }
    }

    /*
    1.0.0 CleanUp - When we remove KoReceiverTypeProviderCore.hasReceiverType it will be unused.
     */
    internal fun hasReceiverType(receiverType: KoTypeDeclaration?, name: String?): Boolean = when (name) {
        null -> receiverType != null
        else -> receiverType?.name == name
    }
}
