package com.lemonappdev.konsist.core.util

import com.intellij.psi.PsiElement
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoFileDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.core.declaration.KoExternalDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoFunctionTypeDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoImportAliasDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoKotlinTypeDeclarationCore
import com.lemonappdev.konsist.core.model.getClass
import com.lemonappdev.konsist.core.model.getInterface
import com.lemonappdev.konsist.core.model.getObject
import com.lemonappdev.konsist.core.model.getTypeAlias
import org.jetbrains.kotlin.psi.KtFunctionType
import org.jetbrains.kotlin.psi.KtNullableType
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType
import kotlin.reflect.KClass

object TypeUtil {
    internal fun getType(
        types: List<KtTypeReference>,
        isExtension: Boolean,
        parentDeclaration: KoBaseDeclaration,
        containingFile: KoFileDeclaration,
    ): KoTypeDeclaration? {
        val type = if (isExtension && types.size > 1) {
            // We choose last because when we have extension the first one is receiver and the second one is (return) type.
            types.last()
        } else {
            if (!isExtension) {
                types.firstOrNull()
            } else {
                null
            }
                ?.children
                ?.firstOrNull()
        }

        val nestedType = if (type is KtNullableType) {
            type
                .children
                .firstOrNull()
        } else {
            type
        }

        val isAlias = containingFile
            .imports
            .firstOrNull {
                it.alias == nestedType?.text
            }


        return if (isAlias != null) {
            KoImportAliasDeclarationCore.getInstance(nestedType as KtUserType, containingFile)
        } else {
            transformPsiElementToKoTypeDeclaration(type, parentDeclaration, containingFile)
        }
    }

    internal fun hasTypeOf(type: KoTypeDeclaration?, kClass: KClass<*>): Boolean =
        kClass.qualifiedName == type?.fullyQualifiedName

    /*
    1.0.0 CleanUp - When we remove KoReceiverTypeProviderCore.hasReceiverType it will be unused.
     */
    internal fun getReceiverType(
        types: List<KtTypeReference>,
        isExtension: Boolean,
        parentDeclaration: KoBaseDeclaration,
        containingFile: KoFileDeclaration,
    ): KoTypeDeclaration? {
        val type = if (isExtension) {
            types.first()
        } else {
            null
        }
            ?.children
            ?.firstOrNull()

        return transformPsiElementToKoTypeDeclaration(type, parentDeclaration, containingFile)
    }

    private fun transformPsiElementToKoTypeDeclaration(
        type: PsiElement?,
        parentDeclaration: KoBaseDeclaration,
        containingFile: KoFileDeclaration,
    ): KoTypeDeclaration? {
        val nestedType = if (type is KtNullableType) {
            type
                .children
                .firstOrNull()
        } else {
            type
        }
        val typeText = nestedType?.text

        val fqn =
            containingFile
                .imports
                .firstOrNull { import ->
                    import.name.substringAfterLast(".") == typeText || import.alias == typeText
                }
                ?.name
                ?: (containingFile.packagee?.fullyQualifiedName + "." + typeText)

        return when {
            nestedType is KtFunctionType -> KoFunctionTypeDeclarationCore.getInstance(nestedType, parentDeclaration)
            nestedType is KtUserType && typeText != null -> {
                if (isKotlinBasicType(typeText) || isKotlinCollectionTypes(typeText)) {
                    KoKotlinTypeDeclarationCore.getInstance(nestedType, parentDeclaration)
                } else {
                    getClass(typeText, fqn, containingFile)
                        ?: getInterface(typeText, fqn, containingFile)
                        ?: getObject(typeText, fqn, containingFile)
                        ?: getTypeAlias(typeText, fqn, containingFile)
                        ?: KoExternalDeclarationCore.getInstance(typeText, nestedType)
                }
            }
            nestedType is KtTypeReference && typeText != null -> {
                getClass(typeText, fqn, containingFile)
                    ?: getInterface(typeText, fqn, containingFile)
                    ?: getObject(typeText, fqn, containingFile)
                    ?: getTypeAlias(typeText, fqn, containingFile)
                    ?: KoExternalDeclarationCore.getInstance(typeText, nestedType)
            }
            else -> null
        }
    }

    /*
    1.0.0 CleanUp - When we remove KoReceiverTypeProviderCore.hasReceiverType it will be unused.
     */
    internal fun hasReceiverType(receiverType: KoTypeDeclaration?, name: String?): Boolean = when (name) {
        null -> receiverType != null
        else -> receiverType?.name == name
    }

    internal fun isKotlinBasicType(name: String): Boolean = kotlinBasicTypes.any { it == name }

    internal fun isKotlinCollectionTypes(name: String): Boolean = kotlinCollectionTypes.any { name.startsWith("$it<") }

    // Basic types in Kotlin are described here: https://kotlinlang.org/docs/basic-types.html
    private val kotlinBasicTypes: Set<String>
        get() = setOf(
            "Byte",
            "Short",
            "Int",
            "Long",
            "Float",
            "Double",
            "UByte",
            "UShort",
            "UInt",
            "ULong",
            "UByteArray",
            "UShortArray",
            "UIntArray",
            "ULongArray",
            "Boolean",
            "Char",
            "String",
        )

    // Collections in Kotlin are described here: https://kotlinlang.org/docs/collections-overview.html#collection
    // and here https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections
    private val kotlinCollectionTypes: Set<String>
        get() = setOf(
            "AbstractCollection",
            "AbstractIterator",
            "AbstractList",
            "AbstractMap",
            "AbstractMutableCollection",
            "AbstractMutableList",
            "AbstractMutableMap",
            "AbstractMutableSet",
            "AbstractSet",
            "ArrayDeque",
            "ArrayList",
            "Array",
            "Collection",
            "HashMap",
            "HashSet",
            "LinkedHashMap",
            "LinkedHashSet",
            "List",
            "Map",
            "MutableCollection",
            "MutableList",
            "MutableMap",
            "MutableSet",
            "Set",
        )
}
